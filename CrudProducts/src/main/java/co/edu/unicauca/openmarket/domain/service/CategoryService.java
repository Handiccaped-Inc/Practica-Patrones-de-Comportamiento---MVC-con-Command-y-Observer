package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.Category;
import java.util.ArrayList;
import java.util.List;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author Libardo, Julio
 */
public class CategoryService extends Observado {

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private ICategoryRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de ICategoryRepository
     */
    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }

    public boolean saveCategory(Long id, String name) {

        Category newCategory = new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);

        //Validate product
        if (newCategory.getName().isEmpty()) {
            return false;
        }
        boolean respuesta = repository.save(newCategory);
        this.notificar();
        return respuesta;
    }

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories = repository.findAll();;

        return categories;
    }

    public Category findCategoryById(Long id) {
        return repository.findById(id);
    }

    public boolean deleteCategory(Long id) {
        boolean result;
        result = repository.delete(id);
        this.notificar();
        return result;
    }

    public boolean editCategory(Long categoryId, Category cat) {

        //Validate product
        if (cat == null || cat.getName().isEmpty()) {
            return false;
        }
        boolean result = repository.edit(categoryId, cat);
        this.notificar();
        return result;
    }

}
