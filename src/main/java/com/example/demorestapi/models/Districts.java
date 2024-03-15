package com.example.demorestapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "districts")
public class Districts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Provinces province;
}
