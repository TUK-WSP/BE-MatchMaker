package org.wsp.matchmaker.domain.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.wsp.matchmaker.domain.recruitment.entity.enums.FieldType;

@Entity
@Table(name = "recruitment_additional_fields")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentAdditionalField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id", updatable = false, nullable = false)
    private Long recruitmentAdditionalId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_type")
    private FieldType fieldType;

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private RecruitmentAnswer recruitmentAnswer;
}
