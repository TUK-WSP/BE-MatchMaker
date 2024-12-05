package org.wsp.matchmaker.domain.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.domain.admin.entity.enums.ActionType;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "log") // Changed from "admin" to "log"
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "log_id", updatable = false, nullable = false)
    private UUID logId;

    @Column(name = "target_id")
    private UUID targetId;

    @Column(name = "action_type")
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}