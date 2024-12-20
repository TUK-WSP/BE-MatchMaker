package org.wsp.matchmaker.domain.user.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

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
    public static class UserRegisterInfoRequestDTO extends BaseUserInfoRequestDTO{
        private UserHobbyRequestDTO userHobby;
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

}
