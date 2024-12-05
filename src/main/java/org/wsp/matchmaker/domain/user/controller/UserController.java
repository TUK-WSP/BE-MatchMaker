package org.wsp.matchmaker.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ApiResponse<Void> registerUser(@RequestBody @Valid UserRegisterInfoRequestDTO registerInfo) {
        try{
            userService.registerUser(registerInfo);
            return ApiResponse.onSuccess(null);
        }catch(IllegalArgumentException e){
            return ApiResponse.onFailure("ERR001", e.getMessage(), null);
        }catch(Exception e){
            return ApiResponse.onFailure("ERR500", "서버 오류가 발생했습니다. \n" + e, null);
        }
    }
}
