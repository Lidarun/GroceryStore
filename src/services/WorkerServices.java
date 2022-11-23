package services;

import models.Worker;

import java.util.List;

public interface WorkerServices {
    public void save(String fullName, String login, String password, long idTbStore);
    public void update(long id, String fullName, String login, String password, long idTbStore);
    public List<Worker> findAll();
    public Worker findById(long id);
    public void delete(long id);
}
