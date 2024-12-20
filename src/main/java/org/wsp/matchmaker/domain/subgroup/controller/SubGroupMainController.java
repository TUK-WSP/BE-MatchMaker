package org.wsp.matchmaker.domain.subgroup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;
import org.wsp.matchmaker.domain.subgroup.service.SubGroupService;

import java.util.List;

@Controller
@RequestMapping("/subgroup")
@RequiredArgsConstructor
public class SubGroupMainController {

    private final SubGroupService subGroupService;

    @GetMapping
    public String subgroupMain() {
        return "subgroupmain"; // subgroupmain.jsp 렌더링
    }

    @GetMapping("/sglist")
    public String subgroupList(@RequestParam(value = "search", required = false)String search, Model model) {
        // 서비스에서 소모임 목록 가져오기
        List<SubGroup> subGroups = subGroupService.getAllSubGroups();
        // 검색어가 있을 경우 검색된 소모임 반환, 없으면 전체 소모임 반환
        if (search != null && !search.isEmpty()) {
            subGroups = subGroupService.searchSubGroupsByName(search);
            model.addAttribute("searchTerm", search);
        } else {
            subGroups = subGroupService.getAllSubGroups();
        }

        model.addAttribute("subGroups", subGroups);
        return "sglist"; // JSP 파일 렌더링
    }
}