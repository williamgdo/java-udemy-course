package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Transaction {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);

            statement = conn.createStatement ();

            int rowsAffected1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int x = 1;
            if (x < 2)
                throw new SQLException("Fake Error");

            int rowsAffected2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println(rowsAffected1 + " row(s) affected by UPDATE 1.");
            System.out.println(rowsAffected2 + " row(s) affected by UPDATE 2.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
