package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String username;
    private String password;
}
