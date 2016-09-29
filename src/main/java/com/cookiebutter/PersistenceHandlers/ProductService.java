package com.cookiebutter.PersistenceHandlers;

import com.cookiebutter.Data.Product;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luis on 21/09/16.
 */
@Stateless
public class ProductService {
    public enum ProductFilter {
        NAME, DESCRIPTION, PUBLISHED_BY
    }

    public static int IdCounter = 0;

    private List<Product> products;
    private List<Product> filteredProducts;
    private List<Product> selectedProducts;

    public ProductService() { }

//    @Override
    public Boolean add(Product item) {
        Boolean added = products.add(item);
        if (added) {
            setProducts(products);
        }

        return added;
    }

    public Product addToSelected(Product item) {
        selectedProducts.add(item);
        setSelectedProducts(selectedProducts);
        return item;
    }

//    @Override
    public Product update(Product item) {
        deleteById(item.getId());
        add(item);
        return null;
    }

//    @Override
    public Product getById(int id) {
        for (Product p: products) {
            if(p.getId() == id) {
                return p;
            }
        }

        return null;
    }

//    @Override
    public Boolean deleteById(int id) {
        for (Product p: products) {
            if(p.getId() == id) {
                products.remove(p);
                setProducts(products);
                return true;
            }
        }
        return false;
    }

//    @Override
    public Boolean delete(Product item) {
        Boolean deleted = products.remove(item);
        if(deleted) {
            setProducts(products);
        }

        return deleted;
    }

    public Boolean removeFromSelected(int id) {
        for (Product p: selectedProducts) {
            if (p.getId() == id) {
                Boolean deleted = selectedProducts.remove(p);
                if (deleted) {
                    setSelectedProducts(selectedProducts);
                }

                return deleted;
            }
        }

        return false;
    }

    public List<Integer> getQtyRange(int id) {
        Product p = getById(id);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= p.getQuantity(); i++) {
            list.add(i);
        }

        return list;
    }

    public double calcTotalPrice(List<Product> selected) {
        double total = 0.0;
        for (Product p : selected) {
            total += p.getPrice() * p.getQuantity();
        }

        return total;
    }

    public void reduceAvailableProducts(List<Product> selection) {
        products = getProducts();
        for (Product select : selection) {
            Iterator<Product> iterator = products.iterator();
            while(iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId() == select.getId()) {
                    int newQty = p.getQuantity() - select.getQuantity();
                    if (newQty <= 0) {
                        delete(p);
                    } else {
                        p.setQuantity(newQty);
                        update(p);
                    }
                }
            }
        }
    }

    public List<Product> getProducts() {
        products = (ArrayList<Product>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("products");
        if(products == null) {
            products = new ArrayList<>();
        }
        filteredProducts = products;
        return products;
    }

    public List<Product> getProducts(int page, int count) {
        return  filteredProducts.subList(Math.min(page*count, products.size() - 1), Math.min(page*count + count, products.size() - 1));
    }


    public void filterBy(ProductFilter filter, String query) {
        if(query.equals("")) {
            filteredProducts = products;
        }
        switch (filter) {
            case NAME:
                filteredProducts = products
                        .stream()
                        .filter(product -> product.getName().contains(query))
                        .collect(Collectors.toList());
                break;
            case DESCRIPTION:
                filteredProducts = products
                        .stream()
                        .filter(product -> product.getDescription().contains(query))
                        .collect(Collectors.toList());
                break;
            case PUBLISHED_BY:
                filteredProducts = products
                        .stream()
                        .filter(product ->
                                product.getPublishedBy().getUsername().contains(query) ||
                                product.getPublishedBy().getEmail().contains(query))
                        .collect(Collectors.toList());
                break;
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("products", this.products);
    }

    public List<Product> getSelectedProducts() {
        selectedProducts = (ArrayList<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedProducts");
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>();
        }
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedProducts", this.selectedProducts);
    }
}
