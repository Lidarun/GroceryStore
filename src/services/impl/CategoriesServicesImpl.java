package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Categories;
import models.Store;
import services.CategoriesServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoriesServicesImpl implements CategoriesServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(String name) {
        try (PreparedStatement prepStmt = dbHelper.getStmt
                ("INSERT INTO tb_categories(name, add_date, active) VALUES(?,?,?)")) {

            prepStmt.setString(1,name);
            prepStmt.setString( 2, new Date().toString());
            prepStmt.setBoolean(3, true);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка сохранения данных (tb_categories)");
        }
    }

    @Override
    public void update(Long id, String name) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_categories SET name = ? WHERE id = ?")){
            prepStmt.setString(1, name);
            prepStmt.setLong(2, id);

            prepStmt.executeUpdate();
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка обновления данных по id (tb_categories)");
        }
    }

    @Override
    public List<Categories> findAll() {
        List<Categories> categorieses = new ArrayList<>();

        try (PreparedStatement prepStmt =  dbHelper.getStmt("SELECT * FROM tb_categories ORDER BY id")){
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                try {
                        Categories categories = new Categories();

                        categories.setId(resultSet.getInt("id"));
                        categories.setName(resultSet.getString("name"));
                        categories.setAddDate(resultSet.getString("add_date"));
                        categories.setActive(resultSet.getBoolean("active"));

                        categorieses.add(categories);

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извления данных (tb_categories)");
        }
        return categorieses;
    }

    @Override
    public Categories findByID(long id) {
        Categories categories = new Categories();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_categories WHERE id = ?")){
            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                categories.setId(resultSet.getInt("id"));
                categories.setName(resultSet.getString("name"));
                categories.setAddDate(resultSet.getString("add_date"));
                categories.setActive(resultSet.getBoolean("active"));

            }
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка получения данных по id (tb_categories)");
        }
        return categories;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_categories SET active = FALSE WHERE id = ?")){
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();

        }catch (SQLException throwables) {
            throw new SqlException("Ошибка удаления данных по id (tb_categories)");
        }
    }
}
