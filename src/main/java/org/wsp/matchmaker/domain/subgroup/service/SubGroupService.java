package org.wsp.matchmaker.domain.subgroup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.subgroup.dto.SubGroupRequest;
import org.wsp.matchmaker.domain.subgroup.dto.SubGroupResponse;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.domain.subgroup.repository.SubGroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubGroupService {


    private final SubGroupRepository subGroupRepository; // 의존성 주입

    public List<SubGroup> getAllSubGroups() {
        return subGroupRepository.findAll(); // JPA의 기본 메서드 활용
    }
    // 소모임 이름으로 검색
    public List<SubGroup> searchSubGroupsByName(String name) {
        return subGroupRepository.findByNameContainingIgnoreCase(name); // 이름에 검색어 포함 여부로 검색
    }

    public SubGroupResponse createSubGroup(SubGroupRequest request) {
        // SubGroup 엔티티 생성
        SubGroup subGroup = SubGroup.builder()
                .group(Group.builder().groupId(request.getGroupId()).build()) // Group 객체 매핑
                .name(request.getSubgroupName())
                .description(request.getDescription())
                .status("활성")
                .build();

        // DB 저장
        SubGroup savedSubGroup = subGroupRepository.save(subGroup);

        // 응답 DTO 생성
        return SubGroupResponse.builder()
                .subgroupId(savedSubGroup.getSubGroupId())
                .groupId(savedSubGroup.getGroup().getGroupId())
                .subgroupName(savedSubGroup.getName())
                .description(savedSubGroup.getDescription())
                .status(savedSubGroup.getStatus())
                .createdAt(savedSubGroup.getCreatedAt())
                .updatedAt(savedSubGroup.getUpdatedAt())
                .build();
    }
}