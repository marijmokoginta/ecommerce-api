package com.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {
    private long id;

    @NotEmpty(message = "Category name is required")
    private String name;

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
}
