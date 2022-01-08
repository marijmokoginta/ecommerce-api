package com.ecommerce.models.repository;

import com.ecommerce.dto.SupplierDTO;
import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s WHERE :products MEMBER OF s.products")
    Iterable<Supplier> findSupplierByProductId(@PathParam("products")Products products);

    Iterable<Supplier> findByNameContains(String name);
}
