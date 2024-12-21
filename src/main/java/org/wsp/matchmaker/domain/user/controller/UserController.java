package org.wsp.matchmaker.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.dto.response.UserResponseDTO;
import org.wsp.matchmaker.domain.user.entity.Report;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.repository.UserRepository;
import org.wsp.matchmaker.domain.user.service.UserService;
import org.wsp.matchmaker.global.response.ApiResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/report")
    public ApiResponse<UserResponseDTO.UserReportResponseDTO> report(@RequestBody @Valid UserRequestDTO.UserReportRequestDTO userReportRequestDTO) {
        UserResponseDTO.UserReportResponseDTO userResponseDTO = userService.userReport(userReportRequestDTO);
        return ApiResponse.onSuccess(userResponseDTO);
    }
}
