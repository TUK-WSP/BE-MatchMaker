package org.wsp.matchmaker.domain.user.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.group.entity.UserGroup;
import org.wsp.matchmaker.domain.group.repository.UserGroupRepository;
import org.wsp.matchmaker.domain.recruitment.entity.RecruitmentApplication;
import org.wsp.matchmaker.domain.recruitment.entity.repository.RecruitmentApplicationRepository;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.HobbyDTO;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserRegisterInfoRequestDTO;
import org.wsp.matchmaker.domain.user.entity.Hobby;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.entity.UserHobby;
import org.wsp.matchmaker.domain.user.repository.HobbyRepository;
import org.wsp.matchmaker.domain.user.repository.UserRepository;
import org.wsp.matchmaker.global.commonEntity.enums.Gender;
import org.wsp.matchmaker.global.commonEntity.enums.Status;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final UserGroupRepository userGroupRepository;
    private final RecruitmentApplicationRepository recruitmentApplicationRepository;


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

    // 사용자 정보 업데이트
    @Transactional
    public void updateUserInfo(UUID userId, String userName, String userEmail, String gender, Set<String> hobbies) {
        // userId로 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 사용자 정보 수정
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setGender(Gender.valueOf(gender));

        // 기존 취미 삭제
        user.getUserHobbies().clear();

        // 새로운 취미 추가
        for (String hobbyName : hobbies) {
            Hobby hobby = findOrCreateHobby(hobbyName);
            UserHobby userHobby = UserHobby.builder()
                    .user(user)
                    .hobby(hobby)
                    .build();
            user.getUserHobbies().add(userHobby);
        }

        // 사용자 정보 저장
        userRepository.save(user);
    }

    // 취미를 찾거나 생성 테스트용
    private Hobby findOrCreateHobby(String hobbyName) {
        return hobbyRepository.findByHobbyName(hobbyName)
                .orElseGet(() -> hobbyRepository.save(Hobby.builder()
                        .hobbyName(hobbyName)
                        .build()));
    }

    //회원의 모임 목록 가져오기
    public List<Group> getUserGroups(UUID userId) {
        List<UserGroup> userGroups = userGroupRepository.findByUser_UserId(userId);
        return userGroups.stream()
                .map(UserGroup::getGroup) // UserGroup에서 Group을 가져옴
                .collect(Collectors.toList());
    }

    // 사용자의 모집 신청 목록 가져오기
    public List<RecruitmentApplication> getUserApplications(UUID userId) {
        return recruitmentApplicationRepository.findByUser_UserId(userId);
    }

    // 모집 신청 취소하기
    public void cancelRecruitmentApplication(Long applicationId) {
        RecruitmentApplication application = recruitmentApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("신청을 찾을 수 없습니다."));

        // 신청 상태를 '삭제됨'으로 변경
        application.setApplicationStatus(Status.DELETED);

        // 상태 업데이트 후 저장
        recruitmentApplicationRepository.save(application);
    }



}
