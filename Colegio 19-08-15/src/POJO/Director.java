package POJO;
// Generated 19-ago-2015 14:32:50 by Hibernate Tools 3.6.0



/**
 * Director generated by hbm2java
 */
public class Director  implements java.io.Serializable {


     private Integer idDirector;
     private String nombre;
     private String apellido;
     private String direccion;
     private String telefono;
     private int estado;

    public Director() {
    }

    public Director(String nombre, String apellido, String direccion, String telefono, int estado) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.direccion = direccion;
       this.telefono = telefono;
       this.estado = estado;
    }
   
    public Integer getIdDirector() {
        return this.idDirector;
    }
    
    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }




}


