package org.wsp.matchmaker.domain.subgroup.entity;

import jakarta.persistence.*;
import lombok.*;
import org.wsp.matchmaker.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_subgroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User 엔티티 참조

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subgroup_id", nullable = false)
    private SubGroup subGroup;

    @Column(name = "joined_date", nullable = false)
    private LocalDateTime joinedDate;
}