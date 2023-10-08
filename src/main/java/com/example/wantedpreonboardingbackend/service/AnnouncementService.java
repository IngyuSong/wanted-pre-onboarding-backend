package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.entity.Company;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Long createAnnouncement(AnnouncementCreateRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow();
        dto.setCompany(company);
        return announcementRepository.save(dto.toEntity()).getId();
    }
}
