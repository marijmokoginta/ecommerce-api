package com.ecommerce.controllers;

import com.ecommerce.dto.ResponseData;
import com.ecommerce.dto.SearchDTO;
import com.ecommerce.dto.SupplierDTO;
import com.ecommerce.models.entities.Supplier;
import com.ecommerce.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value= "/add")
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        ResponseData<Supplier> supplierResponseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                supplierResponseData.getMessages().add(error.getDefaultMessage());
            }
            supplierResponseData.setStatus(false);
            supplierResponseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(supplierResponseData);
        }
        supplierResponseData.setStatus(true);

        // mapping object DTO ke object entity
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        supplierResponseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(supplierResponseData);
    }

    @GetMapping(value= "/list")
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping(value= "/{id}")
    public Supplier findOne(@PathVariable("id") long id) {
        return supplierService.findOne(id);
    }

    @PutMapping(value= "/update")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        ResponseData<Supplier> supplierResponseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                supplierResponseData.getMessages().add(error.getDefaultMessage());
            }
            supplierResponseData.setStatus(false);
            supplierResponseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(supplierResponseData);
        }
        supplierResponseData.setStatus(true);

        // mapping object DTO ke object entity
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        supplierResponseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(supplierResponseData);
    }

    @DeleteMapping(value= "/delete/{id}")
    public void removeOne(@PathVariable("id") long id) {
        supplierService.removeOne(id);
    }

    @GetMapping(value = "/list/{productId}")
    public Iterable<SupplierDTO> getSupplierByProductId(@PathVariable("productId") long productId) {
        Iterable<Supplier> suppliers = supplierService.findSupplierByProductId(productId);
        List<SupplierDTO> supplierDTOS = new ArrayList<>();

        for(Supplier supplier : suppliers) {
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setId(supplier.getId());
            supplierDTO.setName(supplier.getName());
            supplierDTO.setAlamat(supplier.getAlamat());
            supplierDTO.setEmail(supplier.getEmail());
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    @PostMapping(value = "/search")
    public Iterable<Supplier> searchSupplierByName(@RequestBody SearchDTO searchDTO) {
        return supplierService.findByNameContains(searchDTO.getSearchKey());
    }
}
