package services;

import models.StoreCategories;

import java.util.List;

public interface StoreCategoriesServices {
    public void save(long idTbStore, long idTbCategories);
    public void update(long id, long idTbStore, long idTbCategories);
    public List<StoreCategories> findAll();
    public StoreCategories findById(long id);
    public void delete(long id);
}
