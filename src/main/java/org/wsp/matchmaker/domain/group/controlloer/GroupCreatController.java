package com.example.controller;

import com.example.repository.GroupRepository;
import com.example.repository.UserGroupRepository;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.global.commonEntity.enums.Status;
import org.wsp.matchmaker.domain.group.entity.UserGroup;
import org.wsp.matchmaker.domain.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/group/create")
public class GroupCreateController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private GroupRepository groupRepository = new GroupRepository();
    private UserGroupRepository userGroupRepository = new UserGroupRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 파라미터 받기
        String groupName = request.getParameter("group_name");
        String groupDescription = request.getParameter("group_description");
        String groupStatus = request.getParameter("group_status");
        String groupThumbnailImageUrl = request.getParameter("group_thumbnail_image_url");

        if (groupName == null || groupName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "group_name is required");
            return;
        }

        if (groupStatus == null || groupStatus.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "group_status is required");
            return;
        }

        Status status;
        try {
            status = Status.valueOf(groupStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid group_status value");
            return;
        }

        // 그룹 객체 생성 (UUID 사용)
        Group group = Group.builder()
                .groupId(UUID.randomUUID())
                .groupName(groupName)
                .groupDescription(groupDescription)
                .status(status)
                .groupThumbnailImageUrl(groupThumbnailImageUrl)
                .build();

        // 그룹 저장
        groupRepository.save(group);

        // 로그인 유저 정보 얻기 (세션에서 가져오는 로직이 필요)
        User user = getLoggedInUser(request);

        // UserGroup 엔티티 생성 및 저장 (유저를 그룹에 속하게 함)
        UserGroup userGroup = UserGroup.builder()
                .group(group)
                .user(user)
                .build();
        userGroupRepository.save(userGroup);

        // 완료 후 /group/list 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/group/list");
    }

    // 로그인된 유저를 반환하는 메서드
    private User getLoggedInUser(HttpServletRequest request) {
        // 세션에서 user를 가져오는 로직이 있다고 가정
        // 여기서는 임의의 User 반환
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setUserName("testUser");
        return user;
    }
}
