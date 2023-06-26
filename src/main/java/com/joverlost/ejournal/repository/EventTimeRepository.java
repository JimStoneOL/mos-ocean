package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTimeRepository extends JpaRepository<EventTime,Long> {
}
