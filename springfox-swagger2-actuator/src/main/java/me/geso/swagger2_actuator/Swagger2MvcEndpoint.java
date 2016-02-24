package me.geso.swagger2_actuator;

import com.google.common.base.Optional;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.mvc.AbstractEndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class Swagger2MvcEndpoint extends AbstractEndpointMvcAdapter
        implements MvcEndpoint {
    @Value("${swagger2_actuator.access_control_allow_origin}")
    private String accessControlAllowOrigin;

    // Access-Control-Allow-Headers
    @Value("${swagger2_actuator.access_control_allow_headers}")
    private String accessControlAllowHeaders;

    @Autowired
    private DocumentationCache documentationCache;

    @Autowired
    private ServiceModelToSwagger2Mapper mapper;

    @Autowired
    private JsonSerializer jsonSerializer;

    public Swagger2MvcEndpoint(Swagger2Endpoint delegate) {
        super(delegate);
    }

    @ApiIgnore
    @RequestMapping(value = "",
            method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Json> getDocumentation(
            @RequestParam(value = "group", required = false) String swaggerGroup,
            HttpServletRequest servletRequest) {

        String groupName = Optional.fromNullable(swaggerGroup).or(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
        }
        Swagger swagger = mapper.mapDocumentation(documentation);
        if (isNullOrEmpty(swagger.getHost())) {
            swagger.host(hostName(servletRequest));
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        if (accessControlAllowOrigin != null) {
            httpHeaders.add("Access-Control-Allow-Origin", accessControlAllowOrigin);
        }
        if (accessControlAllowHeaders != null) {
            httpHeaders.add("Access-Control-Allow-Headers", accessControlAllowHeaders);
        }
        return new ResponseEntity<Json>(jsonSerializer.toJson(swagger), httpHeaders, HttpStatus.OK);
    }

    private String hostName(HttpServletRequest servletRequest) {
        URI uri = URI.create(String.valueOf(servletRequest.getRequestURL()));
        String host = uri.getHost();
        int port = uri.getPort();
        if (port > -1) {
            return String.format("%s:%d", host, port);
        }
        return host;
    }

}
