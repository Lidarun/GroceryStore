package services;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetNameById {
    DBHelper dbHelper = new DBHelperImpl();

    public String name(long id, String tbName) {
            String name = null;

            try (PreparedStatement prepStmt = dbHelper.getStmt("SELECT name FROM "+ tbName +" WHERE id = ?")){
                prepStmt.setLong(1, id);

                ResultSet resultSet = prepStmt.executeQuery();
                while (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return name;
    }
}
