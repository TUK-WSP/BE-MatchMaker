package org.wsp.matchmaker.jsp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/report")
public class UserServletController {
    @GetMapping("/user")
    public String userReport() {
        return "user/report";
    }

}
