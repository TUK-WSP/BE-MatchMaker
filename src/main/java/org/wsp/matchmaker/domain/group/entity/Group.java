package org.wsp.matchmaker.domain.group.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.domain.recruitment.entity.Recruitment;
import org.wsp.matchmaker.domain.schedule.entity.Schedule;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;
import org.wsp.matchmaker.global.commonEntity.enums.Status;

@Entity
@Table(name = "mgroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group extends AuditEntity {

    @Id
    @UuidGenerator
    @Column(name = "group_id", updatable = false, nullable = false)
    private UUID groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_thumbnail_image_url")
    private String groupThumbnailImageUrl;

    @Column(name = "group_description")
    private String groupDescription;

    @Column(name = "group_status")
    private Status status;

    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Recruitment recruitment;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<UserGroup> groupUsers = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<SubGroup> subGroups = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Schedule> schedules = new ArrayList<>();

}
