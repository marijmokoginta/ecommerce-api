package com.ecommerce.models.repository;

import com.ecommerce.models.entities.Products;
import com.ecommerce.models.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Products, Long> {

    @Query("SELECT p FROM Products p WHERE p.name LIKE :name")
    List<Products> findProductsByNameLike(@PathParam("name") String name);

    List<Products> findByNameContainsOrderByIdDesc(String name);

    @Query("SELECT p FROM Products p WHERE :supplier MEMBER OF p.suppliers")
    List<Products> findProductsBySuppliersId(@PathParam("supplier") Supplier supplier);

    Iterable<Products> findAllByOrderByIdDesc();

    Page<Products> findAllByOrderByIdDesc(Pageable pageable);
}
