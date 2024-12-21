package org.wsp.matchmaker.domain.user.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterResponseDTO {
        private UUID userId;
        private String userName;
        private String userEmail;
        private Integer hobbyCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReportResponseDTO {
        private UUID userId;
        private UUID targetId;
    }
}
