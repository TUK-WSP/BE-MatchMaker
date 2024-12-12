package org.wsp.matchmaker.domain.user.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.HobbyDTO;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
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
}
