package org.wsp.matchmaker.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.dto.response.UserResponseDTO;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

import java.util.UUID;

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

    //회원정보 가져오기
    @GetMapping("/myinfo")
    public ApiResponse<UserResponseDTO.UserRegisterResponseDTO> getUserInfo(HttpSession session) {
        try {
            // 세션에서 사용자 ID 가져오기
            UUID userId = (UUID) session.getAttribute("userId");

            if (userId == null) {
                throw new IllegalArgumentException("사용자가 로그인되어 있지 않습니다.");
            }

            // 사용자 정보 조회
            UserResponseDTO.UserRegisterResponseDTO userInfo = userService.getUserInfo(userId);
            return ApiResponse.onSuccess(userInfo);
        } catch (IllegalArgumentException e) {
            return ApiResponse.onFailure("ERR001", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("ERR500", "서버 오류가 발생했습니다. \n" + e.getMessage(), null);
        }
    }

}
