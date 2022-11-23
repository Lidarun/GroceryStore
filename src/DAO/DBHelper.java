package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DBHelper {
    PreparedStatement getStmt(String sql) throws SQLException;
}
