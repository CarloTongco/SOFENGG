/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sofengg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SQLiteTransactionManager implements DBTransactionManager{

    PreparedStatement st;
    String query;
    Connection conn = SQLiteConnect.getConnection();
    ResultSet rs;
    private ArrayList<Transaction> transactionList;
    
    @Override
    public List<Transaction> viewTransactions() {
        
        try {
            st = conn.prepareStatement(query);
            
            query = "SELECT * FROM `Transactions`";
                        
            if(st.executeUpdate() > 0)
            {
                System.out.println("Query successful");
                rs = st.executeQuery(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return transactionList;
    }

    @Override
    public List<Transaction> viewTransactions(int month, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> viewTransaction(int month, int day, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTransaction(Transaction t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getMonthlySales(int month, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
