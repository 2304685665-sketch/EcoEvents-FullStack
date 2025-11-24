package com.ecoevents.ecoevents.repository;

import com.ecoevents.ecoevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // 因为继承了 JpaRepository，
    // Spring 会自动给你提供 save(), findById(), findAll(), delete() 等方法。
    // 你一行 SQL 都不用写！
}
