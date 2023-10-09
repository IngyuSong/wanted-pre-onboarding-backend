package com.example.wantedpreonboardingbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnnouncementUpdateRequestDto {
    @JsonProperty(value = "채용포지션")
    private String position;
    @JsonProperty(value = "채용보상금")
    private Integer reward;
    @JsonProperty(value = "채용내용")
    private String content;
    @JsonProperty(value = "사용기술")
    private String skill;

    @Builder
    public AnnouncementUpdateRequestDto(String position, Integer reward, String content, String skill) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
    }
}
