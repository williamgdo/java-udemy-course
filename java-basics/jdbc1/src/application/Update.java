package application;

import db.DB;
import db.DbException;

import java.sql.*;

public class Update {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "UPDATE seller SET BaseSalary = BaseSalary + ? "
                        + "WHERE DepartmentId = ?"
            );
            st.setDouble(1, 9999.0);
            st.setInt(2, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
