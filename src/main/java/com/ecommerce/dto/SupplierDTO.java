package com.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierDTO {
    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Addres is required")
    private String alamat;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
