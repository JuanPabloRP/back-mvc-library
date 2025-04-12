package com.example.demo.service;

import com.example.demo.model.Material;
import com.example.demo.repository.MaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepo;

    public MaterialService(MaterialRepository materialRepo) {
        this.materialRepo = materialRepo;
    }

    public List<Material> getAllMaterials() {
        return materialRepo.findAll();
    }

    public Material registerMaterial(Material material) {
        return materialRepo.save(material);
    }

    public boolean incrementMaterialQuantity(String materialId, int amount) {
        Material material = materialRepo.findById(materialId).orElse(null);
        if (material != null && amount > 0) {
            material.setTotalQuantity(material.getTotalQuantity() + amount);
            material.setAvailableQuantity(material.getAvailableQuantity() + amount);
            materialRepo.save(material);
            return true;
        }
        return false;
    }

    public Material findById(String materialId) {
        return materialRepo.findById(materialId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material not found"));
    }
}
