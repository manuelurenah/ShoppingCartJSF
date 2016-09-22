package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;
import com.cookiebutter.PersistenceHandlers.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by MEUrena on 9/21/16.
 * All rights reserved.
 */
@ManagedBean(name = "productDetail")
@SessionScoped
public class ProductDetailBean implements Serializable {

    private String productId;
    private Product p;

    @EJB
    ProductService productService;

    @PostConstruct
    public void init() {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");

        if (Integer.parseInt(id) != -1) {
            p = productService.getById(Integer.parseInt(id));
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
}
