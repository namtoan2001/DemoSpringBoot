package com.example.demorestapi.repositories;

import com.example.demorestapi.models.LogCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LogCountRepository extends JpaRepository<LogCount, Long> {
    Optional<LogCount> findByDate(Date date);
}
