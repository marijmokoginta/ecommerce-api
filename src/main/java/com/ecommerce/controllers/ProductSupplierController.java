package com.ecommerce.controllers;

import com.ecommerce.services.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productsupp")
public class ProductSupplierController {

    @Autowired
    private ProductSupplierService productSupplierService;

    @DeleteMapping(value = "/delete/{productId}/{supplierId}")
    public void deleteByProductIdAndSupplierId(@PathVariable("productId") long productId, @PathVariable("supplierId") long supplierId) {
        productSupplierService.deleteByProductIdAndSupplierId(productId, supplierId);
    }
}
