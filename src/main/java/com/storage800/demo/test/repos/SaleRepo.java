package com.storage800.demo.test.repos;

import com.storage800.demo.test.models.Sale;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepo extends CrudRepository<Sale, Integer> {
}
