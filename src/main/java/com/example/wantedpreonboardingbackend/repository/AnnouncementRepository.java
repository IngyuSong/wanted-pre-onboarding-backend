package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, QuerydslPredicateExecutor<Announcement> {
}
