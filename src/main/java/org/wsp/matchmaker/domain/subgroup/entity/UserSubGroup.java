package org.wsp.matchmaker.domain.subgroup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import org.wsp.matchmaker.domain.user.entity.User;

@Entity
@Table(name = "user_subgroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubGroup {
    @Id
    @UuidGenerator
    private UUID subGroupId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subgroup_id", nullable = false)
    private SubGroup subgroup;
}
