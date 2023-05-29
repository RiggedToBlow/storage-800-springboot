package com.storage800.demo.test.repos;

import com.storage800.demo.test.models.SaleProduct;
import org.springframework.data.repository.CrudRepository;

public interface SaleProductRepo extends CrudRepository<SaleProduct, Integer> {
}
