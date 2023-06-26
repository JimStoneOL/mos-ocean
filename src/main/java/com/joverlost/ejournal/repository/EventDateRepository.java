package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDateRepository extends JpaRepository<EventDate,Long> {
}
