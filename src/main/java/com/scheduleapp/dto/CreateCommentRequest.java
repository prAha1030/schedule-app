package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    // 댓글 내용, 작성자명, 비밀번호 요청
    private String contents;
    private String username;
    private String password;
}
