package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.time > ?1 and e.time < ?2 and e.analysisRequired = ?3")
    Page<Event> findByTimeGreaterThanAndTimeLessThanAndAnalysisRequired(LocalDateTime start, LocalDateTime end, boolean isAnalysisRequired, Pageable pageable);


    @Transactional
    @Modifying
    @Query("delete from Event e where e.time < :time")
    void deleteByTimeLessThan(LocalDateTime time);

    @Transactional
    @Modifying
    @Query("update Event e set e.analysisRequired = true where e.duration > ?1 and type(e) = ?2")
    void updateAnalysisRequiredByDurationGreaterThan(int duration, Class<? extends Event> clazz);


}
