package com.example.GFCreater.dto;

import lombok.Data;
import java.util.List;

@Data
public class FormDTO {
    private Long id;
    private String name;
    private boolean isPublished;
    private List<FieldDTO> fields;
    private String submitButtonMethod;
    private String submitButtonAddress;
}
