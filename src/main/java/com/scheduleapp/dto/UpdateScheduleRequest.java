package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String username;
    private String password;
}
