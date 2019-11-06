package sofengg;

import java.math.*;
import java.sql.Connection;
import java.time.*;
import java.time.format.*;
import java.util.List;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        Connection conn = SQLiteConnect.getConnection();
        SQLiteTransactionManager sql = new SQLiteTransactionManager();
        
        do{
            System.out.println("Which function do you want to do?");
            System.out.println("[1] - View Transactions");
            System.out.println("[2] - Add Transactions");
            userInput = scan.nextInt();
        }while(userInput != 1 && userInput != 2);
        
        switch(userInput){
            case 1: 
                display();
                break;
            case 2:
                Transaction t = getInput();
                sql.addTransaction(t);
                break;
        }
    }
    
    public static void display(){
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
    
    public static Transaction getInput() {   
        //gets input from the user for name and type of haircut and asks if customer is a PWD
        Scanner input = new Scanner(System.in);
            
        System.out.println("Is customer PWD?[y/n]: ");
        String isPWD = input.nextLine();
        System.out.println("Enter Customer Name: ");
        String name = input.nextLine();
        System.out.println("Enter Type of Haircut[child/bangs/adult/senior]: ");
        String haircut = input.nextLine();
            
        BigDecimal amount = new BigDecimal(0);
        BigDecimal PWDdiscount = new BigDecimal(0.8);
            
        if(isPWD.equalsIgnoreCase("y")){
            if(haircut.equalsIgnoreCase("child")){
                amount = new BigDecimal(300);                
                haircut = "Child/PWD";
            } else if (haircut.equalsIgnoreCase("bangs")){
                amount = new BigDecimal(175);
                amount = amount.multiply(PWDdiscount);
                haircut = "Child/Bangs/PWD";
            } else if (haircut.equalsIgnoreCase("adult")){
                amount = new BigDecimal(170);
                amount = amount.multiply(PWDdiscount);
                haircut = "Adult/PWD";
            } 
        } else {
            if(haircut.equalsIgnoreCase("child")){
                amount = new BigDecimal(350);
                haircut = "Child";
            } else if (haircut.equalsIgnoreCase("bangs")){
                amount = new BigDecimal(175);
                haircut = "Child/Bangs";
            } else if (haircut.equalsIgnoreCase("adult")){
                amount = new BigDecimal(170);              
                haircut = "Adult";
            } else if (haircut.equalsIgnoreCase("senior")){
                amount = new BigDecimal(150);                   
                haircut = "Senior";
            }
        }
        Transaction t = new Transaction(LocalDate.now(), name, haircut, amount);
        return t;
    }
}
