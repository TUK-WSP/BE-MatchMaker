package org.wsp.matchmaker.domain.recruitment.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import lombok.*;

@Entity
@Table(name = "recruitment_answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentAnswer {
    @Id
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "answer_content")
    private String answerContent;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private RecruitmentApplication application;

    @OneToMany(mappedBy = "recruitmentAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<RecruitmentAdditionalField> recruitmentAdditionalFields = new ArrayList<>();
}
