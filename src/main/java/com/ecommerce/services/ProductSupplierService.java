package com.ecommerce.services;

import com.ecommerce.models.entities.Products;
import com.ecommerce.models.repository.ProductSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplierService {

    @Autowired
    private ProductSupplierRepository productSupplierRepository;

    public void deleteByProductIdAndSupplierId(long productId, long supplierId) {
        productSupplierRepository.deleteOneRow(productId, supplierId);
    }
}
