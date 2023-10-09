package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class AnnouncementCreateRequestDto {
    @JsonProperty(value = "회사_id")
    private Long companyId;
    @JsonProperty(value = "채용포지션")
    private String position;
    @JsonProperty(value = "채용보상금")
    private Integer reward;
    @JsonProperty(value = "채용내용")
    private String content;
    @JsonProperty(value = "사용기술")
    private String skill;
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
