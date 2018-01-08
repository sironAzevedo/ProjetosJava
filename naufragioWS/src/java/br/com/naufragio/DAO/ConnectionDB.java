package br.com.naufragio.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADM
 */
public class ConnectionDB {
    private static final String NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/mergulho";
    private static final String USERS = "root";
    private static final String PASSWORD = "root";

    public static Connection obterConexao() throws SQLException{
        
        try {
            Class.forName(NAME); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return DriverManager.getConnection(URL, USERS, PASSWORD);
        
    } 
}
