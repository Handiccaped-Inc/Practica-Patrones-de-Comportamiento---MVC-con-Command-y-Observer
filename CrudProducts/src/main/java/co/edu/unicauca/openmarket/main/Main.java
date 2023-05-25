
package co.edu.unicauca.openmarket.main;

import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.edu.unicauca.openmarket.presentation.GUIMain;
import co.edu.unicauca.openmarket.presentation.GUIProducts;
import co.edu.unicauca.openmarket.presentation.GUIProductsFind;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IProductRepository productRepository = Factory.getInstance().getProductRepository("default");
        ICategoryRepository categoryRepository = Factory.getInstance().getCategoryRepository("default");
        ProductService productService = new ProductService(productRepository);
        CategoryService categoryService = new CategoryService(categoryRepository);
        
        GUIMain mainGui = new GUIMain(productService, categoryService);
        mainGui.setVisible(true);
        
        
    }
    
}
