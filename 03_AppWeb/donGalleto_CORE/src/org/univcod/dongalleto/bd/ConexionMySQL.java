/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univcod.dongalleto.bd;



import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alexesp
 */
public class ConexionMySQL {

    Connection conn;

    public Connection open() throws Exception {
        String usuario = "root";
        String contrasenia = "root";

        String url = "jdbc:mysql://localhost:3307/dongalleto";

        Class.forName("com.mysql.jdbc.Driver");
        
        conn = DriverManager.getConnection(url, usuario, contrasenia);
       
        return conn;

    }
    
    public void close()
    {
        try
        {
            
                conn.close();
               
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    

}
