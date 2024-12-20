package org.wsp.matchmaker.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class MypageController {

    private final UserService userService;

    @Autowired
    public MypageController(UserService userService) {
        this.userService = userService;
    }

    // 내 정보 조회
    @GetMapping("/mypage")
    public String getUserInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "mypage/mypage"; // 내 정보 페이지로 이동
    }

    //나의 모집글 신청 목록 페이지
    @GetMapping("/mypage/application")
    public String getApplication(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "mypage/application"; // 나의 모집신청 페이지로 이동
    }

    //나의 모임 페이지 이동
    @GetMapping("/mypage/group")
    public String getGroup(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Group> userGroups = userService.getUserGroups(user.getUserId());
        model.addAttribute("userGroups", userGroups); // 모임 정보 모델에 추가
        return "mypage/group"; // 나의 모임 페이지로 이동
    }

    //회원 정보 수정 페이지
    @GetMapping("/mypage/update")
    public String updateUserInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "mypage/update"; // 내 정보 수정 페이지로 이동
    }
    
    //회원 정보 수정
    @PutMapping("/mypage/update")
    public ApiResponse<Void> updateUserInfo(
            @RequestParam UUID userId,
            @RequestParam String userName,
            @RequestParam String userEmail,
            @RequestParam String gender,
            @RequestParam Set<String> hobbies) {
        try {
            userService.updateUserInfo(userId, userName, userEmail, gender, hobbies);
            return ApiResponse.onSuccess(null); // 성공 응답
        } catch (IllegalArgumentException e) {
            return ApiResponse.onFailure("ERR001", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("ERR500", "서버 오류가 발생했습니다. \n" + e, null);
        }
    }

}
