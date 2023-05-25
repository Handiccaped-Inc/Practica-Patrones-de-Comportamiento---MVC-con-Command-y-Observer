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
public class OMEditProductCommand extends OMCommand {
    private Product previusState;
    private Product newState;
    private ProductService productService;
    private Long productId;
    boolean result=false;
    
    public OMEditProductCommand(Long productId, Product newState, ProductService productService){
        this.productId = productId;
        this.newState = newState;
        this.productService = productService;
    }
    
    
    @Override
    public void make() {
        //Guardar el estado anterior 
        this.previusState = productService.findProductById(productId);
        //Establecer el nuevo 
        result = productService.editProduct(productId, newState);
    }

    @Override
    public void unmake() {
        //Establecer el anterior estado
        result = productService.editProduct(productId, previusState);
    }
    
    public boolean getResult(){
        return result;
    }

    @Override
    public void remake() {
        make();
    }
    
}
