package com.storage800.demo.test.repos;

import com.storage800.demo.test.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Integer> {
}
