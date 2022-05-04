package org.univcod.dongalleto.Commands;

/**
 *
 * @author franc
 */
public class UsuarioRegistroCommands {
   
    private String nombre;
    private String userName;
    private String fechaNacimiento;
    private String correo;

    public UsuarioRegistroCommands(String nombre, String userName, String fechaNacimiento, String correo) {
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
