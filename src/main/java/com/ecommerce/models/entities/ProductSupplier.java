package com.ecommerce.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product_supplier")
public class ProductSupplier {

    @Id
    @Column(name = "product_id")
    private long productId;

    @Column(name = "supplier_id")
    private long supplierId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
