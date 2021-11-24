package br.com.jobem.api.controllers;

import br.com.jobem.api.models.KnowledgeArea;
import br.com.jobem.api.repositories.KnowledgeAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/knowledge-area")
public class KnowledgeAreaController {

    @Autowired
    private KnowledgeAreaRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index(){
        List<KnowledgeArea> list = repository.findAll();
        if(!list.isEmpty()) {
            return ok(list);
        }
        return status(204).build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody KnowledgeArea knowledgeArea){

        repository.save(knowledgeArea);
        return status(201).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id){
        if (id > 0){
            return of(repository.findById(id));
        }
        return status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        return status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody KnowledgeArea knowledgeArea){
        if(repository.existsById(id)){
            knowledgeArea.setId(id);
            repository.save(knowledgeArea);
            return status(200).build();
        }
        return status(404).build();
    }
}
