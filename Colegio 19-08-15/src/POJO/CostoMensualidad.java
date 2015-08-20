package POJO;
// Generated 19-ago-2015 14:32:50 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * CostoMensualidad generated by hbm2java
 */
public class CostoMensualidad  implements java.io.Serializable {


     private Integer idCostomensualidad;
     private double costo;
     private String nivel;
     private Set pagoMensualidads = new HashSet(0);

    public CostoMensualidad() {
    }

	
    public CostoMensualidad(double costo, String nivel) {
        this.costo = costo;
        this.nivel = nivel;
    }
    public CostoMensualidad(double costo, String nivel, Set pagoMensualidads) {
       this.costo = costo;
       this.nivel = nivel;
       this.pagoMensualidads = pagoMensualidads;
    }
   
    public Integer getIdCostomensualidad() {
        return this.idCostomensualidad;
    }
    
    public void setIdCostomensualidad(Integer idCostomensualidad) {
        this.idCostomensualidad = idCostomensualidad;
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
    public Set getPagoMensualidads() {
        return this.pagoMensualidads;
    }
    
    public void setPagoMensualidads(Set pagoMensualidads) {
        this.pagoMensualidads = pagoMensualidads;
    }




}


