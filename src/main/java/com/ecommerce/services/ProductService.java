package com.ecommerce.services;

import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import com.ecommerce.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private SupplierService supplierService;

    public Products save(Products product) {
        return productRepository.save(product);
    }

    public Products findOne(long id) {
        Optional<Products> products = productRepository.findById(id);
        if(!products.isPresent()) {
            return null;
        }
        return products.get();
    }

    public Iterable<Products> findAll() {
        return productRepository.findAll();
    }

    public Iterable<Products> findAllDesc() {
        return productRepository.findAllByOrderByIdDesc();
    }

    public void removeOne(long id) {
        productRepository.deleteById(id);
    }
    
    public void addSupplier(Supplier supplier, long productId){
        Products products = findOne(productId);
        if(products == null) {
            throw new RuntimeException("Product with ID: "+productId+" no found");
        }
        products.getSuppliers().add(supplier);
        save(products);
    }

    public List<Products> findProductsByNameLike(String name) {
        return productRepository.findByNameContainsOrderByIdDesc(name);
    }

    public Iterable<Products> findAllPerPage(Pageable pageable) {
        return productRepository.findAllByOrderByIdDesc(pageable);
    }

//    public List<Products> findProductsBySupplierId(long supplierId) {
//        Supplier supplier = supplierService.findOne(supplierId);
//        if(supplier == null) {
//            return new ArrayList<Products>();
//        }
//        return productRepository.findProductsBySuppliersId(supplier);
//    }
}
