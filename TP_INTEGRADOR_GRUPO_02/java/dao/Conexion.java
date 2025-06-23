package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String HOST = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DBNAME = "bdbancobg2?useSSL=false&serverTimezone=UTC";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(HOST + DBNAME, USER, PASS);
    }
}

