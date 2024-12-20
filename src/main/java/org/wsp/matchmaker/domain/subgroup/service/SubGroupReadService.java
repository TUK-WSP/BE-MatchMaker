package org.wsp.matchmaker.domain.subgroup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wsp.matchmaker.domain.subgroup.dto.SubGroupReadResponse;
import org.wsp.matchmaker.domain.subgroup.dto.MemberReadResponse;
import org.wsp.matchmaker.domain.subgroup.dto.ActivityDetailResponse;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.domain.subgroup.repository.SubGroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubGroupReadService {

    private final SubGroupRepository subGroupRepository;

    // 전체 소모임 조회
    public List<SubGroupReadResponse> getAllSubGroups() {
        return subGroupRepository.findAll().stream()
                .map(subGroup -> SubGroupReadResponse.builder()
                        .subgroupId(subGroup.getSubGroupId()) // UUID 그대로 전달
                        .name(subGroup.getName()) // 정확한 필드명 사용
                        .description(subGroup.getDescription())
                        .status(subGroup.getStatus())
                        .createdAt(subGroup.getCreatedAt())
                        .updatedAt(subGroup.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 검색 기능
    public List<SubGroupReadResponse> searchSubGroups(String query) {
        return subGroupRepository.findByNameContainingIgnoreCase(query).stream()
                .map(subGroup -> SubGroupReadResponse.builder()
                        .subgroupId(subGroup.getSubGroupId()) // UUID 그대로 전달
                        .name(subGroup.getName()) // 정확한 필드명 사용
                        .description(subGroup.getDescription())
                        .status(subGroup.getStatus())
                        .createdAt(subGroup.getCreatedAt())
                        .updatedAt(subGroup.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 소모임 상세 조회
    public SubGroupReadResponse getSubGroupInfo(UUID subGroupId) {
        // 소모임 데이터 조회
        SubGroup subGroup = subGroupRepository.findById(subGroupId)
                .orElseThrow(() -> new RuntimeException("SubGroup not found"));

        // 멤버 정보 생성
        List<MemberReadResponse> members = Optional.ofNullable(subGroup.getUserSubGroups())
                .orElse(List.of())
                .stream()
                .map(userSubGroup -> MemberReadResponse.builder()
                        .userId(userSubGroup.getUser().getUserId()) // User ID 매핑
                        .userName(userSubGroup.getUser().getUserName()) // User 이름 매핑
                        .joinedDate(userSubGroup.getJoinedDate()) // 가입 날짜 매핑
                        .build())
                .collect(Collectors.toList());

        // 활동 정보 생성
        List<ActivityDetailResponse> activities = Optional.ofNullable(subGroup.getActivities())
                .orElse(List.of())
                .stream()
                .map(activity -> ActivityDetailResponse.builder()
                        .activityId(activity.getActivityId()) // 활동 ID 매핑
                        .title(activity.getTitle()) // 활동 제목 매핑
                        .createdAt(activity.getCreatedAt()) // 생성 날짜 매핑
                        .build())
                .collect(Collectors.toList());

        // 응답 객체 생성 및 반환
        return SubGroupReadResponse.builder()
                .subgroupId(subGroup.getSubGroupId()) // UUID 그대로 전달
                .name(subGroup.getName()) // 정확한 필드명 사용
                .description(subGroup.getDescription()) // 소모임 설명
                .groupName(Optional.ofNullable(subGroup.getGroup()).map(group -> group.getGroupName()).orElse(null)) // 상위 그룹 이름
                .status(subGroup.getStatus()) // 상태
                .members(members) // 멤버 목록
                .activities(activities) // 활동 목록
                .build();
    }
}