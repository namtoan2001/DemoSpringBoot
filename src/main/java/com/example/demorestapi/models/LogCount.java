package com.example.demorestapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class LogCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int requestCount;

    private int transactionCount;

    @Temporal(TemporalType.DATE)
    private Date date;
}
