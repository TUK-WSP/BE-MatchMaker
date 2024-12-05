package org.wsp.matchmaker.domain.recruitment.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.*;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;
import org.wsp.matchmaker.global.commonEntity.enums.Status;

@Entity
@Table(name = "recruitment_application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentApplication extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", updatable = false, nullable = false)
    private Long applicationId;

    @Column(name = "application_status", updatable = false, nullable = false)
    private Status applicationStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<RecruitmentAnswer> additionalFields = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;
}
