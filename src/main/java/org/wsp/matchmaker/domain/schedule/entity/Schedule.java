package org.wsp.matchmaker.domain.schedule.entity;

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
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule extends AuditEntity{
    @Id
    @UuidGenerator
    @Column(name = "schedule_id", updatable = false, nullable = false)
    private UUID scheduleId;

    @Column(name = "schedule_name", nullable = false)
    private String scheduleName;

    @Column(name = "schedule_date", nullable = false)
    private Date scheduledDate;

    @Column(name = "schedule_location")
    private String scheduledLocation;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<UserSchedule> userSchedules = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subgroup_id", referencedColumnName = "subgroup_id", nullable = false)
    private SubGroup subGroup;
}
