package com.example.GFCreater.controllers;

import com.example.GFCreater.util.FormMapper;
import com.example.GFCreater.dto.FieldDTO;
import com.example.GFCreater.dto.FormDTO;
import com.example.GFCreater.models.Form;
import com.example.GFCreater.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping("/")
    public List<FormDTO> getAllForms() {
        return formService.getAllForms()
                          .stream()
                          .map(FormMapper::toDTO)
                          .toList();
    }

    @PostMapping("/")
    public FormDTO createForm(@RequestBody FormDTO formDTO) {
        return FormMapper.toDTO(formService.createForm(FormMapper.toEntity(formDTO)));
    }

    @GetMapping("/{id}")
    public FormDTO getFormById(@PathVariable Long id) {
        return FormMapper.toDTO(formService.getFormById(id));
    }

    @PutMapping("/{id}")
    public FormDTO updateForm(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        return FormMapper.toDTO(formService.updateForm(id, FormMapper.toEntity(formDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    @PostMapping("/{id}/publish")
    public void publishForm(@PathVariable Long id) {
        formService.publishForm(id);
    }

    @GetMapping("/{id}/fields")
    public List<FieldDTO> getFieldsByFormId(@PathVariable Long id) {
        Form form = formService.getFormById(id);
        return form.getFields().stream().map(FormMapper::toDTO).toList();
    }
    
    @GetMapping("/published")
    public List<FormDTO> getPublishedForms() {
        return formService.getAllForms().stream()
                        .filter(Form::isPublished)
                        .map(FormMapper::toDTO)
                        .toList();
}

}
