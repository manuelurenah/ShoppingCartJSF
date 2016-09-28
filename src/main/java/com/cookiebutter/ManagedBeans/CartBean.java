package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MEUrena on 9/27/16.
 * All rights reserved.
 */

@ManagedBean(name = "cart")
@SessionScoped
public class CartBean {

    private List<Product> selectedProducts;

    @PostConstruct
    public void init() {
        selectedProducts = new ArrayList<>();
    }

    public String addToCart(Product p) {
        this.selectedProducts.add(p);
        return "shoppingCart?faces-redirect=true";
    }

    public String removeFromCart(Product p) {
        this.selectedProducts.remove(p);
        return "shoppingCart?faces-redirect=true";
    }

    public List<Product> getSelectedProducts() {
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>();
        }
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
}
