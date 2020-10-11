/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author korisnik
 */


@Entity
public class ZanimljivaGeografija {
    @Id
    @Column(name = "id")
    private int id;
    
   @Column(name = "postoji")
    private String postoji;
   
   @Column(name = "username")
    private String username;
   
    @Column(name = "slovo")
    private String slovo;
    
    @Column(name = "grad")
    private String grad;
    
    @Column(name = "drzava")
    private String drzava;
    
    @Column(name = "planina")
    private String planina;
    
    @Column(name = "reka")
    private String reka;
    
     @Column(name = "jezero")
    private String jezero;
    
    @Column(name = "more")
    private String more;
    
    @Column(name = "biljka")
    private String biljka;
    
    @Column(name = "zivotinja")
    private String zivotinja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostoji() {
        return postoji;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostoji(String postoji) {
        this.postoji = postoji;
    }

    public String getSlovo() {
        return slovo;
    }

    public void setSlovo(String slovo) {
        this.slovo = slovo;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getPlanina() {
        return planina;
    }

    public void setPlanina(String planina) {
        this.planina = planina;
    }

    public String getReka() {
        return reka;
    }

    public void setReka(String reka) {
        this.reka = reka;
    }

    public String getJezero() {
        return jezero;
    }

    public void setJezero(String jezero) {
        this.jezero = jezero;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getBiljka() {
        return biljka;
    }

    public void setBiljka(String biljka) {
        this.biljka = biljka;
    }

    public String getZivotinja() {
        return zivotinja;
    }

    public void setZivotinja(String zivotinja) {
        this.zivotinja = zivotinja;
    }
    
}
