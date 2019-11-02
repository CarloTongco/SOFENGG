package sofengg;

import java.sql.Connection;
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
        
        List<Transaction> tr = sql.viewTransactions();    
        
        for(int i=0; i< tr.size(); i++){
            System.out.print(tr.get(i).getDate() + " ");
            System.out.print(tr.get(i).getName() + " ");
            System.out.print(tr.get(i).getHaircut() + " ");
            System.out.println(tr.get(i).getAmount());
        }
    }
}
