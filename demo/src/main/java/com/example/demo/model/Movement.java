package com.example.demo.model;

import com.example.demo.configs.MovementType;
import jakarta.persistence.*;
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
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Material material;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MovementType type;

}

