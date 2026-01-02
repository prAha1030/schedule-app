package com.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetOneScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CreateCommentResponse> commentList;

    public GetOneScheduleResponse(Long id, String title, String contents, String username,
                                  LocalDateTime createdAt, LocalDateTime modifiedAt, List<CreateCommentResponse> commentList) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentList = commentList;
    }
}
