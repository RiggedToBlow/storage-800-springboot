package com.storage800.demo.test.models;

import com.storage800.demo.test.enums.SaleActionTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("SALE_TRANSACTION")
public class SaleTransaction {
@Id
    int id;
SaleActionTypes actionType;

SaleVM oldValue;
SaleVM newValue;
int sale;
Date actionDate;


    public SaleTransaction(SaleActionTypes actionType, SaleVM oldValue, SaleVM newValue, int sale, Date actionDate) {
        this.actionType = actionType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.sale = sale;
        this.actionDate = actionDate;
    }

    public SaleActionTypes getActionType() {
        return actionType;
    }

    public void setActionType(SaleActionTypes actionType) {
        this.actionType = actionType;
    }

    public SaleVM getOldValue() {
        return oldValue;
    }

    public void setOldValue(SaleVM oldValue) {
        this.oldValue = oldValue;
    }

    public SaleVM getNewValue() {
        return newValue;
    }

    public void setNewValue(SaleVM newValue) {
        this.newValue = newValue;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
