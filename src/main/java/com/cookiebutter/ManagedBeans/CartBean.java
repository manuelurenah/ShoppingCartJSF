package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;
import com.cookiebutter.PersistenceHandlers.ProductService;
import com.cookiebutter.PersistenceHandlers.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    private double cartTotal;

    @EJB
    ProductService productService;

    @PostConstruct
    public void init() {
        selectedProducts = new ArrayList<>();
        cartTotal = 0.0;
    }

    public String addToCart(Product p, int qty) {
        p.setQuantity(qty);
        this.selectedProducts.add(p);
        return "shoppingCart?faces-redirect=true";
    }

    public String removeFromCart(Product p) {
        this.selectedProducts.remove(p);
        return "shoppingCart?faces-redirect=true";
    }

    public void getTotalPrice() {
        cartTotal = productService.calcTotalPrice(selectedProducts);
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

    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }
}
