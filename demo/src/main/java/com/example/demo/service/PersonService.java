package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonService {
    private final PersonRepository personRepo;

    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Person registerPerson(Person person) {
        return personRepo.save(person);
    }

    public boolean deletePerson(String idCard) {
        Person person = personRepo.findById(idCard).orElse(null);
        if (person != null && person.getBorrowedMaterials().isEmpty()) {
            personRepo.delete(person);
            return true;
        }
        return false;
    }

    public Person findById(String idCard) {
        return personRepo.findById(idCard)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

}
