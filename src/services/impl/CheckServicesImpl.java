package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.Check;
import services.CheckServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckServicesImpl implements CheckServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(double totalSum) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("INSERT INTO tb_checks(total_sum, add_date, active) VALUES (?,?,?)")){

            prepStmt.setDouble(1, totalSum);
            prepStmt.setString(2, new Date().toString());
            prepStmt.setBoolean(3, true);

            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка сохранения данных (tb_categories)");
        }
    }

    @Override
    public void update(long id, double totalSum) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_checks SET total_sum = ? WHERE id = ?")) {

            prepStmt.setDouble(1, totalSum);
            prepStmt.setLong(2, id);

            prepStmt.executeUpdate();
        }catch (SQLException throwables) {
            throw new SqlException("Ошибка обновления данных по id (tb_categories)");
        }
    }

    @Override
    public List<Check> findAll() {
        List<Check> checkList = new ArrayList<>();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_checks ORDER BY id")){
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()){
                try {
                    Check check = new Check();

                    check.setId(resultSet.getLong("id"));
                    check.setTotalSum(resultSet.getDouble("total_sum"));
                    check.setAddDate(resultSet.getString("add_date"));
                    check.setActive(resultSet.getBoolean("active"));

                    checkList.add(check);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_categories)");
        }
        return checkList;
    }

    @Override
    public Check findById(long id) {
        Check check = new Check();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_checks WHERE id = ?")) {
            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                check.setId(resultSet.getLong("id"));
                check.setTotalSum(resultSet.getDouble("total_sum"));
                check.setAddDate(resultSet.getString("add_date"));
                check.setActive(resultSet.getBoolean("active"));
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных по id (tb_categories)");
        }
        return check;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_checks SET active = false WHERE id = ?")) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления данных по id (tb_categories)");
        }
    }
}
