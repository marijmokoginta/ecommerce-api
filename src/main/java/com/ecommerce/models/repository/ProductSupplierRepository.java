package com.ecommerce.models.repository;

import com.ecommerce.models.entities.ProductSupplier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;

public interface ProductSupplierRepository extends CrudRepository<ProductSupplier, Long> {

    @Modifying
    @Query("DELETE FROM ProductSupplier ps WHERE ps.productId = :productId AND ps.supplierId = :supplierId")
    void deleteOneRow(@PathParam("productId") long productId, @PathParam("supplierId") long supplierId);
}
