package org.wsp.matchmaker.domain.subgroup.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.domain.group.entity.Group;

@Entity
@Table(name = "subgroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubGroup {
    @Id
    @UuidGenerator
    @Column(name = "subgroup_id", nullable = false)
    private UUID subGroupId;

    @Column(name = "subgroup_name", nullable = false)
    private String name; // 소모임 이름

    @OneToMany(mappedBy = "subgroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<UserSubGroup> userSubGroups = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false) // 외래 키 설정
    private Group group; // Group 필드 정의

}
