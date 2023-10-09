package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.ApplyRequestDto;
import com.example.wantedpreonboardingbackend.repository.AnnouncementRepository;
import com.example.wantedpreonboardingbackend.repository.ApplyRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final AnnouncementRepository announcementRepository;


    @Transactional
    public Long apply(ApplyRequestDto applyRequestDto) {
        if(applyRepository.existsByAnnouncement_IdAndMember_Id(applyRequestDto.getAnnouncementId(),applyRequestDto.getMemberId())) {
            throw new IllegalArgumentException("이미 지원한 공고입니다.");
        }
        applyRequestDto.setAnnouncement(announcementRepository.findById(applyRequestDto.getAnnouncementId()).orElseThrow(() -> new IllegalArgumentException("채용공고가 존재하지 않습니다.")));
        applyRequestDto.setMember(memberRepository.findById(applyRequestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")));
        return applyRepository.save(applyRequestDto.toEntity()).getId();
    }
}
