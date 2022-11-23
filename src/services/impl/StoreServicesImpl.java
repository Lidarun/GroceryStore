package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Store;
import services.StoreServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreServicesImpl implements StoreServices {
    DBHelper dbHelper = new DBHelperImpl();


    @Override
    public void save(String name) {
        try {
            PreparedStatement prepStmt = dbHelper.getStmt("INSERT INTO tb_store(name, add_date, active) VALUES (?,?,?)");
            prepStmt.setString(1, name);
            prepStmt.setString(2, new Date().toString());
            prepStmt.setBoolean(3, true);

            prepStmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long id, String name) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_store SET name = ? WHERE id = ?")) {
            prepStmt.setString(1,name);
            prepStmt.setLong(2, id);

            prepStmt.executeUpdate();
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка обновления данных (tb_store)");
        }
    }

    @Override
    public List<Store> findAll() {
        List<Store> listStore = new ArrayList<>();

        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_store ORDER by ID")) {
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                try {
                    Store store = new Store();

                    store.setId(resultSet.getInt("id"));
                    store.setName(resultSet.getString("name"));
                    store.setAddDate(resultSet.getString("add_date"));
                    store.setActive(resultSet.getBoolean("active"));

                    listStore.add(store);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_store)");
        }
        return listStore;
    }

    @Override
    public Store findByID(long id) {
        Store store = new Store();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_store WHERE id = '" + id +"'")) {
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                store.setId(resultSet.getLong("id"));
                store.setName(resultSet.getString("name"));
                store.setAddDate(resultSet.getString("add_date"));
                store.setActive(resultSet.getBoolean("active"));
            }
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка поиска данных по id (tb_store)");
        }
        return store;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_store SET active = FALSE WHERE tb_store.id = '"+ id +"'")) {

            prepStmt.executeUpdate();

        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления данных (tb_store)");
        }
    }
}
