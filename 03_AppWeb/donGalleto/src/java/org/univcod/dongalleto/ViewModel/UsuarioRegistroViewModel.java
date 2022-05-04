/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univcod.dongalleto.ViewModel;

/**
 *
 * @author franc
 */
public class UsuarioRegistroViewModel {
 
    private String nombre;
    private String userName;
    private String fechaNacimiento;
    private String correo;

    public UsuarioRegistroViewModel(String nombre, String userName, String fechaNacimiento, String correo) {
        this.nombre = nombre;
        this.userName = userName;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
    
    
    
}
