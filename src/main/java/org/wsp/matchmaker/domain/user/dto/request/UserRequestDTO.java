package org.wsp.matchmaker.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportStatus;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportType;
import org.wsp.matchmaker.domain.user.entity.Report;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.global.commonEntity.enums.Gender;

public class UserRequestDTO {
    private UserRequestDTO() {
        throw new UnsupportedOperationException("UserRequestDTO는 인스턴스화할 수 없습니다.");
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseUserInfoRequestDTO {
        private String userId;
        private String userName;
        private String userEmail;
        private String userPassword;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterInfoRequestDTO extends BaseUserInfoRequestDTO {
        @NotNull
        private String userName;

        @NotNull
        @Email
        private String userEmail;

        @NotNull
        private String password;

        @NotNull
        private Gender gender;
    }


    @Builder
    @Value
    @Getter
    @AllArgsConstructor
    public static class UserHobbyRequestDTO {
        List<HobbyDTO> hobbies;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class HobbyDTO {
        private Long hobbyId;
        private String hobbyName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReportRequestDTO{
        private UUID userId;
        private UUID reportTargetId;
        private String reportContent;
        private ReportType reportType;
    }
}
