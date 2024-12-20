package org.wsp.matchmaker;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // /WEB-INF/views/index.jsp를 찾습니다.
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // /WEB-INF/views/login.jsp를 찾습니다.
    }


}
