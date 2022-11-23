package services;

import models.Check;

import java.util.List;

public interface CheckServices {
    public void save(double totalSum);
    public void update(long id, double totalSum);
    public List<Check> findAll();
    public Check findById(long id);
    public void delete(long id);
}
