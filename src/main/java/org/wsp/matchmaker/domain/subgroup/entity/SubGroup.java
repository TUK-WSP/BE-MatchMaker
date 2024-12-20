package org.wsp.matchmaker.domain.subgroup.entity;

import jakarta.persistence.*;
import lombok.*;
import org.wsp.matchmaker.domain.group.entity.Group;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subgroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubGroup {

    @Id
    @GeneratedValue(generator = "UUID")
    @org.hibernate.annotations.GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "subgroup_id", nullable = false, updatable = false)
    private UUID subGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    @Column(name = "subgroup_name", nullable = false)
    private String name; // 소모임 이름

    @Column(length = 1000)
    private String description; // 소모임 설명

    @Column(nullable = false)
    private String status; // 상태

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성일시

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정일시

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "subGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSubGroup> userSubGroups = new ArrayList<>(); // 소모임 멤버 목록

    @OneToMany(mappedBy = "subGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubActivity> activities = new ArrayList<>(); // 활동 목록
}