package com.storage800.demo.test.controllers;

import com.storage800.demo.test.models.Product;
import com.storage800.demo.test.repos.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
public class ProductsController {
    ProductRepo productRepo;

    public ProductsController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping("/")
    String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/product")
    Iterable<Product> getProducts(){
        return productRepo.findAll();
    }

    @PostMapping("/product")
    Product createProduct(@RequestBody Product product){
        product.setCreationDate(new Date());
    Product savedProduct = productRepo.save(product);
    return savedProduct;
    }

    @PutMapping("/product/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable int id){
        Product product = productRepo.findById(id).orElse(null);
        if (product==null){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        product.setName(newProduct.getName());
        product.setCategory(newProduct.getCategory());
        product.setDescription(newProduct.getDescription());
        return productRepo.save(product);
    }


}
