package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AnnouncementRepositoryTest {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    CompanyRepository companyRepository;

    @AfterEach
    public void tearDown() {
        announcementRepository.deleteAll();
    }

    @Test
    @DisplayName("채용공고 등록 & 조회 테스트")
    void createAnnouncementTest() {
        // given
        String position = "백엔드 주니어 개발자";
        Integer reward = 1500000;
        Company company = companyRepository.findById(1L).orElseThrow();
        announcementRepository.save(Announcement.builder()
                .position(position)
                .reward(reward)
                .skill("Python")
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .company(company)
                .build());

        // when
        List<Announcement> announcements = announcementRepository.findAll();

        // then
        Announcement announcement = announcements.get(0);
        assertThat(announcement.getPosition()).isEqualTo(position);
        assertThat(announcement.getReward()).isEqualTo(reward);
    }
}