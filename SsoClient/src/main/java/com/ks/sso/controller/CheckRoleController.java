package com.ks.sso.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CheckRoleController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("-------------------------");
        return "index";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/user/check/role1")
    public String checkrole1() {
        return "success";
    }
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/user/check/role2")
    public String checkrole2() {
        return "success";
    }
    @GetMapping("/user/check/role3")
    public String checkrole3() {
        return "success";
    }
}
