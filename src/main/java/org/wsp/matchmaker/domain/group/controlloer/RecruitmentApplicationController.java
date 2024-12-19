package org.wsp.matchmaker.domain.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 들어오는 값: { "application_id": applicationId }
// 나가는 값:
// {
//   "recruitmentId": "...",
//   "recruitmentApplication": {
//     "application_id": "...",
//     "application_status": "...",
//     "user_id": "...",
//     "application": "..."
//   }
// }

@RestController
@RequiredArgsConstructor
public class RecruitmentApplicationController {

    private final RecruitmentApplicationService recruitmentApplicationService;

    @PostMapping("/recruitment/application")
    public RecruitmentApplicationResponse getApplication(@RequestBody RecruitmentApplicationRequest request) {
        // application_id로 신청 정보 조회
        return recruitmentApplicationService.findByApplicationId(request.getApplication_id());
    }
}
