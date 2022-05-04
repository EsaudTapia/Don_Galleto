package org.univcod.dongalleto.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.univcod.dongalleto.bd.ConexionMySQL;
import org.univcod.dongalleto.model.Persona;
import org.univcod.dongalleto.model.Proveedor;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author franc
 */
public class ControladorProveedor {

    public int insert(Proveedor p) throws Exception {
        // Definimos la instruccion SQL dentro de un String Java:

        String sql = "{call insertProveedor(?, ?, ?, ?, ?, "
                + //Datos de Persona
                "?, ?, "
                + //Datos de Proveedor
                "?, ?)}"; //Valores de retorno
        // Nos conectamos a la BD:
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        // Utilizando la conexion con MySQL, creamos un objeto que nos permita
        // invocar el Stored Procedure que hara la insercion en las tablas
        // persona y proveedor:
        CallableStatement cstmt = conn.prepareCall(sql);

        //Llenamos los datos de Persona de acuerdo con los parámetros que pide
        //el Stored Procedure:
        cstmt.setString(1, p.getPersona().getNombre());
        cstmt.setString(2, p.getPersona().getApellidoPaterno());
        cstmt.setString(3, p.getPersona().getApellidoMaterno());
        cstmt.setString(4, p.getPersona().getDomicilio());
        cstmt.setString(5, p.getPersona().getTel1());
        cstmt.setString(6, p.getRfc());
        cstmt.setString(7, p.getRazonSocial());

        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(8, Types.INTEGER);

        cstmt.registerOutParameter(9, Types.INTEGER);

        //Ejecutamos la consulta:
        cstmt.execute();

        //Recuperamos los ID's generados:
        p.getPersona().setId(cstmt.getInt(8));

        p.setId(cstmt.getInt(9));

        //Cerramos los objetos de BD:
        cstmt.close();

        connMySQL.close();

        //Devolvemos el ID del Proveedor generado:
        return p.getId();

    }

    public List<Proveedor> getall(String filtro) throws Exception {
// Definimos la consulta SQL:

        String sql = "SELECT * FROM v_proveedores";

        //Abrimos una conexion con MySQL:
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        // Generamos un objeto que nos permita ejecutar la consulta
        // de manera segura, a diferencia de un Statement:
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Ejecutamos la consulta y obtenemos sus resultados:
        ResultSet rs = pstmt.executeQuery();

        // Declaramos una lista dinamica para guardar los objetos que
        // generaremos al recorrer los resultados devueltos por la BD:
        List<Proveedor> proveedores = new ArrayList<>();

        Proveedor p = null;

        //Recorremos el conjunto de registros devuelto por la BD:
        while (rs.next()) {

            //Obtenemos un objeto de tipo Mercancia con los datos que se
            //encuentran en el registro actual, devuelto por la BD:
            p = fill(rs);

            //Agregamos el objeto a la lista:
            proveedores.add(p);
        }

        //Devolvemos la lista de objetos consultados:
        return proveedores;
    }

    public Proveedor fill(ResultSet rs) throws Exception {
        Proveedor prov = new Proveedor();

        Persona p = new Persona();

        //Llenamos los datos de Producto:
        p.setId(rs.getInt("idPersona"));

        p.setNombre(rs.getString("nombre"));

        p.setApellidoPaterno(rs.getString("apellidoPaterno"));

        p.setApellidoMaterno(rs.getString("apellidoMaterno"));

        p.setDomicilio(rs.getString("domicilio"));
       
        p.setTel1(rs.getString("tel1"));   

        p.setEstatus(rs.getInt("estatus"));

        prov.setId(rs.getInt("idProveedor"));

        prov.setRfc(rs.getString("rfc"));

        prov.setRazonSocial(rs.getString("razonSocial"));

        prov.setPersona(p);

        return prov;
    }
}
