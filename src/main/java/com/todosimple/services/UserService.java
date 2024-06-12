package com.todosimple.services;

import com.todosimple.models.User;
import com.todosimple.repositories.TaskRepository;
import com.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User findById (Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuario nao encontrad! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User create (User obj){
        obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update (User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return userRepository.save(newObj);
    }

    public void delete (Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel deletar pois ha entidades relacionadas!");
        }

    }


}
