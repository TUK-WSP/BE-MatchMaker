package com.example.controller;

import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.group.entity.Status;
import org.wsp.matchmaker.domain.group.entity.UserGroup;
import org.wsp.matchmaker.domain.group.repository.GroupRepository;
import org.wsp.matchmaker.domain.group.repository.UserGroupRepository;
import org.wsp.matchmaker.domain.user.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/group/create")
public class GroupCreateController extends HttpServlet {

    private GroupRepository groupRepository;
    private UserGroupRepository userGroupRepository;

    @Override
    public void init() {
        groupRepository = new GroupRepository();  // 실제로는 스프링에서 자동 주입 사용
        userGroupRepository = new UserGroupRepository();  // UserGroup Repository 추가
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 가져오기
        String groupName = request.getParameter("group_name");
        String groupDescription = request.getParameter("group_description");
        String groupStatus = request.getParameter("group_status");
        String groupThumbnailImageUrl = request.getParameter("group_thumbnail_image_url");

        // Group 객체 생성
        Group group = Group.builder()
                .groupName(groupName)
                .groupDescription(groupDescription)
                .status(Status.valueOf(groupStatus))  // groupStatus는 "active"나 "inactive" 값
                .groupThumbnailImageUrl(groupThumbnailImageUrl)
                .build();

        // 그룹을 저장
        groupRepository.save(group);

        // 그룹에 사용자를 추가 (예시: 이미 로그인된 사용자)
        User user = getLoggedInUser();  // 예시: 현재 로그인된 사용자 정보를 가져오는 방법
        UserGroup userGroup = UserGroup.builder()
                .group(group)
                .user(user)
                .build();

        // UserGroup 객체를 저장하여 사용자가 그룹에 속하도록 설정
        userGroupRepository.save(userGroup);

        // 완료 후 다른 페이지로 리다이렉트 (예: 목록 페이지)
        response.sendRedirect("/group/list");
    }

    // 예시로 현재 로그인된 사용자 정보를 가져오는 메서드 (실제 구현은 로그인 세션을 통해 얻어야 함)
    private User getLoggedInUser() {
        // 실제로는 세션에서 로그인 정보를 가져와야 합니다.
        return new User();  // 예시 코드
    }
}
