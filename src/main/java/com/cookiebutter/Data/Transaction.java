package com.cookiebutter.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by MEUrena on 9/29/16.
 * All rights reserved.
 */
public class Transaction {

    private int id;
    private List<Integer> productsIds;
    private List<Integer> productsQty;
    private User createdBy;
    private Date date;
    private double transactionTotal;

    public Transaction(List<Integer> productsIds, List<Integer> productsQty, User createdBy, Date date, double transactionTotal) {
        this.productsIds = productsIds;
        this.productsQty = productsQty;
        this.createdBy = createdBy;
        this.date = date;
        this.transactionTotal = transactionTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }

    public List<Integer> getProductsQty() {
        return productsQty;
    }

    public void setProductsQty(List<Integer> productsQty) {
        this.productsQty = productsQty;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(double transactionTotal) {
        this.transactionTotal = transactionTotal;
    }
}
