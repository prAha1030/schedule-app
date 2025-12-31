package com.scheduleapp.service;

import com.scheduleapp.dto.*;
import com.scheduleapp.entity.Comment;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.CommentRepository;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    // 일정 다건 조회 / 전체 / 수정일 기준으로 내림차순 정렬 후 변환
    @Transactional(readOnly = true)
    public List<GetManyScheduleResponse> getAll() {
        List<Schedule> allSchedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC,"modifiedAt"));
        List<GetManyScheduleResponse> dtos = new ArrayList<>();
        for (Schedule allSchedule : allSchedules) {
            GetManyScheduleResponse dto = new GetManyScheduleResponse(
                    allSchedule.getId(),
                    allSchedule.getTitle(),
                    allSchedule.getContents(),
                    allSchedule.getUsername(),
                    allSchedule.getCreatedAt(),
                    allSchedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 일정 다건 조회 / 특정 작성자명의 일정 목록 / 수정일 기준으로 내림차순 정렬 후 변환
    @Transactional(readOnly = true)
    public List<GetManyScheduleResponse> getFilter(String username) {
        List<Schedule> filterSchedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt"))
                .stream().filter(s -> s.getUsername().equals(username)).toList();
        List<GetManyScheduleResponse> dtos = new ArrayList<>();
        for (Schedule filterSchedule : filterSchedules) {
            GetManyScheduleResponse dto = new GetManyScheduleResponse(
                    filterSchedule.getId(),
                    filterSchedule.getTitle(),
                    filterSchedule.getContents(),
                    filterSchedule.getUsername(),
                    filterSchedule.getCreatedAt(),
                    filterSchedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
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
