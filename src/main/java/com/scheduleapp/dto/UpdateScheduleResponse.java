package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {
    // 일정 고유 식별자, 일정 제목, 일정 내용, 작성자명 응답
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;

    public UpdateScheduleResponse(Long id, String title, String contents, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
