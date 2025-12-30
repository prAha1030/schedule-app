package com.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    // 고유 식별 번호, 일정 제목, 일정 내용, 작성자명, 비밀번호, 작성일, 수정일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String contents;
    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 20, nullable = false)
    private String password;
    private LocalDateTime scheduleCreateAt;
    private LocalDateTime scheduleUpdateAt;

    // 생성자
    public Schedule(Long scheduleId, String title, String contents, String username, String password,
                    LocalDateTime scheduleCreateAt, LocalDateTime scheduleUpdateAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.password = password;
        this.scheduleCreateAt = scheduleCreateAt;
        this.scheduleUpdateAt = scheduleUpdateAt;
    }
    // 세터
    public void update(String title, String contents, String username, String password,
                       LocalDateTime scheduleCreateAt, LocalDateTime scheduleUpdateAt) {
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.password = password;
        this.scheduleCreateAt = scheduleCreateAt;
        this.scheduleUpdateAt = scheduleUpdateAt;
    }
}
