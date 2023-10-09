package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.ApplyRequestDto;
import com.example.wantedpreonboardingbackend.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/api/apply")
    public Long apply(@RequestBody ApplyRequestDto applyRequestDto) {
        return applyService.apply(applyRequestDto);
    }
}
