package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/freelancehub"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=America/Sao_Paulo"
                    + "&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "Angolo10";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL nao encontrado.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean testarConexao() {
        try (Connection conn = getConnection()) {
            return conn.isValid(3);
        } catch (SQLException e) {
            return false;
        }
    }
}
