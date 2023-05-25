/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahurtado
 */
public class OMAddCategoryCommand extends OMCommand {

    private Category addedCategory;
    private CategoryService categoryService;
    private ProductService productService;
    private ArrayList<Product> productsToAdd;
    private ArrayList<Product> previusStateProducts;

    boolean result = false;

    public OMAddCategoryCommand(Category addedCategory, CategoryService categoryService, ProductService productService) {
        this.addedCategory = addedCategory;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void make() {
        result = categoryService.saveCategory(addedCategory.getCategoryId(), addedCategory.getName());
        if (productsToAdd != null) {
            previusStateProducts = new ArrayList<>();
            for (Product product : productsToAdd) {
                Product prevStateProduct = new Product(product.getProductId(), product.getName(), product.getDescription(), 0);
                prevStateProduct.setCategory(product.getCategory());
                previusStateProducts.add(prevStateProduct);
                product.setCategory(addedCategory);
                productService.editProduct(product.getProductId(), product);
            }
        }
    }

    @Override
    public void unmake() {
        if (previusStateProducts != null) {
            for (Product product : previusStateProducts) {
                productService.editProduct(product.getProductId(), product);
            }
        }
        result = categoryService.deleteCategory(addedCategory.getCategoryId());
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public void remake() {
        make();
    }

    public ArrayList<Product> getProductsToAdd() {
        return productsToAdd;
    }

    public void setProductsToAdd(ArrayList<Product> productsToAdd) {
        this.productsToAdd = productsToAdd;
    }

    public Category getAddedCategory() {
        return addedCategory;
    }

    
}
