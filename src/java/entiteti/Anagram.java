/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */

@Entity
public class Anagram implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "zagonetka")
    private String zagonetka;
    
    @Column(name = "resenje")
    private String resenje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZagonetka() {
        return zagonetka;
    }

    public void setZagonetka(String zagonetka) {
        this.zagonetka = zagonetka;
    }

    public String getResenje() {
        return resenje;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }
    
}
