package services;

import models.Store;

import java.util.List;
public interface StoreServices {
    public void save(String name);
    public void update(long id, String name);
    public List<Store> findAll();
    public Store findByID(long id);
    public void delete(long id);
}
