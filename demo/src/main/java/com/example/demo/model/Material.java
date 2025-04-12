package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Material {
    @Id
    private String id;

    private String title;

    private LocalDate registrationDate;

    private int totalQuantity;

    private int availableQuantity;

}

