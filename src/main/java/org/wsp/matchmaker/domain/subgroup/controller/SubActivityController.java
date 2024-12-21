package org.wsp.matchmaker.domain.subgroup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wsp.matchmaker.domain.subgroup.dto.ActivityRequest;
import org.wsp.matchmaker.domain.subgroup.service.ActivityService;

import java.util.UUID;

@Controller
@RequestMapping("/subgroup/activity")
@RequiredArgsConstructor
public class SubActivityController {

    private final ActivityService activityService;

    // 활동 기록 목록 조회 (소모임 ID 포함)
    @GetMapping("/{subgroupId}/activities")
    public String getActivities(@PathVariable UUID subgroupId, Model model) {
        model.addAttribute("activities", activityService.getActivities(subgroupId));
        model.addAttribute("subgroupId", subgroupId);
        return "sgactivity";  // sgactivity.jsp 페이지로 이동
    }

    // 활동 기록 추가 화면
    @GetMapping("/{subgroupId}/activities/create")
    public String showAddActivityForm(@PathVariable UUID subgroupId, Model model) {
        model.addAttribute("subgroupId", subgroupId);
        return "subactivityCreate";  // subactivityCreate.jsp 페이지로 이동
    }

    // 활동 기록 추가 처리
    @PostMapping("/{subgroupId}/activities")
    public String addActivity(@PathVariable UUID subgroupId, @ModelAttribute ActivityRequest activityRequest) {
        activityService.addActivity(subgroupId, activityRequest);
        return "redirect:/subgroup/activity/" + subgroupId + "/activities";  // 활동 목록 페이지로 리다이렉트
    }
}