package com.scheduleapp.service;

import com.scheduleapp.dto.CreateCommentRequest;
import com.scheduleapp.dto.CreateCommentResponse;
import com.scheduleapp.entity.Comment;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.CommentRepository;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성을 위한 정보 변환 후 해당 일정과 함께 응답
    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (commentRepository.findAll().stream().filter(c -> c.getSchedule().equals(schedule)).count() >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지만 생성 가능합니다.");
        }
        Comment comment = new Comment(schedule, request.getContents(), request.getUsername(), request.getPassword());
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getSchedule().getId(),
                savedComment.getContents(),
                savedComment.getUsername(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
                );
    }
}
