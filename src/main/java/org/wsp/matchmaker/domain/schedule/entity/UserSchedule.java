package org.wsp.matchmaker.domain.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.global.commonEntity.enums.Status;

@Entity
@Table(name = "user_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_user_id", updatable = false, nullable = false)
    private Long scheduleUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(name = "status", nullable = false)
    private Status status;
}
