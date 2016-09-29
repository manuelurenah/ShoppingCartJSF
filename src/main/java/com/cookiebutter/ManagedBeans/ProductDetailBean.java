package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Comment;
import com.cookiebutter.Data.Product;
import com.cookiebutter.PersistenceHandlers.ProductService;
import com.cookiebutter.PersistenceHandlers.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by MEUrena on 9/21/16.
 * All rights reserved.
 */
@ManagedBean(name = "productDetail")
@SessionScoped
public class ProductDetailBean implements Serializable {

    private String productId;
    private String imgName;
    private List<Integer> qtyRange;
    private int selectedQty;
    private Product p;

    //form:
    private int rating;
    private String comment;


    @EJB
    ProductService productService;
    @EJB
    UserService userService;

    public String viewProduct(int productId) {
        p = productService.getById(productId);
        qtyRange = productService.getQtyRange(p.getId());
        return "productView?faces-redirect=true";
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public List<Integer> getQtyRange() {
        return qtyRange;
    }

    public void setQtyRange(List<Integer> qtyRange) {
        this.qtyRange = qtyRange;
    }

    public int getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(int selectedQty) {
        this.selectedQty = selectedQty;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void saveComment() {
        // got here
        Comment cmt = new Comment();
        cmt.setComment(this.getComment());
        cmt.setRating(this.getRating());
        cmt.setProduct(this.getP());
        cmt.setUser(userService.getCurrentUser());
        p.getComments().add(cmt);
        productService.update(p);

        setComment("");
        setRating(0);

    }
}
