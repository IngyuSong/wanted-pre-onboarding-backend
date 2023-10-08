package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class AnnouncementCreateRequestDto {
    private String position;
    private Integer reward;
    private String content;
    private String skill;
    private Long companyId;
    @Setter
    private Company company;

    @Builder
    public AnnouncementCreateRequestDto(String position, Integer reward, String content, String skill, Long companyId) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
        this.companyId = companyId;
    }

    public Announcement toEntity() {
        return Announcement.builder()
              .position(position)
              .reward(reward)
              .content(content)
              .skill(skill)
              .company(company)
              .build();
    }
}
