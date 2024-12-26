package com.example.GFCreater.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String label;
    private String type; 
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;
}
