package DAO;

import java.sql.*;
public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/iBanking";
    private static final String USER="root";
    private static final String PASSWORD="";
    public static Connection getConnect() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
