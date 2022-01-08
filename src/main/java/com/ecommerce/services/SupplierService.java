package com.ecommerce.services;

import com.ecommerce.dto.SupplierDTO;
import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import com.ecommerce.models.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.ArrayList;
import java.util.Optional;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductService productService;

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier findOne(long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(!supplier.isPresent()) {
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public void removeOne(long id) {
        supplierRepository.deleteById(id);
    }

    public Iterable<Supplier> findSupplierByProductId(long productId) {
        Products products = productService.findOne(productId);
        if(products == null) {
            return new ArrayList<Supplier>();
        }
        return supplierRepository.findSupplierByProductId(products);
    }

    public Iterable<Supplier> findByNameContains(String name) {
        return supplierRepository.findByNameContains(name);
    }
}
