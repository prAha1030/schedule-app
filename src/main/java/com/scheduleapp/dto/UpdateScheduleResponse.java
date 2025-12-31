package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {
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
