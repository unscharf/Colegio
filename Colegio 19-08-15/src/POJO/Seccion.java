package POJO;
// Generated 19-ago-2015 14:32:50 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Seccion generated by hbm2java
 */
public class Seccion  implements java.io.Serializable {


     private Integer idSeccion;
     private Integer noSeccion;
     private Set programacions = new HashSet(0);

    public Seccion() {
    }

    public Seccion(Integer noSeccion, Set programacions) {
       this.noSeccion = noSeccion;
       this.programacions = programacions;
    }
   
    public Integer getIdSeccion() {
        return this.idSeccion;
    }
    
    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }
    public Integer getNoSeccion() {
        return this.noSeccion;
    }
    
    public void setNoSeccion(Integer noSeccion) {
        this.noSeccion = noSeccion;
    }
    public Set getProgramacions() {
        return this.programacions;
    }
    
    public void setProgramacions(Set programacions) {
        this.programacions = programacions;
    }




}

