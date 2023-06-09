/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import java.util.List;

/**
 *
 * @author ahurtado
 */
public class OMAddProductCommand extends OMCommand {

    private Product addedProduct;
    private ProductService productService;
    boolean result=false;
    public OMAddProductCommand(Product addedProduct, ProductService productService){
        this.addedProduct = addedProduct;
        this.productService = productService;
    }
    
    
    @Override
    public void make() {
        result = productService.saveProduct(addedProduct.getProductId(),addedProduct.getName(), addedProduct.getDescription());
    }

    @Override
    public void unmake() {
        result = productService.deleteProduct(addedProduct.getProductId());
    }
    
    public boolean getResult(){
        return result;
    }

    @Override
    public void remake() {
        make();
    }
    
}
