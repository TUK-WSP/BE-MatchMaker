package org.wsp.matchmaker.domain.subgroup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wsp.matchmaker.domain.subgroup.dto.SubGroupRequest;
import org.wsp.matchmaker.domain.subgroup.service.SubGroupService;

@Controller
@RequestMapping("/subgroup")
@RequiredArgsConstructor
public class SubGroupCreateController {

    private final SubGroupService subGroupService;

    @GetMapping("/sgcreate")
    public String createPage() {
        return "sgcreate"; // sgcreate.jsp와 매핑
    }

    @PostMapping("/create")
    public String createSubGroup(@ModelAttribute SubGroupRequest request) {
        subGroupService.createSubGroup(request); // 소모임 생성 처리
        return "redirect:/subgroup"; // 생성 후 메인 페이지로 리다이렉트
    }
}