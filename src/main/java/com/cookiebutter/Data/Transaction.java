package com.cookiebutter.Data;

import java.util.List;

/**
 * Created by MEUrena on 9/29/16.
 * All rights reserved.
 */
public class Transaction {

    private int id;
    private List<Integer> productsIds;
    private List<Integer> productsQty;

    public Transaction(List<Integer> productsIds, List<Integer> productsQty) {
        this.productsIds = productsIds;
        this.productsQty = productsQty;
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

}
