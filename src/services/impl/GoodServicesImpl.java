package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Good;
import services.GoodServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GoodServicesImpl implements GoodServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(String name, double price, int discount, long idTbCategories) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("INSERT INTO tb_goods(name, price, discount, add_date, id_tb_categories, active) VALUES (?,?,?,?,?,?)")) {

            prepStmt.setString(1, name);
            prepStmt.setDouble(2, price);
            prepStmt.setInt(3, discount);
            prepStmt.setString(4, new Date().toString());
            prepStmt.setLong(5, idTbCategories);
            prepStmt.setBoolean(6, true);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка сохранения данных (tb_goods)");
        }
    }

    @Override
    public void update(long id, String name, double price, int discount, long idTbCategories) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_goods SET name = ?, price = ?, discount = ?, id_tb_categories = ? WHERE id = ?")) {

            prepStmt.setString(1, name);
            prepStmt.setDouble(2, price);
            prepStmt.setInt(3, discount);
            prepStmt.setLong(4, idTbCategories);
            prepStmt.setLong(5, id);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка обновления данных по id (tb_goods)");
        }
    }

    @Override
    public List<Good> findAll() {
        List<Good> goodList = new ArrayList<>();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_goods")) {
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()){
                try {
                    Good good = new Good();

                    good.setId(resultSet.getLong("id"));
                    good.setName(resultSet.getString("name"));
                    good.setPrice(resultSet.getDouble("price"));
                    good.setDiscount(resultSet.getInt("discount"));
                    good.setAddDate(resultSet.getString("add_date"));
                    good.setIdCategories(resultSet.getLong("id_tb_categories"));
                    good.setActive(resultSet.getBoolean("active"));

                    goodList.add(good);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_goods)");
        }
        return goodList;
    }

    @Override
    public Good findById(long id) {
        Good good = new Good();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_goods WHERE id = ?")) {
            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                good.setId(resultSet.getLong("id"));
                good.setName(resultSet.getString("name"));
                good.setPrice(resultSet.getDouble("price"));
                good.setDiscount(resultSet.getInt("discount"));
                good.setIdCategories(resultSet.getLong("id_tb_categories"));
                good.setAddDate(resultSet.getString("add_date"));
                good.setActive(resultSet.getBoolean("active"));
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных по id (tb_goods)");
        }
        return good;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_goods SET active = false WHERE id = ?")) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления по id (tb_goods)");
        }
    }
}
