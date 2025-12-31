package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    // 일정 제목, 작성자명, 비밀번호 요청
    private String title;
    private String username;
    private String password;
}
