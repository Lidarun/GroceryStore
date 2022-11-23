package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Store;
import models.StoreCategories;
import services.StoreCategoriesServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

public class StoreCategoriesServicesImpl implements StoreCategoriesServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(long idTbStore, long idTbCategories) {
        try (PreparedStatement prepStmt = dbHelper.getStmt
                    ("INSERT INTO tb_store_categories(id_tb_store, id_tb_categories, active) VALUES (?,?,?)")) {

            prepStmt.setLong(1, idTbStore);
            prepStmt.setLong(2, idTbCategories);
            prepStmt.setBoolean(3, true);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка сохранения данных (tb_store_categories)");
        }
    }

    @Override
    public void update(long id, long idTbStore, long idTbCategories) {
        try (PreparedStatement prepStmt = dbHelper.getStmt
                ("UPDATE tb_store_categories SET id_tb_store = ?, id_tb_categories = ? WHERE id = ?")) {

            prepStmt.setLong(1, idTbStore);
            prepStmt.setLong(2, idTbCategories);
            prepStmt.setLong(3, id);

            prepStmt.executeUpdate();
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка обновления данных по id (tb_goods)");
        }
    }

    @Override
    public List<StoreCategories> findAll() {
        List<StoreCategories> storeCategoriesList = new ArrayList<>();

        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_store_categories ORDER BY id")) {
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                StoreCategories storeCategories = new StoreCategories();
                try {
                    storeCategories.setId(resultSet.getLong("id"));
                    storeCategories.setIdStore(resultSet.getLong("id_tb_store"));
                    storeCategories.setIdCategories(resultSet.getLong("id_tb_categories"));
                    storeCategories.setActive(resultSet.getBoolean("active"));

                    storeCategoriesList.add(storeCategories);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_goods)");
        }
        return storeCategoriesList;
    }

    @Override
    public StoreCategories findById(long id) {
        StoreCategories storeCategories = new StoreCategories();

        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_store_categories WHERE id = ?")) {

            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                storeCategories.setId(resultSet.getInt("id"));
                storeCategories.setIdStore(resultSet.getInt("id_tb_store"));
                storeCategories.setIdCategories(resultSet.getInt("id_tb_categories"));
                storeCategories.setActive(resultSet.getBoolean("active"));
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных по id (tb_goods)");
        }
        return storeCategories;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_store_categories SET active = false WHERE id = ?")) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления данных (tb_store_categories");
        }

    }
}
