package org.wsp.matchmaker.domain.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wsp.matchmaker.domain.recruitment.dto.RecruitmentAnswerRequest;
import org.wsp.matchmaker.domain.recruitment.dto.RecruitmentAnswerResponse;
import org.wsp.matchmaker.domain.recruitment.service.RecruitmentAnswerService;

@RestController
@RequiredArgsConstructor
public class RecruitmentAnswerController {

    private final RecruitmentAnswerService recruitmentAnswerService;

    @PostMapping("/recruitment/answer")
    public RecruitmentAnswerResponse getAnswer(@RequestBody RecruitmentAnswerRequest request) {
        // request: {"answer_id":"..."}
        // answer_id를 이용해 Service에서 userGroups 조회
        return recruitmentAnswerService.findByAnswerId(request.getAnswer_id());
    }
}
