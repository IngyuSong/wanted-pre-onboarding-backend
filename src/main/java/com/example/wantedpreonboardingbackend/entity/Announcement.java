package com.example.wantedpreonboardingbackend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private Integer reward;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String skill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Apply> applyList;

    @Builder
    public Announcement(String position, Integer reward, String content, String skill, Company company) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
        this.company = company;
    }

    public void update(String position, Integer reward, String content, String skill) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
    }

}
