package com.scheduleapp.controller;

import com.scheduleapp.dto.*;
import com.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    // 일정 다건 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetManyScheduleResponse>> getAllSchedule(@RequestParam(required = false) String username) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(username));
    }

    // 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId,
                                                                 @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateOne(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId,
                                               @RequestBody DeleteScheduleRequest request) {
        scheduleService.delete(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
