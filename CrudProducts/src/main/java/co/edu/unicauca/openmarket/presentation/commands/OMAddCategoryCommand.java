/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import java.util.List;

/**
 *
 * @author ahurtado
 */
public class OMAddCategoryCommand extends OMCommand {

    private Category addedCategory;
    private CategoryService categoryService;
    boolean result=false;
    public OMAddCategoryCommand(Category addedCategory, CategoryService categoryService){
        this.addedCategory = addedCategory;
        this.categoryService = categoryService;
    }
    
    
    @Override
    public void make() {
        result = categoryService.saveCategory(addedCategory.getCategoryId(),addedCategory.getName());
    }

    @Override
    public void unmake() {
        result = categoryService.deleteCategory(addedCategory.getCategoryId());
    }
    
    public boolean getResult(){
        return result;
    }

    @Override
    public void remake() {
        make();
    }
    
}
