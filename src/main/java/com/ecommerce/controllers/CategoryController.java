package com.ecommerce.controllers;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.dto.ResponseData;
import com.ecommerce.models.entities.Category;
import com.ecommerce.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value= "/add")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);

        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(value= "/list")
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value= "/{id}")
    public Category findOne(@PathVariable("id") long id) {
        return categoryService.findOne(id);
    }

    @PutMapping(value= "/update")
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);

        Category category = modelMapper.map(categoryDTO, Category.class);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping(value= "/delete/{id}")
    public void removeOne(@PathVariable("id") long id) {
        categoryService.removeOne(id);
    }
}
