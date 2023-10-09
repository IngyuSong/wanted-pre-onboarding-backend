package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementDetailResponseDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementListResponseDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementUpdateRequestDto;
import com.example.wantedpreonboardingbackend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/announcement")
    public ResponseEntity<List<AnnouncementListResponseDto>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    @GetMapping("/api/announcement/search")
    public ResponseEntity<List<AnnouncementListResponseDto>> searchAnnouncements(@RequestParam String keyword) {
        return ResponseEntity.ok(announcementService.searchAnnouncements(keyword));
    }

    @GetMapping("/api/announcement/{id}")
    public ResponseEntity<AnnouncementDetailResponseDto> getAnnouncement(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.getAnnouncement(id));
    }

}
