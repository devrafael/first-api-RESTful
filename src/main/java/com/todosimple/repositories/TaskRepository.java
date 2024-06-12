package com.todosimple.repositories;

import com.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);
}
