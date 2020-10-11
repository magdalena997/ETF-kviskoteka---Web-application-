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
public class Igradana {
    
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "datum")
    private Date datum;
    
    @Column(name = "anagram")
    private int anagram;

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

    public int getAnagram() {
        return anagram;
    }

    public void setAnagram(int anagram) {
        this.anagram = anagram;
    }
    
    
}
