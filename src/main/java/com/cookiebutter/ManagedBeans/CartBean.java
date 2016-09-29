package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;
import com.cookiebutter.Data.Transaction;
import com.cookiebutter.PersistenceHandlers.ProductService;
import com.cookiebutter.PersistenceHandlers.TransactionService;
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
    @EJB
    TransactionService transactionService;

    @PostConstruct
    public void init() {
        selectedProducts = new ArrayList<>();
        cartTotal = 0.0;
    }

    public String addToCart(Product p, int qty) {
        p.setQuantity(qty);
        this.selectedProducts.add(p);
        productService.setSelectedProducts(selectedProducts);
        return "shoppingCart?faces-redirect=true";
    }

    public String removeFromCart(Product p) {
        this.selectedProducts.remove(p);
        return "shoppingCart?faces-redirect=true";
    }

    public String buy() {
        List<Integer> pId = new ArrayList<>();
        List<Integer> pQty = new ArrayList<>();

        for (Product s : selectedProducts) {
            pId.add(s.getId());
            pQty.add(s.getQuantity());
        }

        Transaction trans = new Transaction(pId, pQty);
        transactionService.add(trans);

        productService.reduceAvailableProducts();

        return "availableProducts?faces-redirect=true";
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
