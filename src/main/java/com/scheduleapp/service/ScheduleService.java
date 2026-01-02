package com.scheduleapp.service;

import com.scheduleapp.dto.*;
import com.scheduleapp.entity.Comment;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.CommentRepository;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 일정 생성을 위한 정보를 저장 후 변환
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getUsername(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getUsername(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 단건 조회를 위한 정보 탐색 후 변환 / 댓글도 함께 응답
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById((scheduleId)).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        // 해당 일정의 댓글들 내림차순 정렬 후 변환
        List<Comment> comments = commentRepository.findByScheduleOrderByModifiedAtDesc(schedule);
        List<CreateCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            CreateCommentResponse dto = new CreateCommentResponse(
                    comment.getSchedule().getId(),
                    comment.getContents(),
                    comment.getUsername(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtos.add(dto);
        }
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                dtos
        );
    }

    // 일정 다건 조회 / 수정일 기준으로 내림차순 정렬 후 변환
    @Transactional(readOnly = true)
    public List<GetManyScheduleResponse> getAll(String username) {
        if (username == null) {
            // 일정 목록 전체
            List<Schedule> schedules = scheduleRepository.findByOrderByModifiedAtDesc();
            return schedules.stream().map(s -> new GetManyScheduleResponse(
                    s.getId(),
                    s.getTitle(),
                    s.getContents(),
                    s.getUsername(),
                    s.getCreatedAt(),
                    s.getModifiedAt()
            )).toList();
        }
        else {
            // 특정 작성자명의 일정 목록
            List<Schedule> schedules = scheduleRepository.findByUsernameOrderByModifiedAtDesc(username);
            return schedules.stream().map(s -> new GetManyScheduleResponse(
                    s.getId(),
                    s.getTitle(),
                    s.getContents(),
                    s.getUsername(),
                    s.getCreatedAt(),
                    s.getModifiedAt()
            )).toList();
        }
    }

    // 일정 수정 후 변환 / 비밀번호 미일치 시 미수정
    @Transactional
    public UpdateScheduleResponse updateOne(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.update(request.getTitle(), request.getUsername());
        }
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUsername()
        );
    }

    // 일정 삭제를 위한 비밀번호 확인 후 삭제
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (request.getPassword().equals(schedule.getPassword())) {
            scheduleRepository.delete(schedule);
        }
    }
}
