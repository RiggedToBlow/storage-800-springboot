package com.storage800.demo.test.controllers;

import com.storage800.demo.test.models.Client;
import com.storage800.demo.test.repos.ClientRepo;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class ClientsController {

    ClientRepo clientRepo;

    public ClientsController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping("/clients")
    Iterable<Client> getClients(){
        return clientRepo.findAll();
    }

    @PostMapping("/client")
    Client createClient(@RequestBody Client client){
        return clientRepo.save(client);
    }


    @PutMapping("/client/{id}")
    Client updateClient(@RequestBody Client client, @PathVariable int id) throws ResponseStatusException {
        Client c = clientRepo.findById(id).orElse(null);
        if (c==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource");
        }
        c.setName(client.getName());
        c.setLastName(client.getLastName());
        c.setMobile(client.getMobile());
        return clientRepo.save(c);
    }

}
