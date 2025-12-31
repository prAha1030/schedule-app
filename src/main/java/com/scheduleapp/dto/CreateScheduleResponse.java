package com.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    // 일정 고유 식별자, 일정 제목, 일정 내용, 작성자명, 작성일, 수정일 응답
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String title, String contents, String username,
                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
