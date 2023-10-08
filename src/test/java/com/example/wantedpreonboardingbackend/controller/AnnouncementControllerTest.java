package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.AnnouncementCreateRequestDto;
import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnnouncementControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @AfterEach
    public void tearDown() {
        announcementRepository.deleteAll();
    }

    @Test
    @DisplayName("채용공고 등록 테스트")
    void createAnnouncementTest() {
        // given
        String position = "백엔드 주니어 개발자";
        Integer reward = 1500000;
        AnnouncementCreateRequestDto dto = AnnouncementCreateRequestDto.builder()
                .position(position)
                .reward(reward)
                .skill("Python")
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .companyId(1L)
                .build();

        String url = "http://localhost:" + port + "/api/announcement";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isPositive();

        List<Announcement> announcements = announcementRepository.findAll();
        assertThat(announcements.get(0).getPosition()).isEqualTo(position);
        assertThat(announcements.get(0).getReward()).isEqualTo(reward);
    }

}