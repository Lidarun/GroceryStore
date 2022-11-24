package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Worker;
import services.StoreServices;
import services.WorkerServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerServicesImpl implements WorkerServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(String fullName, String login, String password, long idTbStore) {
        try (PreparedStatement prepStmt = dbHelper.getStmt
                ("INSERT INTO tb_workers(full_name, login, password, add_date, active, id_tb_store) VALUES (?,?,?,?,?,?)")) {

            prepStmt.setString(1, fullName);
            prepStmt.setString(2, login);
            prepStmt.setString(3, password);
            prepStmt.setString(4, new Date().toString());
            prepStmt.setBoolean(5, true);
            prepStmt.setLong(6, idTbStore);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
//            throw new RuntimeException(e);
            throw new SqlException("Ошибка сохранения данных (tb_workers)");
        }
    }

    @Override
    public void update(long id, String fullName, String login, String password, long idTbStore) {
        try (PreparedStatement prepStmt = dbHelper.getStmt(
                "UPDATE tb_workers SET full_name = ?, login = ?, password = ?, id_tb_store = ? WHERE id = ?")) {

            prepStmt.setString(1, fullName);
            prepStmt.setString(2, login);
            prepStmt.setString(3, password);
            prepStmt.setLong(4, idTbStore);
            prepStmt.setLong(5, id);

            prepStmt.executeUpdate();

        }catch (SQLException throwables){
            throw new SqlException("Ошибка обновления данных (tb_workers)");
        }
    }

    @Override
    public List<Worker> findAll() {
        StoreServices storeServices = new StoreServicesImpl();
        List<Worker> workerList = new ArrayList<>();

        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_workers ORDER BY id")) {
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()){
                try {
                    Worker worker = new Worker();

                    worker.setId(resultSet.getInt("id"));
                    worker.setName(resultSet.getString("full_name"));
                    worker.setLogin(resultSet.getString("login"));
                    worker.setPassword(resultSet.getString("password"));
                    worker.setAddDate(resultSet.getString("add_date"));
                    worker.setActive(resultSet.getBoolean("active"));
                    worker.setIdStore(storeServices.findByID(resultSet.getInt("id_tb_store")));

                    workerList.add(worker);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_workers");
        }
        return workerList;
    }

    @Override
    public Worker findById(long id) {
        StoreServices storeServices = new StoreServicesImpl();
        Worker worker = new Worker();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_workers WHERE id = ?")) {
            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                worker.setId(resultSet.getInt("id"));
                worker.setName(resultSet.getString("full_name"));
                worker.setLogin(resultSet.getString("login"));
                worker.setPassword(resultSet.getString("password"));
                worker.setAddDate(resultSet.getString("add_date"));
                worker.setActive(resultSet.getBoolean("active"));
                worker.setIdStore(storeServices.findByID(resultSet.getInt("id_tb_store")));
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных по id (tb_workers)");
        }
        return worker;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_workers SET active = false WHERE id = ?")) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления данных по id (tb_workers)");
        }

    }
}
