package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.Product;
import com.cookiebutter.PersistenceHandlers.ProductService;
import org.omnifaces.cdi.GraphicImageBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Optional;

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
    private ArrayList<UploadedFile> images = new ArrayList<>();



    public void handleFileUpload(FileUploadEvent event) {
        try {
            UploadedFile img = event.getFile();
            getImages().add(img);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ProductService getProductService() {
        return productService;
    }

    public void clear() {
        id = -1;
        name = "";
        description ="";
        images = new ArrayList<>();
    }

    public String editProduct(Product p) {
        this.name = p.getName();
        this.id = p.getId();
        this.description = p.getDescription();
        this.images = p.getImages();

        return "productForm?faces-redirect=true";
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
        p.setImages(getImages());
        p.setDescription(description);
        if(id != -1) {
            productService.update(p);
        }
        else {
            productService.add(p);
        }
        clear();
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


    public ArrayList<UploadedFile> getImages() {
        return images;
    }

    public void setImages(ArrayList<UploadedFile> images) {
        this.images = images;
    }

    public StreamedContent getImage(ArrayList<UploadedFile> images) {
        images = images == null ? this.images : images;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imgName = context.getExternalContext().getRequestParameterMap().get("imgName");
            Optional<UploadedFile> file = images
                    .stream()
                    .filter(uploadedFile -> uploadedFile.getFileName().equals(imgName))
                    .findFirst();


            if(file.isPresent()) {
                return new DefaultStreamedContent(new ByteArrayInputStream(file.get().getContents()));
            }
            else{
                return new DefaultStreamedContent();
            }
        }
    }

    public void removeImage(UploadedFile image) {
        images.remove(image);
    }

    public StreamedContent renderProductImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            int productId = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("productId"));
            Product p = productService.getById(productId);
            String imgName = context.getExternalContext().getRequestParameterMap().get("imgName");
            if(imgName == null) {
                return new DefaultStreamedContent();
            }
            Optional<UploadedFile> file = p.getImages()
                    .stream()
                    .filter(uploadedFile -> uploadedFile.getFileName().equals(imgName))
                    .findFirst();

            if(file.isPresent()) {
                return new DefaultStreamedContent(new ByteArrayInputStream(file.get().getContents()));
            }
            else{
                return new DefaultStreamedContent();
            }
        }
    }
}
