package org.wsp.matchmaker.domain.subgroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wsp.matchmaker.domain.subgroup.dto.SubGroupReadResponse;
import org.wsp.matchmaker.domain.subgroup.service.SubGroupReadService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/subgroup/read")
public class SubGroupReadController {

    private final SubGroupReadService subGroupReadService;

    public SubGroupReadController(SubGroupReadService subGroupReadService) {
        this.subGroupReadService = subGroupReadService;
    }

    // 소모임 목록 조회 (검색 기능 포함)
    @GetMapping("/sglist")
    public String getSubGroups(Model model, @RequestParam(value = "query", required = false) String query) {
        List<SubGroupReadResponse> subGroups;

        if (query != null && !query.isEmpty()) {
            // 검색된 소모임만 가져오기
            subGroups = subGroupReadService.searchSubGroups(query);
            model.addAttribute("message", subGroups.size() + "개의 소모임이 검색되었습니다.");
        } else {
            // 검색어가 없으면 전체 소모임 조회
            subGroups = subGroupReadService.getAllSubGroups();
            model.addAttribute("message", subGroups.size() + "개의 소모임이 있습니다.");
        }

        model.addAttribute("subGroups", subGroups);
        model.addAttribute("query", query); // 검색어를 유지하여 표시
        return "sglist"; // sglist.jsp 파일 렌더링
    }

    // 소모임 상세 조회
    @GetMapping("/sgdetail/{subGroupId}")
    public String getSubGroupDetail(@PathVariable UUID subGroupId, Model model) {
        SubGroupReadResponse response = subGroupReadService.getSubGroupInfo(subGroupId);
        model.addAttribute("subGroup", response);
        return "sgdetail"; // sgdetail.jsp 파일 렌더링
    }
}