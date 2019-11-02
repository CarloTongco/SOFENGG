/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sofengg;

import java.math.BigDecimal;
import java.time.*;
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
    ArrayList<Transaction> transactionList = new ArrayList<>();
    
    @Override
    public List<Transaction> viewTransactions() {
        
        try {
            
            query = "SELECT * FROM `Transactions`";
            st = conn.prepareStatement(query);
            
            rs = st.executeQuery();
                
            while(rs.next()){
                //convert string date to LocalDate
                String localDate = rs.getString(2);
                Instant instant = Instant.parse(localDate);
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
                LocalDate date = dateTime.toLocalDate();
                //name
                String name = rs.getString(3);
                //haircut type
                String haircut = rs.getString(4);
                //amount paid
                BigDecimal amount = rs.getBigDecimal(5);
                    
                Transaction tr = new Transaction(date, name, haircut, amount);
                //add the information to an array list
                transactionList.add(tr);
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
