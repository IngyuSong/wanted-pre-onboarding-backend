package com.example.wantedpreonboardingbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnnouncementUpdateRequestDto {
    private String position;
    private Integer reward;
    private String content;
    private String skill;

    @Builder
    public AnnouncementUpdateRequestDto(String position, Integer reward, String content, String skill) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
    }
}
