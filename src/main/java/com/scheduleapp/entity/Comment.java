package com.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    // 댓글 고유 식별자, 일정 (외래 키), 댓글 내용, 작성자명, 비밀번호, 작성일, 수정일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @Column(length = 100, nullable = false)
    private String contents;
    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 20, nullable = false)
    private String password;

    public Comment(Schedule schedule, String contents, String username, String password) {
        this.schedule = schedule;
        this.contents = contents;
        this.username = username;
        this.password = password;
    }
}