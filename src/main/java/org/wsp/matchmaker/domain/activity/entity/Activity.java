package org.wsp.matchmaker.domain.activity.entity;

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
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "activity_id", updatable = false, nullable = false)
    private UUID activityId;

    @Column(name = "activity_title", nullable = false)
    private String activityTitle;

    @Column(name = "activity_content", nullable = false)
    private String activityContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subgroup_id")
    private SubGroup subGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private ArrayList<ActivityImage> activityImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private ArrayList<ActivityComment> activityComments = new ArrayList<>();
}
