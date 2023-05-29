package com.storage800.demo.test.controllers;

import com.storage800.demo.test.enums.SaleActionTypes;
import com.storage800.demo.test.models.*;
import com.storage800.demo.test.repos.ProductRepo;
import com.storage800.demo.test.repos.SaleProductRepo;
import com.storage800.demo.test.repos.SaleRepo;
import com.storage800.demo.test.repos.SaleTransactionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
public class SalesController {

    SaleRepo saleRepo;
    SaleTransactionRepo saleTransactionRepo;

    ProductRepo productRepo;
    SaleProductRepo saleProductRepo;
    public SalesController(SaleRepo saleRepo, SaleTransactionRepo saleTransactionRepo, ProductRepo productRepo, SaleProductRepo saleProductRepo) {
        this.saleRepo = saleRepo;
        this.saleTransactionRepo = saleTransactionRepo;
        this.productRepo = productRepo;
        this.saleProductRepo = saleProductRepo;
    }

    @GetMapping("/sales")
    Iterable<SaleVM> getAllSales(){
        ArrayList<SaleVM> saleVMS = new ArrayList<>();
        Iterable<Sale> sales = saleRepo.findAll();
        for (Sale s: sales) {
            ArrayList<SaleProduct> sps = new ArrayList<>();
            for (SaleProduct saleProduct : saleProductRepo.findAll()) {
                if (saleProduct.getSale()==s.getId())
                    sps.add(saleProduct);
            }
            SaleVM saleVM = new SaleVM(s, sps);
            saleVMS.add(saleVM);
        }
        return saleVMS;
    }

    @PostMapping("/sale")
    SaleVM createSale(@RequestBody SaleVM sale){
        Sale s = sale.getSale();
        Iterable<SaleProduct> saleItems = sale.getSaleProduct();
        double total = 0;
        for (SaleProduct sp: saleItems) {
            Product p = productRepo.findById(sp.getProduct()).orElse(null);
            total+= sp.getPrice() * sp.getQuantity();
        }
        s.setTotal(total);
        s.setCreationDate(new Date());
        s = saleRepo.save(s);
        for (SaleProduct sp: saleItems) {
            sp.setSale(s.getId());
            saleProductRepo.save(sp);
        }
        sale.setSale(s);
        sale.setSaleProduct(saleItems);
        SaleTransaction saleTransaction = new SaleTransaction(
                SaleActionTypes.Create,null,sale,s.getId(),new Date()
        );
        saleTransactionRepo.save(saleTransaction);
        return sale;
    }

    @PutMapping("/sale/{id}")
    SaleVM editSale(@RequestBody SaleVM sale, @PathVariable int saleId){
        Sale s = saleRepo.findById(saleId).orElse(null);
        if (s== null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale NOT FOUND");
        }
        SaleTransaction currentTransaction = new SaleTransaction(SaleActionTypes.Update,
                new SaleVM(s, new ArrayList<>()),
                sale,
                saleId,
                new Date());
        s.setClient(sale.getSale().getClient());
        s.setSeller(sale.getSale().getSeller());
        List<Integer> productIds = StreamSupport.stream(sale.getSaleProduct().spliterator(), false)
                .map(SaleProduct::getId)
                .collect(Collectors.toList());
        List<SaleProduct> dbProducts = StreamSupport.stream( saleProductRepo.findAllById(productIds).spliterator(),false)
                .collect(Collectors.toList());
        currentTransaction.getOldValue().setSaleProduct(new ArrayList<>(dbProducts));
        for (SaleProduct reqProduct : sale.getSaleProduct()) {
            for (int i = 0; i < dbProducts.size(); i++) {
                SaleProduct dbProduct = dbProducts.get(i);
                if (dbProduct.getId() == reqProduct.getId()) {
                    dbProducts.set(i, reqProduct);
                    break;
                }
            }
        }
        double total = 0;
        for (SaleProduct sp: dbProducts) {
            Product p = productRepo.findById(sp.getProduct()).orElse(null);
            total+= sp.getPrice() * sp.getQuantity();
        }
        saleRepo.save(s);
        saleProductRepo.saveAll(dbProducts);
        saleTransactionRepo.save(currentTransaction);
        return sale;
    }

}
