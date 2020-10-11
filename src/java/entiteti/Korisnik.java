/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author korisnik
 */
@Entity
public class Korisnik {
    
    //@Id
   // @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;
    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "ime")
    private String ime;
    
    @Column(name = "prezime")
    private String prezime;
    
    @Column(name = "email")
    private String email;

    @Column(name = "pol")
    private String pol;
    
    @Column(name = "JMBG")
    private String JMBG;

    @Column(name = "zanimanje")
    private String zanimanje;
    
    @Column(name = "pitanje")
    private String pitanje;
    
    @Column(name = "odgovor")
    private String odgovor;
    

    
    @Column(name = "tip")
    private String tip;
    
    @Column(name = "prihvacen")
    private String prihvacen;
    
   // @Column(name = "slika")
   // private byte[] slika;
    
    @Column(name = "slika")
    private String slika;
    

    public String getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }

    public String getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(String prihvacen) {
        this.prihvacen = prihvacen;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
    
}
