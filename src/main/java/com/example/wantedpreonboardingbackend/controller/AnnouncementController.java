package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/api/announcement")
    public ResponseEntity<Long> createAnnouncement(@RequestBody AnnouncementCreateRequestDto dto) {
        return ResponseEntity.ok(announcementService.createAnnouncement(dto));
    }
}
