package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementUpdateRequestDto;
import com.example.wantedpreonboardingbackend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/api/announcement")
    public ResponseEntity<Long> createAnnouncement(@RequestBody AnnouncementCreateRequestDto dto) {
        return ResponseEntity.ok(announcementService.createAnnouncement(dto));
    }

    @PutMapping("/api/announcement/{id}")
    public ResponseEntity<Long> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementUpdateRequestDto dto) {
        return ResponseEntity.ok(announcementService.updateAnnouncement(id, dto));
    }

    @DeleteMapping("/api/announcement/{id}")
    public ResponseEntity<Long> deleteAnnouncement(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.deleteAnnouncement(id));
    }

}
