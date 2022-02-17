package com.ecommerce.services;

import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import com.ecommerce.models.repositories.ProductRepository;
import com.ecommerce.models.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Products save(Products product) {
        return productRepository.save(product);
    }

    public Products findOne(long id) {
        Optional<Products> products = productRepository.findById(id);
        return products.orElse(null);
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
            throw new RuntimeException("Product with ID: "+productId+" not found");
        }
        products.getSuppliers().add(supplier);
        save(products);
    }

    public void removeSupplier(Supplier supplier, long productId) {
        Products products = findOne(productId);
        if(products == null) {
            throw new RuntimeException("Product with id: " + productId + "not found");
        }
        products.getSuppliers().remove(supplier);

        save(products);
    }

    public List<Products> findProductsByNameLike(String name) {
        return productRepository.findByNameContainsOrderByIdDesc(name);
    }

    public Iterable<Products> findAllPerPage(Pageable pageable) {
        return productRepository.findAllByOrderByIdDesc(pageable);
    }

    public List<Products> findProductsBySupplierId(long supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        return productRepository.findProductsBySuppliersId(supplier);
    }
}
