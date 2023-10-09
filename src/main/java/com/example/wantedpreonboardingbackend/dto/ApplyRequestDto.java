package com.example.wantedpreonboardingbackend.dto;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import com.example.wantedpreonboardingbackend.entity.Apply;
import com.example.wantedpreonboardingbackend.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class ApplyRequestDto {
    @JsonProperty(value = "채용공고_id")
    private Long announcementId;
    @JsonProperty(value = "사용자_id")
    private Long memberId;

    @Setter
    private Announcement announcement;
    @Setter
    private Member member;

    @Builder
    public ApplyRequestDto(Long announcementId, Long memberId) {
        this.announcementId = announcementId;
        this.memberId = memberId;
    }

    public Apply toEntity() {
        return Apply.builder()
              .announcement(announcement)
              .member(member)
              .build();
    }

}
