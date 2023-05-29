package com.storage800.demo.test.repos;

import com.storage800.demo.test.models.SaleTransaction;
import org.springframework.data.repository.CrudRepository;

public interface SaleTransactionRepo extends CrudRepository<SaleTransaction, Integer> {
}
