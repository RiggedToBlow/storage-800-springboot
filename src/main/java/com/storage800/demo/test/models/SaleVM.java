package com.storage800.demo.test.models;

import jakarta.validation.constraints.NotEmpty;

public class SaleVM {
    @NotEmpty
    Sale sale;

    @NotEmpty
    Iterable<SaleProduct> saleProduct;


public SaleVM(Sale sale, Iterable<SaleProduct> saleProduct) {
        this.sale = sale;
        this.saleProduct = saleProduct;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Iterable<SaleProduct> getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(Iterable<SaleProduct> saleProduct) {
        this.saleProduct = saleProduct;
    }


}
