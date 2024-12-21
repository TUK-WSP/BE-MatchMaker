package org.wsp.matchmaker.domain.subgroup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wsp.matchmaker.domain.subgroup.dto.ActivityDetailResponse;
import org.wsp.matchmaker.domain.subgroup.dto.ActivityRequest;
import org.wsp.matchmaker.domain.subgroup.entity.SubActivity;
import org.wsp.matchmaker.domain.subgroup.repository.SubActivityRepository;
import org.wsp.matchmaker.domain.subgroup.repository.SubGroupRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final SubActivityRepository subActivityRepository;
    private final SubGroupRepository subGroupRepository;

    // 활동 기록 조회
    public List<ActivityDetailResponse> getActivities(UUID subgroupId) {
        // 해당 소모임의 활동 기록 조회
        return subActivityRepository.findBySubGroup_SubGroupId(subgroupId).stream()
                .map(activity -> ActivityDetailResponse.builder()
                        .activityId(activity.getActivityId())
                        .title(activity.getTitle())
                        .content(activity.getContent())
                        .createdAt(activity.getCreatedAt())
                        .build())
                .collect(Collectors.toList()); // 반환되는 활동 기록 리스트
    }

    // 활동 기록 추가
    public ActivityDetailResponse addActivity(UUID subgroupId, ActivityRequest activityRequest) {
        SubActivity subActivity = new SubActivity();
        subActivity.setTitle(activityRequest.getTitle());
        subActivity.setContent(activityRequest.getContent());
        subActivity.setSubGroup(subGroupRepository.findById(subgroupId)
                .orElseThrow(() -> new RuntimeException("SubGroup not found")));

        subActivity = subActivityRepository.save(subActivity);

        return ActivityDetailResponse.builder()
                .activityId(subActivity.getActivityId())
                .title(subActivity.getTitle())
                .content(subActivity.getContent())
                .createdAt(subActivity.getCreatedAt())
                .build();
    }
}