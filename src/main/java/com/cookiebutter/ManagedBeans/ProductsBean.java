package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;
import com.cookiebutter.PersistenceHandlers.ProductService;
import org.primefaces.event.FileUploadEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 * Created by luis on 21/09/16.
 */
@ManagedBean(name = "productsBean")
@SessionScoped
public class ProductsBean {
    @EJB
    private ProductService productService;

    private int id = -1;
    private String name;
    private String description;
    private ArrayList<byte[]> images = new ArrayList<byte[]>();



    public void handleFileUpload(FileUploadEvent event) {
        System.out.println(event.getFile().getFileName());
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " was uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        getImages().add(event.getFile().getContents());

    }

    public ProductService getProductService() {
        return productService;
    }

    public String processForm() {
        Product p;
        if(id != -1) {
            p = productService.getById(id);
        }
        else {
            p = new Product();
        }

        p.setName(name);
        p.setImages(images);
        p.setDescription(description);
        if(id != -1) {
            productService.update(p);
        }
        else {
            productService.add(p);
        }
        return "availableProducts?faces-redirect=true";
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<byte[]> getImages() {
        return images;
    }

    public void setImages(ArrayList<byte[]> images) {
        this.images = images;
    }
}
