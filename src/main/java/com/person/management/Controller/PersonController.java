package com.person.management.Controller;


import com.person.management.Entity.Person;
import com.person.management.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> all(){
        return new ResponseEntity<>(personService.all(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> add(@RequestBody Person person){
        return new ResponseEntity<>(personService.add(person), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id){
        return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Person person){
        personService.delete(person);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Person> update(@RequestBody Person person){
        return new ResponseEntity<>(personService.update(person),HttpStatus.OK);
    }
}
