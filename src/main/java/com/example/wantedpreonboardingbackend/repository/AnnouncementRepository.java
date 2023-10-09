package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, QuerydslPredicateExecutor<Announcement> {
    List<Announcement> findAllByCompanyId(Long id);
}
