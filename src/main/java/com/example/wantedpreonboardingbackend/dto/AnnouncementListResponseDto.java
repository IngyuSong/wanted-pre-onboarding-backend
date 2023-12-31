package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AnnouncementListResponseDto {
    @JsonProperty(value = "채용공고_id")
    private Long id;
    @JsonProperty(value = "회사명")
    private String companyName;
    @JsonProperty(value = "국가")
    private String country;
    @JsonProperty(value = "지역")
    private String region;
    @JsonProperty(value = "채용포지션")
    private String position;
    @JsonProperty(value = "채용보상금")
    private Integer reward;
    @JsonProperty(value = "사용기술")
    private String skill;

    public AnnouncementListResponseDto(Announcement entity) {
        this.id = entity.getId();
        this.companyName = entity.getCompany().getName();
        this.country = entity.getCompany().getCountry();
        this.region = entity.getCompany().getRegion();
        this.position = entity.getPosition();
        this.reward = entity.getReward();
        this.skill = entity.getSkill();
    }
}
