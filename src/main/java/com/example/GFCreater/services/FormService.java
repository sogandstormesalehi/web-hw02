package com.example.GFCreater.services;

import com.example.GFCreater.models.Form;
import com.example.GFCreater.models.Field;
import com.example.GFCreater.exception.ResourceNotFoundException;
import com.example.GFCreater.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form getFormById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form with ID " + id + " not found"));
    }

    public Form createForm(Form form) {
        if (form.getFields() != null) {
            form.addFields(form.getFields());
        }
        return formRepository.save(form);
    }

    public Form updateForm(Long id, Form updatedForm) {
// updates an existing form by replacing its details with the new ones provided. first fetches the form by ID and throws an exception if it doesn’t exist. updates the form’s main properties like name, publication status, and submit button details. existing fields are cleared, the new fields are added so that each one is properly linked to the form. saves the updated form to the database and returns it.
        Form existingForm = formRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Form with ID " + id + " not found"));
    
        existingForm.setName(updatedForm.getName());
        existingForm.setPublished(updatedForm.isPublished());
        existingForm.setSubmitButtonMethod(updatedForm.getSubmitButtonMethod());
        existingForm.setSubmitButtonAddress(updatedForm.getSubmitButtonAddress());
    
        existingForm.getFields().clear();
        for (Field field : updatedForm.getFields()) {
            field.setForm(existingForm);
            existingForm.getFields().add(field);
        }
    
        return formRepository.save(existingForm);
    }
    

    public void deleteForm(Long id) {
        if (!formRepository.existsById(id)) {
            throw new ResourceNotFoundException("Form with ID " + id + " not found");
        }
        formRepository.deleteById(id);
    }

    public void publishForm(Long id) {
        Form form = getFormById(id);
        form.setPublished(true);
        formRepository.save(form);
    }
}