package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementListResponseDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementUpdateRequestDto;
import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Company;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Long createAnnouncement(AnnouncementCreateRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("Company not found"));
        dto.setCompany(company);
        return announcementRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long updateAnnouncement(Long id, AnnouncementUpdateRequestDto dto) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        announcement.update(dto.getPosition(), dto.getReward(), dto.getContent(), dto.getSkill());
        return id;
    }

    @Transactional
    public Long deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        announcementRepository.delete(announcement);
        return id;
    }

    @Transactional(readOnly = true)
    public List<AnnouncementListResponseDto> getAllAnnouncements() {
        return announcementRepository.findAll().stream().map(AnnouncementListResponseDto::new).toList();
    }
}
