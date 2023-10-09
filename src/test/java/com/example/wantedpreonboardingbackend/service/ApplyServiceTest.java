package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.ApplyRequestDto;
import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Apply;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.ApplyRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() {
        announcementRepository.deleteAll();
        applyRepository.deleteAll();
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
    }

    @AfterEach
    void tearDown() {
        announcementRepository.deleteAll();
        applyRepository.deleteAll();
    }

    @Test
    @DisplayName("채용공고 지원 테스트")
    void testApply(){
        // given
        setUp();
        try {
            applyService.apply(new ApplyRequestDto(1L, 1L));
            applyService.apply(new ApplyRequestDto(2L, 1L));
            applyService.apply(new ApplyRequestDto(1L, 1L));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // when
        List<Apply> result = applyRepository.findAll();

        // then
        assertThat(result).hasSize(2);
    }

}