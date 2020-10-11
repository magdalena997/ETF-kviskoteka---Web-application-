/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entiteti.Korisnik;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import controllers.SupervizorController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author korisnik
 */

@ManagedBean
@Named(value = "loginKontroler")
@SessionScoped

public class LoginController implements Serializable {
    
    
    private String username;
    private String password;
    private String tip;

    public static boolean poruka1 = true;

    public  boolean isPoruka1() {
        return poruka1;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public  void setPoruka1(boolean poruka1) {
        LoginController.poruka1 = poruka1;
    }

    public String mojBroj() {
        SupervizorController.prOk = true;
        SupervizorController.drOk = true;
        SupervizorController.trOk = true;
        SupervizorController.ctOk = true;
        SupervizorController.ptOk = true;
        SupervizorController.stOk = true;
        SupervizorController.sdOk = true;
        SupervizorController.sviBrojevi = false;
        
        SupervizorController.prvoJeKorisceno = false;
        SupervizorController.drugoJeKorisceno = false;
        SupervizorController.treceJeKorisceno = false;
        SupervizorController.cetvrtoJeKorisceno = false;
        SupervizorController.petoJeKorisceno = false;
        SupervizorController.sestooJeKorisceno = false;
        
        SupervizorController.poslednjiJeBroj = false;
        SupervizorController.dobijeniBroj = "";
        SupervizorController.prviPut = true;
        
        
       SupervizorController.stopPoll1 = false;
       SupervizorController.disabled1 = true;
       SupervizorController.disabledPollZaKraj = true;
       
        return "mojBroj";
    }
    
    public String proba(){
        return "proba";
    }
    
    public String zanGeo(){
        SupervizorController.stopPoll2 = false;
       SupervizorController.disabled2 = true;
       
       SupervizorController.slovoIzabrano = false;
       
       SupervizorController.disInput2 = false;
        
        return "zanimljivaGeografija";
    }
    
    public String gost(){
        return "gost";
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
    
    public String login(){
        poruka1 = true;
        LozinkaController.pork1 = false;
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        
        String pass = kriptovanjePass(password);
        
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", pass)).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        
       
        if(k != null){
            if(k.getPrihvacen().equals("P")) {
            if(k.getTip().equals(tip)){
                
            HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesija.setAttribute("user", k);
            poruka1 = false;
            if(k.getTip().equals("T")){
               // HttpSession sesija1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            //sesija1.setAttribute("takmicar", k);
            return "takmicar";}
            else {
                if(k.getTip().equals("A")){
                    SupervizorController.adminZanimljiva = false;
                    SupervizorController.adminKorNaloga = false;
                    return "admin";
                }
                else {
                    return "supervizor";
                }
            }
            }
            else {
                FacesContext.getCurrentInstance().
                        addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Tip koji ste uneli je pogresan!"));
                return null;
            } } else {
                FacesContext.getCurrentInstance().
                        addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ovaj korisnik nije prihvacen od strane administratora!"));
                return null;
            }
        } else {
        
        SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
        Session session1 = SessionFactory.openSession();
        session1.beginTransaction();
        
        Criteria cr1 = session1.createCriteria(Korisnik.class);
         Korisnik k1 = (Korisnik) cr1.add(Restrictions.eq("username", username)).uniqueResult();
          session1.getTransaction().commit();
        session1.close();
        if(k1 != null && !k1.getPassword().equals(pass)) {
            FacesContext.getCurrentInstance().
                        addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka koju ste uneli je pogresna!"));
            
        }
        else {
            FacesContext.getCurrentInstance().
                        addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnicko ime ne postoji!"));
            
        }
        return null; }
    }
    
    public String registracija(){
        RegistrationController.ime = "";
        return "registracija";
    }
    
    public String promenaLozinke(){
        LozinkaController.username = "";
        LozinkaController.password = "";
        LozinkaController.noviPass = "";
        LozinkaController.ponoviPass = "";
        return "lozinka";
    }
    
     public String kriptovanjePass(String password){
         String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
            
            return generatedPassword;
    }
     
     public String zaboravljenaLozinka1(){
        LozinkaController.username = "";
        LozinkaController.JMBG = "";
        LozinkaController.odgovor = "";
        LozinkaController.zabLozinka = "";
         return "lozinkaJMBG";
     }
}
