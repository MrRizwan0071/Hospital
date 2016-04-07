package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kaniz
 */
public class Database {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care", "root", "123");
            return con;
        } catch (Exception e) {
            System.out.println("Database getConnection Error...>" + e.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
        }
    }
}

