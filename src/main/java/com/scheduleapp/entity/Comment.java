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
    @Id
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @Column(length = 100, nullable = false)
    private String contents;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public Comment(String contents, String username, String password) {
        this.contents = contents;
        this.username = username;
        this.password = password;
    }
}