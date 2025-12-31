package com.scheduleapp.repository;

import com.scheduleapp.entity.Comment;
import com.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySchedule(Schedule schedule);

    List<Comment> findByScheduleOrderByModifiedAtDesc(Schedule schedule);
}
