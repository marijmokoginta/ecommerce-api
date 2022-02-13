package com.ecommerce.controllers;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ResponseData;
import com.ecommerce.dto.SearchDTO;
import com.ecommerce.dto.SupplierDTO;
import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import com.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value= "/add")
    public ResponseEntity<ResponseData<Products>> create(@Valid @RequestBody ProductDTO productDTO, Errors errors) {

        ResponseData<Products> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);

        Products products = modelMapper.map(productDTO, Products.class);
        products.getCategory().setId(productDTO.getCategoryId());
//        products.getSuppliers().add(productDTO.getSupplier());

        responseData.setPayload(productService.save(products));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping(value= "/list")
    public Iterable<Products> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/listdesc")
    public Iterable<Products> findAllDesc() {
        return productService.findAllDesc();
    }

    @GetMapping(value = "/list/{size}/{page}")
    public Iterable<Products> findAllPerPage(@PathVariable("size") int size, @PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(size, page);
        return productService.findAllPerPage(pageable);
    }

    @GetMapping(value= "/list/{id}")
    public Products findOne(@PathVariable("id") long id) {
        return productService.findOne(id);
    }

    @DeleteMapping(value= "/delete/{id}")
    public void removeOne(@PathVariable("id") long id){
        productService.removeOne(id);
    }

    @PostMapping(value= "/supplier/{id}")
    public void setSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable("id") long productId) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        productService.addSupplier(supplier, productId);
    }

    @PostMapping(value = "/search")
    public List<Products> searchByNameDesc(@RequestBody SearchDTO searchDTO) {
        return productService.findProductsByNameLike(searchDTO.getSearchKey());
    }

    @PostMapping(value = "/remove/{productId}")
    public void removeSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable("productId") long productId) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        productService.removeSupplier(supplier, productId);
    }

    @GetMapping(value = "/show/{supplierId}")
    public List<Products> findProductBySupplierId(@PathVariable("supplierId") long supplierId) {
        return productService.findProductsBySupplierId(supplierId);
    }
}
