package com.example.GFCreater.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isPublished;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form", orphanRemoval = true)
    private List<Field> fields;

    private String submitButtonMethod;
    private String submitButtonAddress;

    public void addFields(List<Field> fields) {
        fields.forEach(field -> field.setForm(this)); 
        this.fields = fields;
    }
}
