package org.wsp.matchmaker.domain.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wsp.matchmaker.domain.recruitment.dto.RecruitmentCreateRequest;
import org.wsp.matchmaker.domain.recruitment.dto.RecruitmentResponse;
import org.wsp.matchmaker.domain.recruitment.service.RecruitmentService;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitment")
    public RecruitmentResponse createRecruitment(@RequestBody RecruitmentCreateRequest request) {
        // request: {"groupId", "recruitmentTitle", "recruitmentDescription", "recruitmentDeadline", "recruitmentNumber", "recruitmentStatus"}
        return recruitmentService.createRecruitment(request);
    }
}
