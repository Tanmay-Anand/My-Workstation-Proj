package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}