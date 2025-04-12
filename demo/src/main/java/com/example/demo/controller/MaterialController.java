package com.example.demo.controller;


import com.example.demo.model.Material;
import com.example.demo.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping
    public Material registerMaterial(@RequestBody Material material) {
        return materialService.registerMaterial(material);
    }

    @PostMapping("/{id}/increment")
    public boolean incrementMaterial(@PathVariable String id, @RequestParam int amount) {
        return materialService.incrementMaterialQuantity(id, amount);
    }
}
