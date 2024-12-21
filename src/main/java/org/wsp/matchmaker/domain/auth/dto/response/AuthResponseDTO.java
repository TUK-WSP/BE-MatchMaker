package org.wsp.matchmaker.domain.auth.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wsp.matchmaker.global.commonEntity.enums.Gender;

public class AuthResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponseDTO {
        private String userId;
        private String userEmail;
        private Gender gender;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResponseDTO {
        private String userEmail;
        private String userName;
        private Gender gender;
    }
}
