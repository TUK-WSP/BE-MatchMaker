package org.wsp.matchmaker.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.entity.Hobby;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.repository.UserRepository;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.commonEntity.enums.Gender;
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
