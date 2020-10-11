/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */
@Entity
public class ZanGeografija {
     @Id
    @Column(name = "id")
    private int id;
    
   @Column(name = "prihvacen")
    private String prihvacen;
   
   @Column(name = "vrsta")
   private String vrsta;
   
   @Column(name = "korisnik")
   private String korisnik;
   
   @Column(name = "ime")
   private String ime;
  
   @Column(name = "datum")
   private Date datum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(String prihvacen) {
        this.prihvacen = prihvacen;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
   
   
}
