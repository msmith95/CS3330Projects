/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class DBHelper {
    public static Connection connection;
    
    public DBHelper(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tasks.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("create table if not exists stocks (ticker string PRIMARY KEY, name string, exchange string)");
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean insert(Company company){
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //System.out.println("insert into stocks('ticker', 'name', 'exchange') VALUES('"+company.getTicker()+"', '"+company.getName()+"', '"+company.getStockExchange()+"')");
            statement.execute("insert into stocks('ticker', 'name', 'exchange') VALUES('"+company.getTicker()+"', '"+company.getName()+"', '"+company.getStockExchange()+"')");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static ArrayList<Company> getCompanies(){
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("select * from stocks");
            ArrayList<Company> companies = new ArrayList<>();
            while(rs.next()){
                Company c = new Company();
                c.setName(rs.getString("name"));
                c.setTicker(rs.getString("ticker"));
                c.setStockExchange(rs.getString("exchange"));
                companies.add(c);
            }
            return companies;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static boolean remove(Company company){
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.execute("delete from stocks where ticker='"+company.getTicker() + "'");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
