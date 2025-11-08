package com.projectbasedlearning.Assignment.repositories;

import com.projectbasedlearning.Assignment.model.ContentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppConfigRepository extends JpaRepository<ContentTable, Long> {
    Optional<ContentTable> findByMyKey(String envValue);
}
