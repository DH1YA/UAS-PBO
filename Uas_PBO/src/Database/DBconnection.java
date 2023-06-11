/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;
import javax.swing.JOptionPane;    

public class DBconnection {
   Connection conn;
   
   public DBconnection(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_amplangpbo", "root", "");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
    
   public Connection getConnection(){
       return conn;
   }
}
