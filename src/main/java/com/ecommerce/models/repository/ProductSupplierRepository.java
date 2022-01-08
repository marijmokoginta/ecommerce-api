package com.ecommerce.models.repository;

import com.ecommerce.models.entities.ProductSupplier;
import org.springframework.data.repository.CrudRepository;

public interface ProductSupplierRepository extends CrudRepository<ProductSupplier, Long> {

    void deleteByProductIdAndSupplierId(long productId, long supplierId);
}
