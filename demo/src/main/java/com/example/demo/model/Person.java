package com.example.demo.model;

import com.example.demo.configs.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    @Id
    private String idCard;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Material> borrowedMaterials = new ArrayList<>();

}