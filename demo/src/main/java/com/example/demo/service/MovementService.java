package com.example.demo.service;

import com.example.demo.configs.MovementType;
import com.example.demo.model.Material;
import com.example.demo.model.Movement;
import com.example.demo.model.Person;
import com.example.demo.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovementService {
    private final MovementRepository movementRepo;

    public MovementService(MovementRepository movementRepo) {
        this.movementRepo = movementRepo;
    }

    public boolean borrowMaterial(Person person, Material material) {
        if (material.getAvailableQuantity() > 0) {
            int limit = switch (person.getRole()) {
                case STUDENT -> 5;
                case PROFESSOR -> 3;
                case ADMINISTRATIVE -> 1;
            };
            if (person.getBorrowedMaterials().size() >= limit) return false;

            person.getBorrowedMaterials().add(material);
            material.setAvailableQuantity(material.getAvailableQuantity() - 1);

            movementRepo.save(new Movement(null, person, material, LocalDate.now(), MovementType.BORROW));
            return true;
        }
        return false;
    }

    public boolean returnMaterial(Person person, Material material) {
        if (person.getBorrowedMaterials().contains(material)) {
            person.getBorrowedMaterials().remove(material);
            material.setAvailableQuantity(material.getAvailableQuantity() + 1);

            movementRepo.save(new Movement(null, person, material, LocalDate.now(), MovementType.RETURN));
            return true;
        }
        return false;
    }

    public List<Movement> getLibraryHistory() {
        return movementRepo.findAll();
    }
}
