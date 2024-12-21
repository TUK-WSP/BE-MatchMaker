package org.wsp.matchmaker.domain.user.service;

import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportStatus;
import org.wsp.matchmaker.domain.admin.entity.enums.ReportType;
import org.wsp.matchmaker.domain.group.repository.GroupRepository;
import org.wsp.matchmaker.domain.subgroup.repository.SubGroupRepository;
import org.wsp.matchmaker.domain.user.dto.response.UserResponseDTO;
import org.wsp.matchmaker.domain.user.entity.Report;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.HobbyDTO;
import org.wsp.matchmaker.domain.user.dto.request.UserRequestDTO.UserReportRequestDTO;
import org.wsp.matchmaker.domain.user.entity.Hobby;
import org.wsp.matchmaker.domain.user.entity.User;
import org.wsp.matchmaker.domain.user.repository.HobbyRepository;
import org.wsp.matchmaker.domain.user.repository.ReportRepository;
import org.wsp.matchmaker.domain.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final ReportRepository reportRepository;
    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final SubGroupRepository subGroupRepository;

    @Transactional
    public UserResponseDTO.UserReportResponseDTO userReport(UserReportRequestDTO requestDTO) {
        UUID userId = requestDTO.getUserId();
        UUID targetId = requestDTO.getReportTargetId();
        ReportType reportType = requestDTO.getReportType();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));

        if(!findTargetByReportType(targetId, reportType)){
            throw new NoSuchElementException("대상을 찾을 수 없습니다.");
        }


        Report report = Report.builder()
                .reportTargetId(requestDTO.getReportTargetId())
                .reportContent(requestDTO.getReportContent())
                .reportStatus(ReportStatus.UNDER_REVIEW)
                .reportType(reportType)
                .build();

        user.addReport(report);
        reportRepository.save(report);

        return  UserResponseDTO.UserReportResponseDTO.builder()
                .targetId(targetId)
                .userId(userId)
                .build();
    }

    private Hobby findOrCreateHobby(HobbyDTO hobbyDTO) {
        if (hobbyDTO.getHobbyId() != null) {
            return hobbyRepository.findById(hobbyDTO.getHobbyId())
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 취미 ID입니다: " + hobbyDTO.getHobbyId()));
        } else if (hobbyDTO.getHobbyName() != null && !hobbyDTO.getHobbyName().isEmpty()) {
            return hobbyRepository.findByHobbyName(hobbyDTO.getHobbyName())
                    .orElseGet(() -> hobbyRepository.save(Hobby.builder()
                            .hobbyName(hobbyDTO.getHobbyName())
                            .build()));
        } else {
            throw new IllegalArgumentException("취미 정보가 올바르지 않습니다.");
        }
    }

    private boolean findTargetByReportType(UUID targetId, ReportType reportType){
        if (reportType == ReportType.USER){
            boolean found = userRepository.existsByUserId(targetId);
            return found;
        }
        if (reportType == ReportType.GROUP){
            return groupRepository.existsByGroupId(targetId);
        }
        if (reportType == ReportType.SUBGROUP){
            return subGroupRepository.existsBySubGroupId(targetId);
        }
        return false;
    }
}
