package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.time > ?1 and e.time < ?2 and e.analysisRequired = ?3")
    Page<Event> findByTimeGreaterThanAndTimeLessThanAndAnalysisRequired(LocalDateTime start, LocalDateTime end, boolean isAnalysisRequired, Pageable pageable);
}
