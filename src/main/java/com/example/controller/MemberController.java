package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Api("Members")
public class MemberController {
    @ApiOperation("Get list of members")
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<Member> members() {
        return Collections.singletonList(
                new Member("Tetsu", "tetsu@example.com")
        );
    }

    @Data
    @AllArgsConstructor
    private static class Member {
        private String name;
        private String email;
    }
}
