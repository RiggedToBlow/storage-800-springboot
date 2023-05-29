package com.storage800.demo.test.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("PRODUCT")
public class Product {
    @Id
    private Integer id;

    @NotEmpty
    private String name, description, category;

    private Date creationDate;



    public Product(String name, String description, String category, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
    }



    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
