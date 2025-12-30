package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final String username;

    public CreateScheduleResponse(Long scheduleId, String title, String contents, String username) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
