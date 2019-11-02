package sofengg;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Driver {
    public static void main(String[] args) {
        Connection conn = SQLiteConnect.getConnection();
        SQLiteTransactionManager sql = new SQLiteTransactionManager();
        ArrayList<Transaction> tr = new ArrayList<>();

        tr = (ArrayList<Transaction>) sql.viewTransactions();    
        
        for(int i=0; i< tr.size(); i++){
            System.out.println(tr.get(i));
        }
        
    }
}
