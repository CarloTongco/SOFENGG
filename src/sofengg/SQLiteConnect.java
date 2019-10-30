/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sofengg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class SQLiteConnect {
    private static Connection conn;
    
    private SQLiteConnect(){
        
    }
    
    public static Connection getConnection(){
        if(conn == null){
            try {
                // db parameters
                String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
                // create a connection to the database
                conn = DriverManager.getConnection(url);
            
                System.out.println("Connection to SQLite has been established.");
            
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return conn;
    }
    
    public static void main(String args[]){
        getConnection();
    }
}
