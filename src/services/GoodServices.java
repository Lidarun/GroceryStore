package services;

import models.Good;

import java.util.ArrayList;
import java.util.List;

public interface GoodServices {
    public void save(String name, double price, int discount, long idTbCategories);
    public void update(long id, String name, double price, int discount, long idTbCategories);
    public List<Good> findAll();
    public Good findById(long id);
    public void delete(long id);

}
