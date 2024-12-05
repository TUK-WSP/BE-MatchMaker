package org.wsp.matchmaker.domain.recruitment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
import org.wsp.matchmaker.global.commonEntity.AuditEntity;
import org.wsp.matchmaker.global.commonEntity.enums.Status;

@Entity
@Table(name = "recruitment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recruitment extends AuditEntity {

    @Id
    @UuidGenerator
    @Column(name = "recruitment_id", nullable = false)
    private UUID recruitmentId;

    @Column(name = "recruitment_title", nullable = false)
    private String title;

    @Column(name = "recruitment_description", nullable = false)
    private String description;

    @Column(name = "recruitment_deadline", nullable = false)
    private Date deadline;

    @Column(name = "recruitment_recruitment_number", nullable = false)
    private Integer recruitmentNumber;

    @Column(name = "recruitment_status", nullable = false)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false, unique = true)
    private Group group;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<RecruitmentAdditionalField> additionalFields = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ArrayList<RecruitmentImage> recruitmentImages = new ArrayList<>();

}