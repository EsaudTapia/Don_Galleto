/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univcod.dongalleto.bd;

import java.sql.Connection;

/**
 *
 * @author franc
 */
public class probar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       ConexionMySQL connexion = new ConexionMySQL();
     Connection conn = connexion.open();
     
        System.out.println(""+conn);
    }
    
}
