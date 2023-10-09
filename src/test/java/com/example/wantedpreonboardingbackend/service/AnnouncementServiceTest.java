package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.AnnouncementListResponseDto;
import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AnnouncementServiceTest {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    public void tearDown() {
        announcementRepository.deleteAll();
    }

    @Test
    @DisplayName("채용공고 검색 테스트")
    void testSearchAnnouncements() {
        // given
        List<Announcement> announcementList = new ArrayList<>();
        announcementRepository.save(Announcement.builder()
                .position("백엔드 주니어 개발자")
                .reward(1500000)
                .skill("Python")
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .company(companyRepository.findById(1L).orElseThrow())
                .build());
        announcementRepository.save(Announcement.builder()
                .position("백엔드 주니어 개발자")
                .reward(1500000)
                .skill("Java")
                .content("원티드코리아에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .company(companyRepository.findById(2L).orElseThrow())
                .build());
        announcementRepository.save(Announcement.builder()
                .position("프론트엔드 주니어 개발자")
                .reward(1000000)
                .skill("Javascript")
                .content("원티드코리아에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .company(companyRepository.findById(2L).orElseThrow())
                .build());

        // when
        List<AnnouncementListResponseDto> result = announcementService.searchAnnouncements("백엔드");

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getPosition()).isEqualTo("백엔드 주니어 개발자");

    }
}
