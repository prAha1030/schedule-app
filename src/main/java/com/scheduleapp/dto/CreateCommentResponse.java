package com.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    // 일정 고유 식별자, 댓글 내용, 작성자명, 비밀번호, 작성일, 수정일 응답
    private final Long scheduleId;
    private final String contents;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long scheduleId, String contents, String username,
                                 LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.contents = contents;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
