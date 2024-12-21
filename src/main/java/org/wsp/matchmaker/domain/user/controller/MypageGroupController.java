package org.wsp.matchmaker.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wsp.matchmaker.domain.group.entity.UserGroup;
import org.wsp.matchmaker.domain.group.repository.UserGroupRepository;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

import java.util.List;
import java.util.UUID;

@Controller
public class MypageGroupController {

    private final UserService userService;
    private final UserGroupRepository userGroupRepository;

    // 생성자 주입
    @Autowired
    public MypageGroupController(UserService userService, UserGroupRepository userGroupRepository) {
        this.userService = userService;
        this.userGroupRepository = userGroupRepository;
    }

    // 나의 모임 페이지 이동
    @GetMapping("/mypage/group")
    public String getGroup(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // List<Group> userGroups = userService.getUserGroups(user.getUserId());
        // model.addAttribute("userGroups", userGroups); // 모임 정보 모델에 추가
        return "mypage/group"; // 나의 모임 페이지로 이동
    }

    // 마이페이지 - 가입한 모임 탈퇴
    @DeleteMapping("/mypage/group/leave")
    public ApiResponse<Void> leaveGroup(@RequestParam Long userGroupId) {
        try {
            // userGroupId로 UserGroup 엔티티 조회
            UserGroup userGroupToDelete = userGroupRepository.findById(userGroupId)
                    .orElseThrow(() -> new IllegalArgumentException("UserGroup not found"));

            // 삭제
            userGroupRepository.delete(userGroupToDelete);
            return ApiResponse.onSuccess(null); // 탈퇴 성공 응답
        } catch (Exception e) {
            return ApiResponse.onFailure("ERR500", "서버 오류가 발생했습니다. \n" + e.getMessage(), null); // 서버 오류
        }
    }
}