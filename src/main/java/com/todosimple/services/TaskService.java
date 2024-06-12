package com.todosimple.services;

import com.todosimple.models.Task;
import com.todosimple.models.User;
import com.todosimple.repositories.TaskRepository;
import com.todosimple.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public Task findById (Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Usuario nao encontrad! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @Transactional
    public Task create(Task obj){
        Optional<User> user = this.userRepository.findById(obj.getUser().getId());
        obj.setId(null);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
       Task newObj = findById(obj.getId());
       newObj.setDescription(obj.getDescription());
       return taskRepository.save(obj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel deletar pois ha entidades relacionadas!");
        }

    }



}
