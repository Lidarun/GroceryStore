package DAO.iml;

import DAO.DBHelper;
import exceptions.SqlException;

import java.sql.*;

public class DBHelperImpl implements DBHelper {
    @Override
    public PreparedStatement getStmt(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5555/grocery_store","postgres", "Lidarun005");
        return connection.prepareStatement(sql);
    }
}
