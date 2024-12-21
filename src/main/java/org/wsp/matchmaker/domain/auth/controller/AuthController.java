package org.wsp.matchmaker.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wsp.matchmaker.domain.auth.dto.request.AuthRequestDTO;
import org.wsp.matchmaker.domain.auth.dto.response.AuthResponseDTO;
import org.wsp.matchmaker.domain.auth.service.AuthService;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO.LoginResponseDTO> loginUser(@RequestBody @Valid AuthRequestDTO.LoginRequestDTO requestDTO) {
        try{
            String userEmail = requestDTO.getUserEmail();
            String userPassword = requestDTO.getUserPassword();
            System.out.println(userEmail+"\n"+userPassword);

            User user = authService.loginUser(userEmail, userPassword);
            System.out.println(user.getUserName());
            AuthResponseDTO.LoginResponseDTO builder = AuthResponseDTO.LoginResponseDTO.builder()
                    .userId(user.getUserEmail())
                    .userEmail(user.getUserEmail())
                    .gender(user.getGender())
                    .build();
            System.out.println(builder.toString());
            return ApiResponse.onSuccess(builder);


        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ApiResponse.onFailure("ERR002", e.getMessage(), null);
        }
    }

    @PostMapping("/register")
    public ApiResponse<AuthResponseDTO.RegisterResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO.UserRegisterInfoRequestDTO requestDTO) {
        try{
            User newUser = authService.registerUser(requestDTO);
            AuthResponseDTO.RegisterResponseDTO builder = AuthResponseDTO.RegisterResponseDTO.builder()
                    .userName(newUser.getUserName())
                    .userEmail(newUser.getUserEmail())
                    .gender(newUser.getGender())
                    .build();

            return ApiResponse.onSuccess(builder);
        }catch (Exception e){
            return ApiResponse.onFailure("ERR002", e.getMessage(), null);
        }
    }
}
