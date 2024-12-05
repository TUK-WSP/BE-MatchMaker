package org.wsp.matchmaker.domain.admin.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
import org.wsp.matchmaker.domain.admin.entity.enums.AdminRole;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;

@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "admin_id", updatable = false, nullable = false)
    private UUID adminId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "password")
    private String password;

    @Column(name = "admin_role")
    private AdminRole adminRole;

    @OneToMany(mappedBy ="admin", cascade = CascadeType.ALL)
    private ArrayList<AdminReport> adminRoles = new ArrayList<>();

    @OneToMany(mappedBy ="admin")
    private ArrayList<Log> logs = new ArrayList<>();
}
