package com.scheduleapp.service;

import com.scheduleapp.dto.CreateScheduleRequest;
import com.scheduleapp.dto.CreateScheduleResponse;
import com.scheduleapp.dto.GetOneScheduleResponse;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

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

    // 일정 단건 조회를 위한 정보 탐색 후 변환
    @Transactional
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById((scheduleId)).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
