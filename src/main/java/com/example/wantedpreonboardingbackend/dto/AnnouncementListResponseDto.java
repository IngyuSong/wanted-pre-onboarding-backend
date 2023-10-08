package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import lombok.Getter;

@Getter
public class AnnouncementListResponseDto {
    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
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
