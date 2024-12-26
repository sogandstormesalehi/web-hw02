package com.example.GFCreater.dto;

import lombok.Data;

@Data
public class FieldDTO {
    private Long id;
    private String name;
    private String label;
    private String type;
    private String defaultValue;
}