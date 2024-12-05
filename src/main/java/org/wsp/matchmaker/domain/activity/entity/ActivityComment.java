package org.wsp.matchmaker.domain.activity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityComment extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "comment_id", updatable = false, nullable = false)
    private UUID commentId;

    @Column(name = "comment_content", nullable = false)
    private String commentContent;

}
