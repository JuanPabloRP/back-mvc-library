package com.example.demo.controller;

import com.example.demo.model.Material;
import com.example.demo.model.Movement;
import com.example.demo.model.Person;
import com.example.demo.service.MaterialService;
import com.example.demo.service.MovementService;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movement")
public class MovementController {

    private final MovementService movementService;
    private final PersonService personService;
    private final MaterialService materialService;

    public MovementController(MovementService movementService, PersonService personService, MaterialService materialService) {
        this.movementService = movementService;
        this.personService = personService;
        this.materialService = materialService;
    }


    @PostMapping("/borrow")
    public boolean borrowMaterial(@RequestParam String personId, @RequestParam String materialId) {
        Person person = personService.findById(personId);
        Material material = materialService.findById(materialId);

        boolean result = movementService.borrowMaterial(person, material);
        if (result) {
            personService.registerPerson(person);
            materialService.registerMaterial(material);
        }
        return result;
    }

    @PostMapping("/return")
    public boolean returnMaterial(@RequestParam String personId, @RequestParam String materialId) {
        Person person = personService.findById(personId);
        Material material = materialService.findById(materialId);

        boolean result = movementService.returnMaterial(person, material);
        if (result) {
            personService.registerPerson(person);
            materialService.registerMaterial(material);
        }
        return result;
    }


    @GetMapping("/history")
    public List<Movement> getHistory() {
        return movementService.getLibraryHistory();
    }
}
