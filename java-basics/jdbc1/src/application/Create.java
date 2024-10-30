package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Create {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement statement = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();
            statement = conn.prepareStatement (
            "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + " VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            // to insert multiple values, just put multiple tuples, split by ','
            // ex: " VALUES (?, ?, ?, ?, ?),(?, ?, ?, ?, ?),(?, ?, ?, ?, ?)"

            statement.setString(1, "Arthur Morgan");
            statement.setString(2, "arthur@email.com");
            statement.setDate(3, new Date(sdf.parse("22/10/1885").getTime()));
            statement.setDouble(4, 1000.00);
            statement.setInt(5, 4);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");

            if (rowsAffected > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                while (rs.next()) {
                    System.out.printf("Added ID = %d%n", rs.getInt(1));
                }
            }
        } catch (SQLException | ParseException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
