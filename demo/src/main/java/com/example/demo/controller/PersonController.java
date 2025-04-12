package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person registerPerson(@RequestBody Person person) {
        return personService.registerPerson(person);
    }

    @DeleteMapping("/{idCard}")
    public boolean deletePerson(@PathVariable String idCard) {
        return personService.deletePerson(idCard);
    }
}