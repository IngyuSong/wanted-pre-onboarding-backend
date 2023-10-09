package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementDetailResponseDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementListResponseDto;
import com.example.wantedpreonboardingbackend.dto.AnnouncementUpdateRequestDto;
import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Company;
import com.example.wantedpreonboardingbackend.entity.QAnnouncement;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<AnnouncementListResponseDto> searchAnnouncements(String keyword) {
        QAnnouncement announcement = QAnnouncement.announcement;
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.or(announcement.company.name.contains(keyword));
        predicate.or(announcement.company.country.eq(keyword));
        predicate.or(announcement.company.region.eq(keyword));
        predicate.or(announcement.position.contains(keyword));
        predicate.or(announcement.skill.contains(keyword));

        List<Announcement> announcements = (List<Announcement>) announcementRepository.findAll(predicate);

        return announcements.stream()
                .map(AnnouncementListResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public AnnouncementDetailResponseDto getAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        AnnouncementDetailResponseDto dto = new AnnouncementDetailResponseDto(announcement);
        List<Long> announcementIdList = new ArrayList<>(announcementRepository.findAllByCompanyId(announcement.getCompany().getId()).stream().map(Announcement::getId).toList());
        announcementIdList.remove(id);
        dto.setAnnouncementIdList(announcementIdList);
        return dto;
    }
}
