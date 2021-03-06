package POJO;
// Generated 19-ago-2015 14:32:50 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * CostoMatricula generated by hbm2java
 */
public class CostoMatricula  implements java.io.Serializable {


     private Integer idCostoMatricula;
     private double costo;
     private String nivel;
     private Set matriculas = new HashSet(0);

    public CostoMatricula() {
    }

	
    public CostoMatricula(double costo, String nivel) {
        this.costo = costo;
        this.nivel = nivel;
    }
    public CostoMatricula(double costo, String nivel, Set matriculas) {
       this.costo = costo;
       this.nivel = nivel;
       this.matriculas = matriculas;
    }
   
    public Integer getIdCostoMatricula() {
        return this.idCostoMatricula;
    }
    
    public void setIdCostoMatricula(Integer idCostoMatricula) {
        this.idCostoMatricula = idCostoMatricula;
    }
    public double getCosto() {
        return this.costo;
    }
    
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public String getNivel() {
        return this.nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public Set getMatriculas() {
        return this.matriculas;
    }
    
    public void setMatriculas(Set matriculas) {
        this.matriculas = matriculas;
    }




}


