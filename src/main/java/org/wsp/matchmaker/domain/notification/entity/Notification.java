package org.wsp.matchmaker.domain.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.notification.entity.enums.ReadStatus;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", updatable = false, nullable = false)
    private Long notificationId;

    @Column(name = "notification_content")
    private String notificationContent;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "read_status")
    private ReadStatus readStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
