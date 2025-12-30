package com.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final LocalDateTime scheduleCreateAt;
    private final LocalDateTime scheduleUpdateAt;

    public CreateScheduleResponse(Long id, String title, String contents, String username,
                                  LocalDateTime scheduleCreateAt, LocalDateTime scheduleUpdateAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.scheduleCreateAt = scheduleCreateAt;
        this.scheduleUpdateAt = scheduleUpdateAt;
    }
}
