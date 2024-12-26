package com.example.GFCreater.util;

import com.example.GFCreater.dto.FormDTO;
import com.example.GFCreater.dto.FieldDTO;
import com.example.GFCreater.models.Form;
import com.example.GFCreater.models.Field;
import java.util.List;

public class FormMapper {
    public static FormDTO toDTO(Form form) {
        FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        formDTO.setName(form.getName());
        formDTO.setPublished(form.isPublished());
        formDTO.setSubmitButtonMethod(form.getSubmitButtonMethod());
        formDTO.setSubmitButtonAddress(form.getSubmitButtonAddress());
        formDTO.setFields(form.getFields().stream().map(FormMapper::toDTO).toList());
        return formDTO;
    }

    public static FieldDTO toDTO(Field field) {
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setId(field.getId());
        fieldDTO.setName(field.getName());
        fieldDTO.setLabel(field.getLabel());
        fieldDTO.setType(field.getType());
        fieldDTO.setDefaultValue(field.getDefaultValue());
        return fieldDTO;
    }

    public static Form toEntity(FormDTO formDTO) {
        Form form = new Form();
        form.setName(formDTO.getName());
        form.setPublished(formDTO.isPublished());
        form.setSubmitButtonMethod(formDTO.getSubmitButtonMethod());
        form.setSubmitButtonAddress(formDTO.getSubmitButtonAddress());

        if (formDTO.getFields() != null) {
            List<Field> fields = formDTO.getFields().stream().map(FormMapper::toEntity).toList();
            form.addFields(fields); // set fields and associate them with the form
        }
        return form;
    }

    public static Field toEntity(FieldDTO fieldDTO) {
        Field field = new Field();
        field.setName(fieldDTO.getName());
        field.setLabel(fieldDTO.getLabel());
        field.setType(fieldDTO.getType());
        field.setDefaultValue(fieldDTO.getDefaultValue());
        return field; // not set the form here; it will be handled by addFields()
    }
}
