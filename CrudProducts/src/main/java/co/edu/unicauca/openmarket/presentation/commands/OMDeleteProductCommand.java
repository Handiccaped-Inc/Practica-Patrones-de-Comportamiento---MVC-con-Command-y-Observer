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
public class OMDeleteProductCommand extends OMCommand {

    private Product removedProduct;
    private Long productId;
    private ProductService productService;
    boolean result = false;

    public OMDeleteProductCommand(Long productId, ProductService productService) {
        this.productId = productId;
        this.productService = productService;
    }

    @Override
    public void make() {
        removedProduct = productService.findProductById(productId);
        if (!productService.findAllProducts().isEmpty() && removedProduct != null) {
            result = productService.deleteProduct(removedProduct.getProductId());
        }else{
            result = false;
        }
    }

    @Override
    public void unmake() {
        if (removedProduct != null) {
            result = productService.saveProduct(removedProduct.getProductId(),removedProduct.getName(), removedProduct.getDescription());
        }
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public void remake() {
        make();
    }

}
