package org.wsp.matchmaker.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public User loginUser(String useremail, String password){
        User user = userRepository.findByUserEmail(useremail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    @Transactional
    public User registerUser(UserRegisterInfoRequestDTO requestDto){
        if (userRepository.existsByUserName(requestDto.getUserName())) {
            throw new RuntimeException("이미 존재하는 사용자 이름입니다.");
        }
        if (userRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new RuntimeException("이미 존재하는 사용자 이메일입니다.");
        }

        User newUser = User.builder()
                .userName(requestDto.getUserName())
                .userEmail(requestDto.getUserEmail())
                .password(requestDto.getPassword())
                .gender(requestDto.getGender())
                .build();
        return userRepository.save(newUser);
    }
}
