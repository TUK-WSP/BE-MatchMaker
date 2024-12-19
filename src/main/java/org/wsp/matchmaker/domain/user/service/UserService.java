package org.wsp.matchmaker.domain.user.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.HobbyDTO;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.dto.response.UserResponseDTO;
import org.wsp.matchmaker.domain.user.entity.Hobby;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.entity.UserHobby;
import org.wsp.matchmaker.domain.user.repository.HobbyRepository;
import org.wsp.matchmaker.domain.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    @Transactional
    public void registerUser(UserRegisterInfoRequestDTO requestDto){
        String userName = requestDto.getUserName();
        String userPassword = requestDto.getUserPassword();
        String userEmail = requestDto.getUserEmail();

        if (userRepository.existsByUserName(userName))
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");

        if (userRepository.existsByUserEmail(userEmail))
            throw new IllegalArgumentException("이미 등록된 이메일 입니다.");

        List<HobbyDTO> hobbyDTOs = requestDto.getUserHobby().getHobbies();
        List<Hobby> hobbies = hobbyDTOs.stream()
                .map(this::findOrCreateHobby)
                .toList();

        User user = User.builder()
                .userName(userName)
                .userEmail(userEmail)
                .password(userPassword)
                .build();

        hobbies.forEach(hobby -> {
            UserHobby userHobby = UserHobby.builder()
                    .user(user)
                    .hobby(hobby)
                    .build();
            user.getUserHobbies().add(userHobby);
        });
        userRepository.save(user);
    }

    private Hobby findOrCreateHobby(HobbyDTO hobbyDTO) {
        if (hobbyDTO.getHobbyId() != null) {
            return hobbyRepository.findById(hobbyDTO.getHobbyId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 취미 ID입니다: " + hobbyDTO.getHobbyId()));
        } else if (hobbyDTO.getHobbyName() != null && !hobbyDTO.getHobbyName().isEmpty()) {
            return hobbyRepository.findByHobbyName(hobbyDTO.getHobbyName())
                    .orElseGet(() -> hobbyRepository.save(Hobby.builder()
                            .hobbyName(hobbyDTO.getHobbyName())
                            .build()));
        } else {
            throw new IllegalArgumentException("취미 정보가 올바르지 않습니다.");
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDTO.UserRegisterResponseDTO getUserInfo(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));

        // 취미 개수 계산
        int hobbyCount = user.getUserHobbies() != null ? user.getUserHobbies().size() : 0;

        // 응답 DTO 반환
        return UserResponseDTO.UserRegisterResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .hobbyCount(hobbyCount)
                .build();
    }


    // 사용자 정보 수정
    @Transactional
    public void updateUser(UUID userId, UserRegisterInfoRequestDTO requestDto) {
        // 기존 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));

        // 사용자 정보 수정
        if (requestDto.getUserName() != null && !requestDto.getUserName().isEmpty()) {
            if (userRepository.existsByUserName(requestDto.getUserName()))
                throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
            user.setUserName(requestDto.getUserName());
        }

        if (requestDto.getUserEmail() != null && !requestDto.getUserEmail().isEmpty()) {
            if (userRepository.existsByUserEmail(requestDto.getUserEmail()))
                throw new IllegalArgumentException("이미 등록된 이메일입니다.");
            user.setUserEmail(requestDto.getUserEmail());
        }

        if (requestDto.getUserPassword() != null && !requestDto.getUserPassword().isEmpty()) {
            user.setPassword(requestDto.getUserPassword());
        }

        // 취미 정보 수정
        List<HobbyDTO> hobbyDTOs = requestDto.getUserHobby().getHobbies();
        if (hobbyDTOs != null && !hobbyDTOs.isEmpty()) {
            // 기존 취미 삭제
            user.getUserHobbies().clear();

            // 새로운 취미 추가
            List<Hobby> hobbies = hobbyDTOs.stream()
                    .map(this::findOrCreateHobby)
                    .toList();

            hobbies.forEach(hobby -> {
                UserHobby userHobby = UserHobby.builder()
                        .user(user)
                        .hobby(hobby)
                        .build();
                user.getUserHobbies().add(userHobby);
            });
        }

        // 수정된 사용자 정보 저장
        userRepository.save(user);
    }

}
