package services;

import models.Categories;
import models.Store;

import java.util.List;

public interface CategoriesServices {
    public void save(String name);
    public void update(Long id, String name);
    public List<Categories> findAll();
    public Categories findByID(long id);
    public void delete(long id);
}
