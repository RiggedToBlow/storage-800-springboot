package com.storage800.demo.test.repos;

import com.storage800.demo.test.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product,Integer> {
}
