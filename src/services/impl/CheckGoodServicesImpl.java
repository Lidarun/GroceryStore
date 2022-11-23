package services.impl;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import exceptions.SqlException;
import models.CheckGood;
import services.CheckGoodServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckGoodServicesImpl implements CheckGoodServices {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public void save(long idTbChecks, long idTbGoods, int count) {
        try (PreparedStatement prepStmt = dbHelper.getStmt
                ("INSERT INTO tb_check_goods(id_tb_checks, id_tb_goods, count, active) VALUES (?,?,?,?)")) {

            prepStmt.setLong(1, idTbChecks);
            prepStmt.setLong(2, idTbGoods);
            prepStmt.setInt(3, count);
            prepStmt.setBoolean(4, true);

            prepStmt.executeUpdate();

        }catch (SQLException throwables){
            throw new SqlException("Ошибка сохранения данных (tb_check_goods)");
        }
    }

    @Override
    public void update(long id, long idTbChecks, long idTbGoods, int count) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_check_goods SET id_tb_checks = ?, id_tb_goods = ?, count = ? WHERE id = ?")){

            prepStmt.setLong(1, idTbChecks);
            prepStmt.setLong(2, idTbGoods);
            prepStmt.setLong(3, id);
            prepStmt.setLong(4, count);

            prepStmt.executeUpdate();

        }catch (SQLException throwables){
            throw new SqlException("Ошибка обновления данных по id (tb_check_goods)");
        }
    }

    @Override
    public List<CheckGood> findAll() {
        List<CheckGood> checkGoodList = new ArrayList<>();

        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_check_goods ORDER BY id")){
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()){
                try {
                    CheckGood checkGood = new CheckGood();
                    checkGood.setId(resultSet.getLong("id"));
                    checkGood.setIdCheck(resultSet.getLong("id_tb_checks"));
                    checkGood.setIdGood(resultSet.getLong("id_tb_goods"));
                    checkGood.setCount(resultSet.getInt("count"));
                    checkGood.setActive(resultSet.getBoolean("active"));

                    checkGoodList.add(checkGood);

                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных (tb_check_goods)");
        }
        return checkGoodList;
    }

    @Override
    public CheckGood findById(long id) {
        CheckGood checkGood = new CheckGood();
        try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT * FROM tb_check_goods WHERE id = ?")){
            prepStmt.setLong(1, id);

            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                checkGood.setId(resultSet.getLong("id"));
                checkGood.setIdCheck(resultSet.getLong("id_tb_checks"));
                checkGood.setIdGood(resultSet.getLong("id_tb_goods"));
                checkGood.setCount(resultSet.getInt("count"));
                checkGood.setActive(resultSet.getBoolean("active"));
            }
        }catch (SQLException throwables){
            throw new SqlException("Ошибка извлечения данных по id (tb_check_goods)");
        }
        return checkGood;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement prepStmt = dbHelper.getStmt("UPDATE tb_check_goods SET active = false WHERE id = ?")){
            prepStmt.setLong(1, id);

            prepStmt.executeUpdate();

        }catch (SQLException throwables){
            throw new SqlException("Ошибка удаления данных (tb_check_goods)");
        }
    }
}
