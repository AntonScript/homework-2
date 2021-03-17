package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "custom_comparator")
public class CustomComparator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
