package org.wsp.matchmaker.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.wsp.matchmaker.domain.admin.entity.AdminReport;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportStatus;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportType;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "report_id", updatable = false, nullable = false)
    private UUID reportId;

    @Column(name="report_target_id", updatable = false, nullable = false)
    private UUID reportTargetId;

    @Column(name = "report_content", nullable = false)
    private String reportContent;

    @Column(name = "report_status")
    private ReportStatus reportStatus;

    @Column(name = "report_type")
    private ReportType reportType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany
    @JoinColumn(name="report_id")
    private ArrayList<AdminReport> adminReport = new ArrayList<>();
}
