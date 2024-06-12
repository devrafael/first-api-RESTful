package com.todosimple.repositories;

import com.todosimple.models.Task;
import com.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<User, Long> {

    List<Task> findByUser_Id(Long id);
}
