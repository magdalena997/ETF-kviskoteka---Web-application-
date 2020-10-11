/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */
@Entity
public class Igrac implements Serializable{
    
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "datum")
    private Date datum;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "anagramPoeni")
    private int anagramPoeni;

    @Column(name = "mojBrojPoeni")
    private int mojBrojPoeni;
    
    @Column(name = "zanGeoPoeni")
    private int zanGeoPoeni;
    
    @Column(name = "ukPoeni")
    private int ukPoeni;
    

    

    public int getUkPoeni() {
        return ukPoeni;
    }

    public void setUkPoeni(int ukPoeni) {
        this.ukPoeni = ukPoeni;
    }

    public int getZanGeoPoeni() {
        return zanGeoPoeni;
    }

    public void setZanGeoPoeni(int zanGeoPoeni) {
        this.zanGeoPoeni = zanGeoPoeni;
    }

    public int getMojBrojPoeni() {
        return mojBrojPoeni;
    }

    public void setMojBrojPoeni(int mojBrojPoeni) {
        this.mojBrojPoeni = mojBrojPoeni;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAnagramPoeni() {
        return anagramPoeni;
    }

    public void setAnagramPoeni(int anagramPoeni) {
        this.anagramPoeni = anagramPoeni;
    }
}
