package br.com.jobem.api.controllers;

import br.com.jobem.api.models.User;
import br.com.jobem.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity index(){
        List<User> list = repository.findAll();
        if(!list.isEmpty()){
            return ok(list);
        }
        return status(204).build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user){
        if(!repository.existsByEmail(user.getEmail())) {
            repository.save(user);
            return status(201).build();
        }
        String message = "Email ja cadastrado";
        return status(400).body(message);

    }

    @GetMapping("/{id}")
    public ResponseEntity showByID(@PathVariable int id){

        if (id > 0) {
            return of(repository.findById(id));
        }
        return status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }

        return status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putUser(@PathVariable int id, @RequestBody User user){
        if(repository.existsById(id)){
            user.setId(id);
            repository.save(user);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
