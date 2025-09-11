package org.example.testspring.controllers;


import org.example.testspring.model.DataForm;
import org.example.testspring.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5175")
@RestController
@RequestMapping("/api")
public class DataFormController {
    @Autowired
    MyRepository repo;

    @PostMapping("/data")
    public ResponseEntity<String> proccessDataForm(@RequestBody DataForm dataForm) {
        System.out.println(dataForm.getName());
        repo.save(dataForm);
        return ResponseEntity.ok(dataForm.getName());
    }
}
