package org.wsp.matchmaker.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

import java.util.UUID;

@Controller
public class MypageApplicationController {

    @Autowired
    private UserService userService; // 모집신청 서비스

    //나의 모집글 신청 목록 페이지
    @GetMapping("/mypage/application")
    public String getApplication(HttpSession session, Model model) {
        //User user = (User) session.getAttribute("user");
        //List<RecruitmentApplication> recruitmentApplications = userService.getUserApplications(user.getUserId());
        //model.addAttribute("recruitmentApplications", recruitmentApplications);
        return "mypage/application"; // 나의 모집신청 페이지로 이동
    }

    @DeleteMapping("/mypage/cancelApplication")
    public ApiResponse<Void> cancelRecruitmentApplication(@RequestParam Long applicationId) {
        try {
            // UserService를 사용하여 모집 신청 취소
            userService.cancelRecruitmentApplication(applicationId);

            // 취소 성공 응답
            return ApiResponse.onSuccess(null);
        } catch (IllegalArgumentException e) {
            // 모집 신청을 찾을 수 없거나 잘못된 요청 처리
            return ApiResponse.onFailure("ERR404", e.getMessage(), null);
        } catch (Exception e) {
            // 서버 오류 처리
            return ApiResponse.onFailure("ERR500", "서버 오류가 발생했습니다. \n" + e.getMessage(), null);
        }
    }
}
