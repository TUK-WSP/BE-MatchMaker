package org.wsp.matchmaker.domain.user.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.wsp.matchmaker.domain.notification.entity.Notification;
import org.wsp.matchmaker.domain.recruitment.entity.RecruitmentApplication;
import org.wsp.matchmaker.global.commonEntity.AuditEntity;
import org.wsp.matchmaker.global.commonEntity.enums.Gender;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    private String userName;
    private String userEmail;
    private String password;
    private Gender gender;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBadge> userBadges = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserHobby> userHobbies = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<RecruitmentApplication> recruitmentApplications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Notification> notifications = new ArrayList<>();

}
