/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.istack.internal.logging.Logger;
import entiteti.Korisnik;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.Registration;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
//import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;
//import sun.misc.IOUtils;
//import org.hibernate.criterion.Restrictions;

/**
 *
 * @author korisnik
 */

@ManagedBean
@Named(value = "registracijaKontroler")
@SessionScoped

public class RegistrationController implements Serializable {
    public static String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;
    private String zanimanje;
    private String pol;
    private String JMBG;
    private String passwordAg;
    private String message;
    private String pitanje;
    private String odgovor;
    private String tip;
    private String prihvacen;
   // private String slika;
    private UploadedFile Slika;
 //   private byte[] slika;

  /*  public byte[] getSlika() {
        return slika;
    }

    public void setSlika(byte[] slika) {
        this.slika = slika;
    } */
    
    public void setSlika(UploadedFile Slika) {
        this.Slika = Slika;
    } 

    public UploadedFile getSlika() {
        return Slika;
    } 
    
    public void setProfilnaSlika(UploadedFile Slika) {
        this.Slika = Slika;
    }


   /* public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    */
    
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(String prihvacen) {
        this.prihvacen = prihvacen;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public String getPasswordAg() {
        return passwordAg;
    }

    public void setPasswordAg(String passwordAg) {
        this.passwordAg = passwordAg;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }
    public void handleFileUpload(FileUploadEvent event){
       Slika = event.getFile();
       
    
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
    
    public String registrujse(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
       cr.add(Restrictions.eq("username", username));
      
       if(cr.list().size() > 0) {
           FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnik sa ovim korisnickim imenom vec postoji."));
           message = "Korisnik sa ovim korisnickim imenom vec postoji.";
           return "registracija";
          // return "index";
       } else {
           if(!password.equals(passwordAg)){
               FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinke se ne poklapaju."));
               message = "Lozinke se ne poklapaju.";
               return "registracija";
               //return "index";
           }
           else {
               
           boolean ok = false;
               int passLen = password.length();
                 if(passLen >= 8 && passLen <= 12){
                    if(password.equals(password.toLowerCase())){
                      //  registerMessage += "Lozinka mora da sadrži barem 1 veliko slovo.\n";
                      ok = true;
                         FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 veliko slovo"));
                    return "registracija";
                    }
                    if(!password.matches("^[A-Za-z].*$")){
                      //  registerMessage += "Lozinka mora počinjati sa slovom.\n";
                       ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora počinjati sa slovom."));
                      
                    return "registracija";
                      
                    }
                    int numOfLower = 0;
                    for (int i = 0; i < password.length(); i++) if (Character.isLowerCase(password.charAt(i))) numOfLower++;                    
                    if(numOfLower < 3){
                      //  registerMessage += "Lozinka mora da sadrži barem 3 malih slova.\n";
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 3 malih slova."));
                      
                    return "registracija";
           
                    }
                    if(!password.matches(".*[.,/!?|/()].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 broj.\n";
                        ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 specijalni znak."));
                      
                    return "registracija";
                  
                    }
                    if(!password.matches(".*[0-9].*$")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 specijalni znak.\n";
                       //[A-Za-z0-9 ]*
                      ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Lozinka mora da sadrži barem 1 broj."));
                      
                    return "registracija";
                    }
                    
                } else {
                    //registerMessage += "Dužina lozinke mora biti između 6 i 12 karaktera.\n";
                     ok = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dužina lozinke mora biti između 8 i 12 karaktera."));
                      
                    return "registracija";
                 }
                 
                 boolean okJmbg = false ;
                 int JmbgDuzina = JMBG.length();
                 if (JmbgDuzina == 13) {
                     int prvaCifra = Integer.parseInt(JMBG.substring(0, 1));
                     int drugaCifra = Integer.parseInt(JMBG.substring(1, 2));
                     int trecaCifra = Integer.parseInt(JMBG.substring(2, 3));
                     int cetvrtaCifra = Integer.parseInt(JMBG.substring(3, 4));
                     int petaCifra = Integer.parseInt(JMBG.substring(4, 5));
                     int sestaCifra = Integer.parseInt(JMBG.substring(5, 6));
                     int sedmaCifra = Integer.parseInt(JMBG.substring(6, 7));
                     int osmaCifra = Integer.parseInt(JMBG.substring(7, 8));
                     int devetaCifra = Integer.parseInt(JMBG.substring(8, 9));
                     int desetaCifra = Integer.parseInt(JMBG.substring(9, 10));
                     int jedanaestaCifra = Integer.parseInt(JMBG.substring(10, 11));
                     int dvanaestaCifra = Integer.parseInt(JMBG.substring(11, 12));                     
                     int trinaestaCifra = Integer.parseInt(JMBG.substring(12, 13));
                     
                     int dan = Integer.parseInt(JMBG.substring(0, 2));
                     int mesec = Integer.parseInt(JMBG.substring(2, 4));
                     int godina = Integer.parseInt(JMBG.substring(4, 7));
                     int jeBrPol = Integer.parseInt(JMBG.substring(9, 12));
                     int kontrolna = Integer.parseInt(JMBG.substring(12, 13));
                     
                     if(prvaCifra == 0) {
                         dan = drugaCifra;
                     }
                     if(trecaCifra == 0){
                         mesec = cetvrtaCifra; 
                     }
                     
                     if(devetaCifra == 0){
                         if(desetaCifra == 0){
                             jeBrPol = jedanaestaCifra;
                         } else {
                             jeBrPol = Integer.parseInt(JMBG.substring(10, 12));
                         }
                     }
                     if(cetvrtaCifra == 0){
                         if(petaCifra == 0){
                             godina = sestaCifra;
                         } else {
                             godina = Integer.parseInt(JMBG.substring(5, 7));
                         }
                     }
                     
                     if(mesec < 1 || mesec > 12) {
                     okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Mesec nije u odgovarajucem opsegu."));
                      
                    return "registracija";
                 } 
                     int mesMod = mesec%2;
                   if((mesec < 8 && mesMod == 1) || (mesec > 7 && mesMod == 0) ){
                       if(dan < 1 || dan > 31) {
                           okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dan nije u odgovarajucem opsegu."));
                      
                    return "registracija";
                       }
                   } else {
                       if((mesec < 8 && mesMod == 0 && mesec != 2) || (mesec > 7 && mesMod == 1)) {
                            if(dan < 1 || dan > 30) {
                           okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dan nije u odgovarajucem opsegu."));
                      
                    return "registracija";
                       }
                       }
                       else {
                           if(mesec == 2) {
                               if(!(godina%4 == 0 && dan == 29)){ okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dan nije u odgovarajucem opsegu."));
                      
                    return "registracija"; } else {
                                   if(!(godina%4 == 1 && dan == 28)){ okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Dan nije u odgovarajucem opsegu."));
                      
                    return "registracija";
                               }

                           }
                       }
                   }
                   }
                   if(pol.equals("M")){
                       if(!(jeBrPol >= 0 && jeBrPol <= 499 )) {
                           okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Jedinstveni broj nije u odgovarajucem opsegu za muskarce."));
                      
                    return "registracija";
                       }
                   }
                   
                   if(pol.equals("Z")) {
                       if(!(jeBrPol >= 500 && jeBrPol <= 999 )) {
                           okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Jedinstveni broj nije u odgovarajucem opsegu za zene."));
                      
                    return "registracija";
                       }
                   }
                   
                  
                   
                   int sum = 7*prvaCifra + 6*drugaCifra + 5*trecaCifra + 4*cetvrtaCifra + 3*petaCifra + 2*sestaCifra + 7*sedmaCifra + 6*osmaCifra + 5*devetaCifra + 4*desetaCifra + 3*jedanaestaCifra + 2*dvanaestaCifra;
                   int m = sum%11;
                   int K = -1; 
                   if (m == 0){
                       K = 0;
                   } else {
                       if (m > 1) {
                           K = 11 - m;
                       } else{
                           if (m == 1) {
                               okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Maticni broj je pogresan, uvecajte jedinstveni broj za 1 i pokusajte ponovo."));
                      
                    return "registracija";
                           }
                       }
                   }
                   
                   if(kontrolna != K){
                       okJmbg = true;
                     FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Kontrolna cifra je pogresna."));
                      
                    return "registracija";
                   }
                     
                 } else{
                     okJmbg = true;
                      FacesContext.getCurrentInstance().
                        addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Duzina JMBG mora da bude 13 karaktera."));
                      
                    return "registracija";
                 }
                 
               
                 
                 //=?facet-redirect=true
                 if(!ok && !okJmbg) {
                    // Path dst = Paths.get();
                    String slikaBaza = Slika.getFileName();
                     Path dst =  Paths.get("C:\\Users\\korisnik\\Documents\\NetBeansProjects\\Projekat\\web\\resources\\images\\" + Slika.getFileName());
                    
                   InputStream input;
               try {
                   input = Slika.getInputstream();
                   Files.copy(input, dst, StandardCopyOption.REPLACE_EXISTING);
               } catch (IOException ex) {
                   java.util.logging.Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
               }
                       
                  
            // String path = "C:\\Users\\korisnik\\Documents\\NetBeansProjects\\Projekat\\web\\resources\\images\\" + Slika.getFileName();
          /*     try {
                   Slika.write(path);
               } catch (Exception ex) {
                   java.util.logging.Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
               }
                */
                  String pass = kriptovanjePass(password);
               Korisnik k = new Korisnik();
               
               k.setIme(ime);
               k.setEmail(email);
               k.setPassword(pass);
               k.setPrezime(prezime);
               k.setPol(pol);
               k.setJMBG(JMBG);
               k.setZanimanje(zanimanje);
               k.setUsername(username);
               k.setPitanje(pitanje);
               k.setOdgovor(odgovor);
          //     k.setSlika(profilnaSlika.getContents());
               k.setSlika(slikaBaza);
               k.setTip(tip);
               k.setPrihvacen("Z");
               session.save(k); 
       
               session.getTransaction().commit(); 
               session.close();
               
               message = "Korisnik je registrovan";
               
              // FacesContext.getCurrentInstance().
                 //       addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisnik je registrovan"));
               
               //return "index"  //return "login"
               return "index"; }
           }
       }
        return null;
        
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
    
    
}
