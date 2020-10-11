/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entiteti.Anagram;
import entiteti.Igrac;
import entiteti.Igradana;
import entiteti.Korisnik;
import entiteti.ZanGeografija;
import entiteti.ZanimljivaGeografija;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import java.util.Comparator;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author korisnik
 */

@ManagedBean
@Named(value = "supervizorKontroler")
@SessionScoped
public class SupervizorController implements Serializable{
    private String trenutniUsername;
    private Korisnik selectedKor;
    private String poruka;
    private String poruka1;
    private String poruka2;
    private String zagonetaka;
    private String resenje;
    private int trenutniId;
    private Anagram a1;
    private boolean isValid = false;
    private String mojaZagonetka;
    private Date datum;
    private int idA;
    private Date minDate = new Date();
    private Date danasnji = new Date();
    private boolean disabled = true;
    public static boolean disabled1 = true; //za dugme predaj, sve dok je vrednost true, ne mozemo da pritisnemo dugme i vreme ne pocinje da tece
    public static boolean disabledPollZaKraj = true;  //od trenutka kada smo predali pocinje da se meri vreme za prelazak na drugu stranu
    public static boolean disabledPollZaKraj1 = true;
    public static boolean disabled2 = true;
    
    private boolean stopPoll = false;
    public static boolean stopPoll1 = false;
    public static boolean stopPoll2 = false;
    
    private boolean disInput = false; 
    public static boolean disInput2 = false;
    private boolean startPoll = true;
    
    private Igrac trenutniIgrac;
    
    private ArrayList<Korisnik> k1 = new ArrayList<Korisnik>();
    
    
    
    private boolean zaustaviMojBroj = false;
    public static String dobijeniBroj; 
    private int resenjeMogBroja;
    
    private int prvi;
    private int drugi;
    private int treci;
    private int cetvrti;
    private int peti;
    private int sesti;
    private int sedmi;
    
    private boolean prviStartPoll = false;
    private boolean drugiStartPoll = false;
    private boolean treciStartPoll = false;
    private boolean cetvrtiStartPoll = false;
    private boolean petiStartPoll = false;
    private boolean sestiStartPoll = false;
    private boolean sedmiStartPoll = false;
    
    public static boolean prOk = true;
    public static boolean drOk = true;
    public static boolean trOk = true;
    public static boolean ctOk = true;
    public static boolean ptOk = true;
    public static boolean stOk = true;
    public static boolean sdOk = true;

    
    public int getSedmi() {
        return sedmi;
    }

    public void setSedmi(int sedmi) {
        this.sedmi = sedmi;
    }

    public boolean isSedmiStartPoll() {
        return sedmiStartPoll;
    }

    public Korisnik getSelectedKor() {
        return selectedKor;
    }

    public void setSelectedKor(Korisnik selectedKor) {
        this.selectedKor = selectedKor;
    }

    public String getDobijeniBroj() {
        return dobijeniBroj;
    }

    public void setDobijeniBroj(String dobijeniBroj) {
        this.dobijeniBroj = dobijeniBroj;
    }

    public void setSedmiStartPoll(boolean sedmiStartPoll) {
        this.sedmiStartPoll = sedmiStartPoll;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isSdOk() {
        return sdOk;
    }

    public boolean isDisInput2() {
        return disInput2;
    }

    public void setDisInput2(boolean disInput2) {
        this.disInput2 = disInput2;
    }

    public void setSdOk(boolean sdOk) {
        this.sdOk = sdOk;
    }

    public boolean isPrOk() {
        return prOk;
    }

    public void setPrOk(boolean prOk) {
        this.prOk = prOk;
    }

    public boolean isDrOk() {
        return drOk;
    }

    public void setDrOk(boolean drOk) {
        this.drOk = drOk;
    }

    public boolean isTrOk() {
        return trOk;
    }

    public void setTrOk(boolean trOk) {
        this.trOk = trOk;
    }

    public boolean isCtOk() {
        return ctOk;
    }

    public void setCtOk(boolean ctOk) {
        this.ctOk = ctOk;
    }
    
    public String proveriIzraz(String br) {
        ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    //String foo = "40+2";
    try{
        Object obj = engine.eval(br);
        return obj.toString();
    } catch(Exception e){}
    return "";
    }

    public boolean isStOk() {
        return stOk;
    }

    public void setStOk(boolean stOk) {
        this.stOk = stOk;
    }

    public boolean isPtOk() {
        return ptOk;
    }

    public void setPtOk(boolean ptOk) {
        this.ptOk = ptOk;
    }

    
    public boolean isPrviStartPoll() {
        return prviStartPoll;
    }

    public void setPrviStartPoll(boolean prviStartPoll) {
        this.prviStartPoll = prviStartPoll;
    }

    public boolean isDrugiStartPoll() {
        return drugiStartPoll;
    }

    public void setDrugiStartPoll(boolean drugiStartPoll) {
        this.drugiStartPoll = drugiStartPoll;
    }

    public boolean isTreciStartPoll() {
        return treciStartPoll;
    }

    public void setTreciStartPoll(boolean treciStartPoll) {
        this.treciStartPoll = treciStartPoll;
    }

    public int getResenjeMogBroja() {
        return resenjeMogBroja;
    }

    public void setResenjeMogBroja(int resenjeMogBroja) {
        this.resenjeMogBroja = resenjeMogBroja;
    }

    public boolean isCetvrtiStartPoll() {
        return cetvrtiStartPoll;
    }

    public void setCetvrtiStartPoll(boolean cetvrtiStartPoll) {
        this.cetvrtiStartPoll = cetvrtiStartPoll;
    }

    public boolean isPetiStartPoll() {
        return petiStartPoll;
    }

    public void setPetiStartPoll(boolean petiStartPoll) {
        this.petiStartPoll = petiStartPoll;
    }

    public boolean isSestiStartPoll() {
        return sestiStartPoll;
    }

    public void setSestiStartPoll(boolean sestiStartPoll) {
        this.sestiStartPoll = sestiStartPoll;
    }

    
    public int getPrvi() {
        return prvi;
    }

    public void setPrvi(int prvi) {
        this.prvi = prvi;
    }

    public int getDrugi() {
        return drugi;
    }

    public void setDrugi(int drugi) {
        this.drugi = drugi;
    }

    public int getTreci() {
        return treci;
    }

    public void setTreci(int treci) {
        this.treci = treci;
    }

    public int getCetvrti() {
        return cetvrti;
    }

    public void setCetvrti(int cetvrti) {
        this.cetvrti = cetvrti;
    }

    public int getPeti() {
        return peti;
    }

    public void setPeti(int peti) {
        this.peti = peti;
    }

    public int getSesti() {
        return sesti;
    }

    public void setSesti(int sesti) {
        this.sesti = sesti;
    }

    public boolean isDisabled2() {
        return disabled2;
    }

    public void setDisabled2(boolean disabled2) {
        SupervizorController.disabled2 = disabled2;
    }

    public boolean isStopPoll2() {
        return stopPoll2;
    }

    public void setStopPoll2(boolean stopPoll2) {
        SupervizorController.stopPoll2 = stopPoll2;
    }
    

    public boolean isZaustaviMojBroj() {
        return zaustaviMojBroj;
    }

    public void setZaustaviMojBroj(boolean zaustaviMojBroj) {
        this.zaustaviMojBroj = zaustaviMojBroj;
    }

    public boolean isDisabled1() {
        return disabled1;
    }

    public boolean isDisabledPollZaKraj() {
        return disabledPollZaKraj;
    }

    public void setDisabledPollZaKraj(boolean disabledPollZaKraj) {
        SupervizorController.disabledPollZaKraj = disabledPollZaKraj;
    }

    public boolean isDisabledPollZaKraj1() {
        return disabledPollZaKraj1;
    }

    public void setDisabledPollZaKraj1(boolean disabledPollZaKraj1) {
        SupervizorController.disabledPollZaKraj1 = disabledPollZaKraj1;
    }

    public void setDisabled1(boolean disabled1) {
        SupervizorController.disabled1 = disabled1;
    }
    
    private String grad;
    private String drzava;
    private String planina;
    private String reka;
    private String jezero;
    private String more;
    private String biljka;
    private String zivotinja;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Igrac getTrenutniIgrac() {
        return trenutniIgrac;
    }

   /* public SupervizorController() {
        dodajKorisnika();
    } */

    public void setTrenutniIgrac(Igrac trenutniIgrac) {
        this.trenutniIgrac = trenutniIgrac;
    }

    public ArrayList<Korisnik> getK1() {
        return k1;
    }

    public void setK1(ArrayList<Korisnik> k1) {
        this.k1 = k1;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isStartPoll() {
        return startPoll;
    }

    public void setStartPoll(boolean startPoll) {
        this.startPoll = startPoll;
    }

    public boolean isStopPoll() {
        return stopPoll;
    }

    public void setStopPoll(boolean stopPoll) {
        this.stopPoll = stopPoll;
    }

    public boolean isStopPoll1() {
        return stopPoll1;
    }

    public void setStopPoll1(boolean stopPoll1) {
        this.stopPoll1 = stopPoll1;
    }

    public boolean isDisInput() {
        return disInput;
    }

    public void setDisInput(boolean disInput) {
        this.disInput = disInput;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getDanasnji() {
        return danasnji;
    }

    public void setDanasnji(Date danasnji) {
        this.danasnji = danasnji;
    }

    public String getMojaZagonetka() {
        return mojaZagonetka;
    }

    public void setMojaZagonetka(String mojaZagonetka) {
        this.mojaZagonetka = mojaZagonetka;
    }
    
    private ArrayList<Igrac> rangIgraca;
    
    private ArrayList<Igrac> rangIgraca20;
    private ArrayList<Igrac> rangIgracaMesec;

    public ArrayList<Igrac> getRangIgraca() {
        return rangIgraca;
    }

    public void setRangIgraca(ArrayList<Igrac> rangIgraca) {
        this.rangIgraca = rangIgraca;
    }
    public static Comparator<Igrac> IgrUkPoeni = new Comparator<Igrac>() {

	public int compare(Igrac s1, Igrac s2) {
             int poeni1 = s1.getUkPoeni();
	   int poeni2 = s2.getUkPoeni();
           
           return poeni2-poeni1;
        }};
    public static Comparator<Igrac> IgrDatum = new Comparator<Igrac>() {

	public int compare(Igrac s1, Igrac s2) {
             Date date1 = s1.getDatum();
             Date date2 = s2.getDatum();
           
           return date2.compareTo(date1);
        }};

    public ArrayList<Igrac> getRangIgraca20() {
        return rangIgraca20;
    }

    public void setRangIgraca20(ArrayList<Igrac> rangIgraca20) {
        this.rangIgraca20 = rangIgraca20;
    }

    public ArrayList<Igrac> getRangIgracaMesec() {
        return rangIgracaMesec;
    }

    public String getPoruka2() {
        return poruka2;
    }

    public void setPoruka2(String poruka2) {
        this.poruka2 = poruka2;
    }

    public String getPoruka1() {
        return poruka1;
    }

    public void setPoruka1(String poruka1) {
        this.poruka1 = poruka1;
    }

    public void setRangIgracaMesec(ArrayList<Igrac> rangIgracaMesec) {
        this.rangIgracaMesec = rangIgracaMesec;
    }
    public String rangLista20(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Igrac.class);
        
        if(rangIgraca20 != null) {
            rangIgraca20 = null;
        }
        
        Date date = new Date();
        java.sql.Date dateS = new java.sql.Date(date.getTime());
        
        int danD = dateS.getDate();
        int mesecD = dateS.getMonth();
        int godinaD = dateS.getYear();
        
        java.sql.Date pocetnidatum = null;
        
        if(danD >= 20) {
            pocetnidatum = new java.sql.Date(date.getTime());
            pocetnidatum.setDate(danD - 20 +1);
        } else {
            if(danD < 20) {
                if(mesecD == 0) {
                    mesecD = 11;
                    godinaD--;
                }
                mesecD--;
                if((mesecD<7 && mesecD%2==0) || (mesecD>=7 && mesecD%2==1)){
                   pocetnidatum = new java.sql.Date(date.getTime());
                   int ost = 20 - danD;
                   pocetnidatum.setDate(31 - ost + 1);
                   pocetnidatum.setMonth(mesecD);
                   pocetnidatum.setYear(godinaD);
                   
                    //ost = 20 - danD && 31 - ost + 1
                    //dan 31
                } else {
                    if(mesecD == 2){
                        if(godinaD%4==0){
                            pocetnidatum = new java.sql.Date(date.getTime());
                            int ost = 20 - danD;
                            pocetnidatum.setDate(29 - ost + 1);
                            pocetnidatum.setMonth(mesecD);
                            pocetnidatum.setYear(godinaD);
                            //ost = 20 - danD && 29 - ost +1
                            //dan 29
                        } else {
                            pocetnidatum = new java.sql.Date(date.getTime());
                            int ost = 20 - danD;
                            pocetnidatum.setDate(28 - ost + 1);
                            pocetnidatum.setMonth(mesecD);
                            pocetnidatum.setYear(godinaD);
                            //ost = 20 - danD && 28 - ost + 1
                            //dan 28
                        }
                    } else {
                        pocetnidatum = new java.sql.Date(date.getTime());
                        int ost = 20 - danD;
                        pocetnidatum.setDate(30 - ost + 1);
                        pocetnidatum.setMonth(mesecD);
                        pocetnidatum.setYear(godinaD);
                        //ost = 20 - danD && 30 - ost + 1
                        //dan 30
                    }
                }
            }
        }
        
        
            if(dateS.getMonth() != pocetnidatum.getMonth()) {
                
                for(int c=dateS.getDate(); c>0; c--) {
                    Igrac igr = najboljizaOvajDatum(dateS);
                    int dan = dateS.getDate() - 1;
                    dateS.setDate(dan);
                    if(rangIgraca20 == null){
                        rangIgraca20 = new ArrayList<>();
                    }
                    if(igr != null)
                    rangIgraca20.add(igr);
                }
                if((mesecD<7 && mesecD%2==0) || (mesecD>=7 && mesecD%2==1)){
                    for(int e=pocetnidatum.getDate(); e<=31; e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgraca20 == null){
                         rangIgraca20 = new ArrayList<>();
                     }
                         if(igr != null)
                      rangIgraca20.add(igr);
                    }
                } else {
                     if(mesecD == 2){
                        if(godinaD%4==0){
                            for(int e=pocetnidatum.getDate(); e<=29; e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgraca20 == null){
                         rangIgraca20 = new ArrayList<>();
                     }
                         if(igr != null)
                      rangIgraca20.add(igr);
                    }
                        } else {
                            for(int e=pocetnidatum.getDate(); e<=28; e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgraca20 == null){
                         rangIgraca20 = new ArrayList<>();
                     }
                         if(igr != null)
                      rangIgraca20.add(igr);
                    }
                        }
                        
                }
             else {
                    for(int e=pocetnidatum.getDate(); e<=30; e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgraca20 == null){
                         rangIgraca20 = new ArrayList<>();
                     }
                         if(igr != null)
                      rangIgraca20.add(igr);
                    }
                    }
        }
        } else {
        for(int e=pocetnidatum.getDate(); e<=dateS.getDate(); e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgraca20 == null){
                         rangIgraca20 = new ArrayList<>();
                     }
                        if(igr != null)
                      rangIgraca20.add(igr);
        }
            }
            if(rangIgraca20 == null) {
                FacesContext.getCurrentInstance().
                        addMessage("mess2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Rang lista je prazna!"));
                return "gost";
            }
            Collections.sort(rangIgraca20, IgrUkPoeni);
        return "rangLista20";
    }
    
    
    public Igrac najboljizaOvajDatum(java.sql.Date datum){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
         Criteria cr2 = session.createCriteria(Igrac.class);
        ArrayList<Igrac> danasnjiIgraci = null;
        
        int n1 = cr2.list().size();
        for (int j = 0; j < n1; j++) {
            Igrac igraD = (Igrac) cr2.list().get(j);
            long danI = igraD.getDatum().getDate();
            long mesecI = igraD.getDatum().getMonth();
            long godinaI = igraD.getDatum().getYear();
            
            long danD = datum.getDate();
            long mesecD = datum.getMonth();
            long godinaD = datum.getYear();
            if (danI == danD && mesecI == mesecD && godinaI == godinaD){
                if(danasnjiIgraci == null){
                    danasnjiIgraci = new ArrayList<>();
                }
                
                 
                 danasnjiIgraci.add(igraD);                
      }   
        }
        if(danasnjiIgraci != null){
        Collections.sort(danasnjiIgraci, SupervizorController.IgrUkPoeni);
        }
        session.getTransaction().commit();
        session.close(); 
        
        
        if(danasnjiIgraci == null){
            return null;
        }
        
        return danasnjiIgraci.get(0);
        
        
        
    }
    
    
    public String rangLista30(){
        
  
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Igrac.class);
        
        if(rangIgracaMesec != null) {
            rangIgracaMesec = null;
        }
        
        Date date = new Date();
        java.sql.Date dateS = new java.sql.Date(date.getTime());
        
        int danD = dateS.getDate();
        int mesecD = dateS.getMonth();
        int godinaD = dateS.getYear();
        
        java.sql.Date pocetnidatum = new java.sql.Date(date.getTime());
        pocetnidatum.setDate(1);
        pocetnidatum.setMonth(mesecD);
        pocetnidatum.setYear(godinaD);
        
           
        for(int e=pocetnidatum.getDate(); e<=dateS.getDate(); e++){
                        Igrac igr = najboljizaOvajDatum(pocetnidatum);
                        int dan = pocetnidatum.getDate() + 1;
                        pocetnidatum.setDate(dan);
                        if(rangIgracaMesec == null){
                         rangIgracaMesec = new ArrayList<>();
                     }
                        if(igr != null)
                      rangIgracaMesec.add(igr);
        }
            
            if(rangIgracaMesec == null) {
                FacesContext.getCurrentInstance().
                        addMessage("mess2", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Rang lista je prazna!"));
                return "gost";
            }
            if(rangIgracaMesec != null)
            Collections.sort(rangIgracaMesec, IgrUkPoeni);
       
    
        
        return "rangLista30";
    }
    public String rangLista() {
        rend = true;
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        Criteria cr1 = session.createCriteria(Igrac.class);
        Criteria cr = session.createCriteria(Igrac.class);
        
        Criteria cr2 = session.createCriteria(Igrac.class);
        int n1 = cr2.list().size();
        if(rangIgraca != null) {
            rangIgraca = null;
        }
         HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
        
    Igrac igracc = null;
        Date date = new Date();
        java.sql.Date dateS = new java.sql.Date(date.getTime());
           boolean ok1 = true;
           ArrayList<Igrac> danasnjiIgraci = null;
        for (int j = 0; j < n1; j++) {
            Igrac igraD = (Igrac) cr2.list().get(j);
            long danI = igraD.getDatum().getDate();
            long mesecI = igraD.getDatum().getMonth();
            long godinaI = igraD.getDatum().getYear();
            
            long danD = dateS.getDate();
            long mesecD = dateS.getMonth();
            long godinaD = dateS.getYear();
            if (danI == danD && mesecI == mesecD && godinaI == godinaD){
                if(danasnjiIgraci == null){
                    danasnjiIgraci = new ArrayList<>();
                }
                 ok1 = false;
                 
                 if(igraD.getUsername().equals(usern)){
                     igracc = igraD;
                 }
                 
                 danasnjiIgraci.add(igraD);
                 
                 
      }   
        }
      
        
       
       
       
       if(danasnjiIgraci == null) {
             FacesContext.getCurrentInstance().
                        addMessage("mess1", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Danas jos uvek nijedan igrac nije igrao igru dana. Rang lista je prazna!"));
           return "takmicar";
       }
        
    int n = danasnjiIgraci.size();
    boolean ok = false;
       if(n < 10) {
       for(int i = 0; i < n; i++){
           Igrac ig = (Igrac) danasnjiIgraci.get(i);
           if(rangIgraca == null) {
               rangIgraca = new ArrayList<>();
           }
           rangIgraca.add(ig);
       } } else {
           if(n > 10) {
               for(int i = 0; i < 10; i++){
           Igrac ig = (Igrac) danasnjiIgraci.get(i);
           if(ig == igracc) {
               ok = true;
           }
           rangIgraca.add(ig);
           
       }
            if(!ok && igracc != null){
               rangIgraca.add(igracc);
            }
           }
       }
       
        Collections.sort(rangIgraca, SupervizorController.IgrUkPoeni);
        
        session.getTransaction().commit();
        session.close();
        
        
        return "rangLista";
    }
    
    private ArrayList<ZanGeografija> zanlista;
    public static boolean adminZanimljiva = false;
    
    public void proveriPojmoveUZanGeo() {
        zanlista = new ArrayList<>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(ZanGeografija.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
             ZanGeografija zanimljivaC = (ZanGeografija) cr.list().get(i);
             if(zanimljivaC.getPrihvacen().equals("Z")) {
                 zanlista.add(zanimljivaC);
                 adminZanimljiva = true;
             }
              }
        
        if(zanlista.size() == 0) {
             FacesContext.getCurrentInstance().
                        addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Trenutno nema pojmova za proveru!"));
        }
            
        session.getTransaction().commit();
        session.close();
    }
    
    private static ArrayList<Korisnik> korisnici;
    static {
        korisnici = new ArrayList<Korisnik>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Korisnik kor = (Korisnik) cr.list().get(i);
            if((kor.getPrihvacen()).equals("Z")) {
         
            korisnici.add(kor);}
        }
        
         
         
        session.getTransaction().commit();
        session.close();
    }
    
    private static ArrayList<Anagram> zagonetke;
    static {
        zagonetke = new ArrayList<Anagram>();
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Anagram.class);
        
        int n = cr.list().size();
        
        for (int i = 0; i < n; i++){
            Anagram an = (Anagram) cr.list().get(i);
            zagonetke.add(an);
        }
        session.getTransaction().commit();
        session.close(); 
        
    }
    
    public ArrayList<Anagram> getZagonetke() {
        return zagonetke;
    }

    public void setZagonetke(ArrayList<Anagram> zagonetke) {
        SupervizorController.zagonetke = zagonetke;
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        SupervizorController.korisnici = korisnici;
    }

    public boolean isAdminZanimljiva() {
        return adminZanimljiva;
    }

    public void setAdminZanimljiva(boolean adminZanimljiva) {
        SupervizorController.adminZanimljiva = adminZanimljiva;
    }

    public ArrayList<ZanGeografija> getZanlista() {
        return zanlista;
    }

    public void setZanlista(ArrayList<ZanGeografija> zanlista) {
        this.zanlista = zanlista;
    }
    
    
   
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }
    

    public int getTrenutniId() {
        return trenutniId;
    }

    public void setTrenutniId(int trenutniId) {
        this.trenutniId = trenutniId;
    }

    
    public String getZagonetaka() {
        return zagonetaka;
    }

    public void setZagonetaka(String zagonetaka) {
        this.zagonetaka = zagonetaka;
    }

    public Anagram getA1() {
        return a1;
    }

    public void setA1(Anagram a1) {
        this.a1 = a1;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getResenje() {
        return resenje;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }
    
    public void dohvatiPitanje(){
         SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Anagram.class);
        
        int n = cr.list().size();
        Random rand = new Random();
        
        int i = rand.nextInt(n);
        i+=1;
        
        Anagram ana = (Anagram) cr.add(Restrictions.eq("id", i)).uniqueResult();
        if(ana != null) {
            a1 = ana;
            trenutniId = i;
            zagonetaka = ana.getZagonetka();
             }
            session.getTransaction().commit();
            session.close();
        
    }
    
    public void show(){
        if (isValid) {
            isValid = false;
        } else {
            isValid = true;
        }
    }

    public String anagram(){
        dohvatiPitanje();
        return "anagram";
    }
    
    public void sledecaIgraJeMojBrojPoll() throws IOException {
        if(!disabled){
            timer = 60;
            try{
       FacesContext.getCurrentInstance().getExternalContext()
        .redirect("mojBroj.xhtml");} catch(IOException e) {e.printStackTrace();} }
        
    }
    
    public void sledecaIgraJeZanimljivaGeografijaPoll() throws IOException {
         System.out.println("PollZanGeo " + disabledPollZaKraj);
        if(!disabledPollZaKraj){
            timer1 = 60;
       FacesContext.getCurrentInstance().getExternalContext()
        .redirect("zanimljivaGeografija.xhtml");}
    }
    
    public void sledecaIgraJe5Puta5Poll() throws IOException {
         System.out.println("PollZanGeo " + disabledPollZaKraj1);
        if(!disabledPollZaKraj1){
            timer2 = 120;
       FacesContext.getCurrentInstance().getExternalContext()
        .redirect("5x5.xhtml");}
    }
    
    private int timer1 = 60;
    
    private int timer = 60;
    
    private int time = 4;
    private int time2 = 4;
    private int timer2 = 120;

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getTimer2() {
        return timer2;
    }

    public void setTimer2(int timer2) {
        this.timer2 = timer2;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer1() {
        return timer1;
    }

    public void setTimer1(int timer1) {
        this.timer1 = timer1;
    }
    
    public String sledecaIgraJeMojBroj() {
        return "mojBroj";
    }
    
    public void anagramPredajOdmah(){
        timer=0;
    }
    
     public void mojBrojPredajOdmah(){
        // System.out.println(disabledPollZaKraj + " ");
        // System.out.println(disabled1 + " ");
        timer1=0;
    }
     
     public void zanGeoPredajOdmah(){
        // System.out.println(disabledPollZaKraj + " ");
        // System.out.println(disabled2 + " ");
        timer2=0;
    }
     
     public void smanjiTimer() throws IOException {
           
         if(time>0){
             time--;
             
         } else {
              timer1 = 60;
              time = 4;
       FacesContext.getCurrentInstance().getExternalContext()
        .redirect("zanimljivaGeografija.xhtml");}
     }
     
     public static boolean prviPut = true;
     public static boolean prviPut2 = true;

    public boolean isPrviPut() {
        return prviPut;
    }

    public void setPrviPut(boolean prviPut) {
        SupervizorController.prviPut = prviPut;
    }

    public boolean isPrviPut2() {
        return prviPut2;
    }

    public void setPrviPut2(boolean prviPut2) {
        SupervizorController.prviPut2 = prviPut2;
    }
    
    public void izbrisiMojBroj(){
        prvoJeKorisceno = false;
        drugoJeKorisceno = false;
        treceJeKorisceno = false;
        cetvrtoJeKorisceno = false;
        petoJeKorisceno = false;
        sestooJeKorisceno = false;
        
        poslednjiJeBroj = false;
        dobijeniBroj = "";
    }
    
    
    public void zanGeoPredaj() throws IOException{
        System.out.println("zanGeoPredaj " + disabled2 + timer2);
        if(!disabled2) {
           if(timer2>0){
            timer2--;
            return;
        } 
           
           disabledPollZaKraj1 = false;
           disInput2 = true;
           stopPoll2 = true;
           int ukPoena = 0;
              SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
            Session session = SessionFactory.openSession();
            session.beginTransaction();
         //   Session session1 = SessionFactory.openSession();
          //  session1.beginTransaction();
             int uk = 0;
             Criteria cr = session.createCriteria(Igrac.class);
            if(prviPut2){
             prviPut2 = false;
           
           //  Criteria cr2 = session1.createCriteria(ZanimljivaGeografija.class);
        //     Criteria cr1 = session.createCriteria(ZanimljivaGeografija.class);
        Criteria cr1 = session.createCriteria(ZanGeografija.class);
        Criteria cr2 = session.createCriteria(ZanGeografija.class);
        Criteria cr3 = session.createCriteria(ZanGeografija.class);
        Criteria cr4 = session.createCriteria(ZanGeografija.class);
        Criteria cr5 = session.createCriteria(ZanGeografija.class);
        Criteria cr6 = session.createCriteria(ZanGeografija.class);
        Criteria cr7 = session.createCriteria(ZanGeografija.class);
        Criteria cr8 = session.createCriteria(ZanGeografija.class);
        
        Date date = new Date();
       
        ZanGeografija zan = (ZanGeografija) cr1.add(Restrictions.eq("vrsta", "grad")).add(Restrictions.eq("ime", grad)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
      String slovoG = "";
        if(!grad.equals("") ){
        slovoG = grad.substring(0, 1);} 
        if(grad != null && zan != null && slovoG.equals(izabranoSlovo) ) {
            ukPoena += 2;
            System.out.println("uk" + ukPoena);
        } 
        else { if(!grad.equals("") && slovoG.equals(izabranoSlovo)) {
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(grad);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("grad");
            z1.setDatum(date);
            
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
        }
            
        }
        ZanGeografija zanD = (ZanGeografija) cr2.add(Restrictions.eq("vrsta","drzava")).add(Restrictions.eq("ime", drzava)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
        String slovoD = "";
        if(!drzava.equals("")) {
            slovoD = drzava.substring(0, 1);
        }
        if(drzava != null && zanD != null && slovoD.equals(izabranoSlovo)){
            ukPoena += 2;
            System.out.println("ukPoena =" + ukPoena);
        } 
        else { 
             if(!drzava.equals("") && slovoD.equals(izabranoSlovo)){
            ZanGeografija z2 = new ZanGeografija();
            z2.setIme(drzava);
            z2.setKorisnik(trenutniUsername);
            z2.setPrihvacen("Z");
            z2.setVrsta("drzava");
            z2.setDatum(date);           
            
            //session.save(z2);
         //   session.getTransaction().commit(); 
          SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z2);
            session1.getTransaction().commit();
            session1.close();
             }
        }
          ZanGeografija zanP = (ZanGeografija) cr3.add(Restrictions.eq("vrsta", "planina")).add(Restrictions.eq("ime", planina)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
          String slovoP = "";
          if(!planina.equals("")) {
              slovoP = planina.substring(0, 1);
          }
          if(planina != null && zanP != null && zanP.getPrihvacen().equals("P") && slovoP.equals(izabranoSlovo)) {
            ukPoena += 2;
        }  
        else {
             System.out.println("planina" + planina);
             if(!planina.equals("") && slovoP.equals(izabranoSlovo)){
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(planina);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("planina");
            z1.setDatum(date);
            
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
           // session.save(z1);
           // session.getTransaction().commit(); }
            }  
        }
         ZanGeografija zanJ = (ZanGeografija) cr4.add(Restrictions.eq("vrsta", "jezero")).add(Restrictions.eq("ime", jezero)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
         String slovoJ = "";
         if(!jezero.equals("")) {
             slovoJ = jezero.substring(0, 1);
         }
         if(jezero != null && zanJ != null && slovoJ.equals(izabranoSlovo)) {
            ukPoena += 2;
        } 
        else { if(!jezero.equals("") && slovoJ.equals(izabranoSlovo)) {
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(jezero);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("jezero");
            z1.setDatum(date);
            
            
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
            
          //  session.save(z1);
          //  session.getTransaction().commit(); 
        
        }
            
        }
          ZanGeografija zanM = (ZanGeografija) cr5.add(Restrictions.eq("vrsta", "more")).add(Restrictions.eq("ime", more)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
          String slovoM = "";
          if(!more.equals("")) {
              slovoM = more.substring(0, 1);
          }
          if(more != null && zanM != null && slovoM.equals(izabranoSlovo)) {
            ukPoena += 2;
        } 
        else {
             if(!more.equals("") && slovoM.equals(izabranoSlovo)){
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(more);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("more");
            z1.setDatum(date);
            
            
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
          //  session.save(z1);
          //  session.getTransaction().commit(); }
        }
         } 
          ZanGeografija zanR = (ZanGeografija) cr6.add(Restrictions.eq("vrsta", "reka")).add(Restrictions.eq("ime", reka)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
          String slovoR = "";
          if(!reka.equals("")) {
              slovoR = reka.substring(0, 1);
          }
          if(reka != null && zanR != null && slovoR.equals(izabranoSlovo)) {
            ukPoena += 2;
        } 
        else { if(!reka.equals("") && slovoR.equals(izabranoSlovo)){
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(reka);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("reka");
            z1.setDatum(date);
            
            
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
            
         //   session.save(z1);
         //   session.getTransaction().commit(); }
        }
         }   
          ZanGeografija zanB = (ZanGeografija) cr7.add(Restrictions.eq("vrsta", "biljka")).add(Restrictions.eq("ime", biljka)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
          String slovoB = "";
          if(!biljka.equals("")) {
              slovoB = biljka.substring(0, 1);
          }
          if(biljka != null && zanB != null && slovoB.equals(izabranoSlovo)) {
            ukPoena += 2;
        } 
        else {
             if(!biljka.equals("") && slovoB.equals(izabranoSlovo)){
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(biljka);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("biljka");
            z1.setDatum(date);
            
            
          //  session.save(z1);
          //  session.getTransaction().commit(); }
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();
        }
         }
          ZanGeografija zanZ = (ZanGeografija) cr8.add(Restrictions.eq("vrsta", "zivotinja")).add(Restrictions.eq("ime", zivotinja)).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
          String slovoZ = "";
          if(!zivotinja.equals("")) {
              slovoZ = zivotinja.substring(0, 1);
          }
          if(zivotinja != null && zanZ != null && slovoZ.equals(izabranoSlovo)) {
            ukPoena += 2;
        } 
        else { 
             if(!zivotinja.equals("") && slovoZ.equals(izabranoSlovo)) {
            ZanGeografija z1 = new ZanGeografija();
            z1.setIme(zivotinja);
            z1.setKorisnik(trenutniUsername);
            z1.setPrihvacen("Z");
            z1.setVrsta("zivotinja");
            z1.setDatum(date);
            
            
        //    session.save(z1);
        //    session.getTransaction().commit();}
            SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
            Session session1 = SessionFactory.openSession();
            session1.beginTransaction();
            
            Criteria crm = session1.createCriteria(ZanGeografija.class);
            
            session1.save(z1);
            session1.getTransaction().commit();
            session1.close();  
        } }
            
         
  
     
     
             
           FacesContext.getCurrentInstance().
                        addMessage("message12", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "U ovoj igri ste osvojili" + " " + ukPoena + " " + "poena!"));
           
     //      session.getTransaction().commit();
     //      session.close();
           
           
           // igra dana
            java.sql.Date sqlDanasnji = new java.sql.Date(danasnji.getTime());
              
              HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
        
     cr.add(Restrictions.eq("username", usern)); 
     int n1 = cr.list().size();

     for(int i = 0; i<n1; i++){
         Igrac igrac = (Igrac) cr.list().get(i);
      long  danS = igrac.getDatum().getDate();
      long mesecS = igrac.getDatum().getMonth();
      long godinaS = igrac.getDatum().getYear();
      
      long danD = sqlDanasnji.getDate();
      long mesecD = sqlDanasnji.getMonth();
      long godinaD = sqlDanasnji.getYear();
         
      if (danS == danD && mesecS == mesecD && godinaS == godinaD){   
          igrac.setZanGeoPoeni(ukPoena);
          uk = igrac.getUkPoeni();
          uk += ukPoena;
          igrac.setUkPoeni(uk);
           trenutniIgrac = igrac;
                    
           session.save(igrac);
           session.getTransaction().commit();
           session.close();
      }   
             
        }       // igra dana  //}
   poruka1 = "U ovoj igri ste osvojili" + " " + ukPoena + " " + "poena!";
            }
             
               if(time2>0){
             time2--;
             return;
         } else {
              timer2 = 120;
              time2 = 4;
     //  FacesContext.getCurrentInstance().getExternalContext()
      //  .redirect("5x5.xhtml");} 
       rangLista();
       
       // FacesContext.getCurrentInstance().addMessage("mess12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Osvojili ste u igri dana " + uk + " poena!"));
      FacesContext.getCurrentInstance().getExternalContext()
        .redirect("rangLista.xhtml");
              //  FacesContext.getCurrentInstance().addMessage("mess12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Osvojili ste u igri dana " + uk + " poena!"));} 
               }
    
        }
    }
    
     public void mojBrojPredaj() throws IOException{
          System.out.println("mojBrojPredaj " + disabled1 + timer1);
         if (!disabled1) {
         if(timer1>0){
            timer1--;
            return;
        }
         
         
         
         disabledPollZaKraj = false;
         stopPoll1 = true;
        if(prviPut){
             prviPut = false;
         if(proveriIzraz(dobijeniBroj).equals(sedmi + "")) {  
             poruka = "Tacan odgovor! U ovoj igri ste osvojili 10 poena!";
             FacesContext.getCurrentInstance().
                        addMessage("message10", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Tacan odgovor! U ovoj igri ste osvojili 10 poena!"));
             SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
             Session session = SessionFactory.openSession();
             session.beginTransaction();
             
              Criteria cr = session.createCriteria(Igrac.class);
              
              java.sql.Date sqlDanasnji = new java.sql.Date(danasnji.getTime());
              
              HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
        
     cr.add(Restrictions.eq("username", usern)); 
     int n = cr.list().size();

     for(int i = 0; i<n; i++){
         Igrac igrac = (Igrac) cr.list().get(i);
      long  danS = igrac.getDatum().getDate();
      long mesecS = igrac.getDatum().getMonth();
      long godinaS = igrac.getDatum().getYear();
      
      long danD = sqlDanasnji.getDate();
      long mesecD = sqlDanasnji.getMonth();
      long godinaD = sqlDanasnji.getYear();
         
      if (danS == danD && mesecS == mesecD && godinaS == godinaD){   
          igrac.setMojBrojPoeni(10);
          int uk = igrac.getUkPoeni();
          uk += 10;
          igrac.setUkPoeni(uk);
          //trenutniIgrac = igrac;
                    
           session.save(igrac);
           session.getTransaction().commit();
           session.close();
      }   
             
        } 
         } else {
             poruka = "Odgovor nije tacan! U ovoj igri ste osvojili 0 poena!";
             FacesContext.getCurrentInstance().
                        addMessage("message10", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Odgovor nije tacan! U ovoj igri ste osvojili 0 poena!"));
         }   }
           if(time>0){
             time--;
             return;
         } else {
              timer1 = 60;
              time = 4;
       FacesContext.getCurrentInstance().getExternalContext()
        .redirect("zanimljivaGeografija.xhtml");}
       
         }
         }
     
    
    public void anagramPredaj(){
        if(timer>0){
            timer--;
            return;
        }
        disabled = false;
        stopPoll = true;
        disInput = true;
        startPoll = false;
        if (a1.getResenje().equals(resenje)) {
            poruka2 = "Tacan odgovor! U ovoj igri ste osvojili 10 poena!";
             FacesContext.getCurrentInstance().
                        addMessage("mess", new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Tacan odgovor! U ovoj igri ste osvojili 10 poena!"));
             SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
             Session session = SessionFactory.openSession();
             session.beginTransaction();
             
              Criteria cr = session.createCriteria(Igrac.class);
              
              java.sql.Date sqlDanasnji = new java.sql.Date(danasnji.getTime());
              
              HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
        
     cr.add(Restrictions.eq("username", usern)); 
     int n = cr.list().size();

     for(int i = 0; i<n; i++){
         Igrac igrac = (Igrac) cr.list().get(i);
      long  danS = igrac.getDatum().getDate();
      long mesecS = igrac.getDatum().getMonth();
      long godinaS = igrac.getDatum().getYear();
      
      long danD = sqlDanasnji.getDate();
      long mesecD = sqlDanasnji.getMonth();
      long godinaD = sqlDanasnji.getYear();
         
      if (danS == danD && mesecS == mesecD && godinaS == godinaD){   
          igrac.setAnagramPoeni(10);
          int uk = igrac.getUkPoeni();
          uk += 10;
          igrac.setUkPoeni(uk);
          trenutniUsername = k.getUsername();
        //  System.out.println("trenutniU" + trenutniUsername);
           trenutniIgrac = igrac;
                    
           session.save(igrac);
           session.getTransaction().commit();
           session.close();
      }   
             
        } }
        else {
            poruka2 = "Odgovor nije tacan! U ovoj igri ste osvojili 0 poena!";
             FacesContext.getCurrentInstance().
                        addMessage("mess", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Odgovor nije tacan! U ovoj igri ste osvojili 0 poena!"));
        }
    } 
    
    public void dodajUBazu(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Anagram.class);
        
       Anagram anagram = (Anagram) cr.add(Restrictions.eq("zagonetka", zagonetaka)).uniqueResult();
       
       if(anagram != null){
           FacesContext.getCurrentInstance().
                        addMessage("m1", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ova zagonetka vec postoji u bazi. Unesite novu."));
       } else {
           String str1 = zagonetaka;
           String str2 = resenje;
           
           str1 = str1.replaceAll("\\s+","");
           str2 = str2.replaceAll("\\s+","");
           
           int n1 = str1.length();
           int n2 = str2.length();
           boolean ok = true;
           boolean ok1 = true;
            if (n1 != n2) {
                ok = false;
            FacesContext.getCurrentInstance().
                        addMessage("m1", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ovo nije resenje anagrama. Pokusajte ponovo")); 
            }
        // Sort both strings 
        Arrays.sort(str1.toCharArray());
        Arrays.sort(str2.toCharArray()); 
        char[] st1 = str1.toCharArray();
        char[] st2 = str1.toCharArray();
        
        for(int i = 0; i<n1; i++){
            if(Character.isDigit(str1.charAt(i))){
                ok1 = false;
                 FacesContext.getCurrentInstance().
                        addMessage("m1", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Anagram se moze sastojati samo iz slova!")); 
                 return;
            }
        }
        
        // Compare sorted strings 
        for (int i = 0; i < n1; i++) 
            if (st1[i] != st2[i]) {
                ok = false;
              FacesContext.getCurrentInstance().
                        addMessage("m1", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ovo nije resenje anagrama. Pokusajte ponovo")); 
              return;
            }
        
        if(ok && ok1) {
            Anagram a = new Anagram();
            
            a.setZagonetka(zagonetaka);
            a.setResenje(resenje);
            
             session.save(a); 
       
               session.getTransaction().commit(); 
               session.close();
               
               FacesContext.getCurrentInstance().
                        addMessage("mess", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Novi anagram je uspesno dodat!"));
               
        }
            }
    }
    
    public String kreirajIgruDana(){
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Igradana.class);
        
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        
        Igradana igra = (Igradana) cr.add(Restrictions.eq("datum", sqlDatum)).uniqueResult();
        
        if(igra != null) {
             FacesContext.getCurrentInstance().
                        addMessage("mes", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Za ovaj datum je vec definisana igra dana. Izaberite neki drugi datum"));
             return "admin";
        } else {
            Igradana igraDana = new Igradana();
            
            igraDana.setDatum(sqlDatum);
            
            Criteria cr1 = session.createCriteria(Anagram.class);
            Anagram anagram = (Anagram) cr1.add(Restrictions.eq("zagonetka", mojaZagonetka)).uniqueResult();
            
            if(anagram == null){
                 FacesContext.getCurrentInstance().
                        addMessage("mes", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Ovaj anagram ne postoji u bazi anagrama."));
                return "admin";                
            }
            
            igraDana.setAnagram(anagram.getId());
            
            session.save(igraDana);
            session.getTransaction().commit();
            session.close();
           
            FacesContext.getCurrentInstance().
                addMessage("mes", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Igra dana je uspesno dodata!"));
            
            return "admin";
            
        }
       
    }
    
    public String zapocniIgruDana(){
        poruka = "";
        poruka1 = "";
        poruka2= "";
        disabled = true;
        stopPoll = false;
        disInput = false;
        startPoll = true;
        
        rend = true;
        timer = 60;
        timer1 = 60;
        timer2 = 120;
        time = 4;
        time2 = 4;
        resenje = "";
        grad = "";
        drzava ="";
        planina ="";
        more ="";
        reka ="";
        jezero ="";
        biljka ="";
        zivotinja ="";
        
        prOk = true;
        drOk = true;
        trOk = true;
        ctOk = true;
        ptOk = true;
        stOk = true;
        sdOk = true;
        sviBrojevi = false;
        
        prvoJeKorisceno = false;
        drugoJeKorisceno = false;
        treceJeKorisceno = false;
        cetvrtoJeKorisceno = false;
        petoJeKorisceno = false;
        sestooJeKorisceno = false;
        
        poslednjiJeBroj = false;
        dobijeniBroj = "";
        prviPut = true;
        
        
       stopPoll1 = false;
       disabled1 = true;
       disabledPollZaKraj = true;
       
        
        stopPoll2 = false;
       disabled2 = true;
       
       slovoIzabrano = false;
       
       disInput2 = false;
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Igrac.class);
        Criteria cr1 = session.createCriteria(Igradana.class);
        
        int n1 = cr1.list().size();
        
        java.sql.Date sqlDanasnji = new java.sql.Date(danasnji.getTime());
        boolean ok1 = true;
        for (int j = 0; j < n1; j++) {
            Igradana igraD = (Igradana) cr1.list().get(j);
            long danI = igraD.getDatum().getDate();
            long mesecI = igraD.getDatum().getMonth();
            long godinaI = igraD.getDatum().getYear();
            
            long danD = sqlDanasnji.getDate();
            long mesecD = sqlDanasnji.getMonth();
            long godinaD = sqlDanasnji.getYear();
            if (danI == danD && mesecI == mesecD && godinaI == godinaD){
                 ok1 = false;
                 
                 Criteria cr2 = session.createCriteria(Anagram.class);
                 
                 int idAnagrama = igraD.getAnagram();
                
                 Anagram anagram = (Anagram) cr2.add(Restrictions.eq("id", idAnagrama)).uniqueResult(); 
                 a1 = anagram;
                 trenutniId = anagram.getId();
                 zagonetaka = anagram.getZagonetka();
                 
      }   
        }
        
        if(ok1) {
            
             FacesContext.getCurrentInstance().addMessage("messa", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Za danasnji dan nije definisana igra dana!"));
       
             return "takmicar";
        } else {
        
        HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
        
     cr.add(Restrictions.eq("username", usern)); 
     int n = cr.list().size();
     boolean ok = true;
     for(int i = 0; i<n; i++){
         Igrac igrac = (Igrac) cr.list().get(i);
      long  danS = igrac.getDatum().getDate();
      long mesecS = igrac.getDatum().getMonth();
      long godinaS = igrac.getDatum().getYear();
      
      long danD = sqlDanasnji.getDate();
      long mesecD = sqlDanasnji.getMonth();
      long godinaD = sqlDanasnji.getYear();
         
      if (danS == danD && mesecS == mesecD && godinaS == godinaD){
          ok = false;
      }   
     // FacesContext.getCurrentInstance().
                      //  addMessage("messa", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, danD + " " + danS + " " + mesecS + " " + mesecD + " " + godinaS + " " + godinaD));
         
     }
        
//            FacesContext.getCurrentInstance().addMessage("messa", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, k.getUsername() + " " + igrac1.getUsername() + " " + sqlDanasnji + " " + igrac1.getDatum()));
         
        if(cr.list().size() != 0 && !ok ) {
            FacesContext.getCurrentInstance().
                        addMessage("messa", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Danas ste vec igrali igru dana!"));
            return "takmicar"; 
        } else {
            Igrac igr = new Igrac();
           trenutniUsername = k.getUsername();
            igr.setDatum(sqlDanasnji);
            igr.setUsername(k.getUsername());
            //igr.setAnagramPoeni(0);
            
            session.save(igr);
            session.getTransaction().commit();
            session.close();
            // FacesContext.getCurrentInstance().addMessage("messa", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, k.getUsername() + " " + igrac1.getUsername() + " " + sqlDanasnji + " " + igrac1.getDatum()));
//         System.out.println(k.getUsername() + " " + igrac1.getUsername() + " " + sqlDanasnji + " " + igrac1.getDatum());
            // return "partija";
           return "anagram";
        }
        }
    }
   
    
        
    public String prihvatiPojam(ZanGeografija zanGeo) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        SessionFactory SessionFactory1 = DB.HibernateUtil.getSessionFactory();
        Session session1 = SessionFactory.openSession();
        session1.beginTransaction();
        
        Criteria cr = session.createCriteria(ZanGeografija.class);
        Criteria cr1 = session.createCriteria(ZanGeografija.class);
        Criteria cr2 = session1.createCriteria(Igrac.class);
        
        ZanGeografija vecpostoji = (ZanGeografija) cr.add(Restrictions.eq("vrsta", zanGeo.getVrsta())).add(Restrictions.eq("ime", zanGeo.getIme())).add(Restrictions.eq("prihvacen", "P")).uniqueResult();
        
        if(vecpostoji != null) {
           Igrac igr = (Igrac) cr2.add(Restrictions.eq("datum", zanGeo.getDatum())).add(Restrictions.eq("username", zanGeo.getKorisnik())).uniqueResult();  
            
           int zanPoeni = igr.getZanGeoPoeni() + 4;
           igr.setZanGeoPoeni(zanPoeni);
           int uk = igr.getUkPoeni() + 4;
           igr.setUkPoeni(uk);
           
           session1.save(igr);
        
           session1.getTransaction().commit();
           session1.close();
           
       vecpostoji.setPrihvacen("VP");
        
        session.save(vecpostoji);
        
        session.getTransaction().commit();
        session.close();
        zanlista.remove(zanGeo);
              if(zanlista.size() == 0) {
           adminZanimljiva = false;
          // FacesContext.getCurrentInstance().addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       }
             
       return "supervizor";
        }
        
        ZanGeografija zan = (ZanGeografija) cr1.add(Restrictions.eq("korisnik", zanGeo.getKorisnik())).add(Restrictions.eq("vrsta", zanGeo.getVrsta())).add(Restrictions.eq("ime", zanGeo.getIme())).add(Restrictions.eq("datum", zanGeo.getDatum())).uniqueResult();
        
        Igrac igr = (Igrac) cr2.add(Restrictions.eq("datum", zanGeo.getDatum())).add(Restrictions.eq("username", zanGeo.getKorisnik())).uniqueResult();  
            
           int zanPoeni = igr.getZanGeoPoeni() + 4;
           igr.setZanGeoPoeni(zanPoeni);
           int uk = igr.getUkPoeni() + 4;
           igr.setUkPoeni(uk);
           
           session1.save(igr);
        
           session1.getTransaction().commit();
           session1.close();
        
        
        zan.setPrihvacen("P");
        
        session.save(zan);
        
        session.getTransaction().commit();
        session.close();
        
        
             FacesContext.getCurrentInstance().addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       zanlista.remove(zanGeo);
     
        if(zanlista.size() == 0) {
           adminZanimljiva = false;
          // FacesContext.getCurrentInstance().addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       }
       
       return "supervizor";
    }
    
    
    public String prihvati(Korisnik kor) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", kor.getUsername())).uniqueResult();
        
        k.setPrihvacen("P");
        
        session.save(k);
        
        session.getTransaction().commit();
        session.close();
        
        
             FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       korisnici.remove(kor);
       
        if(korisnici.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Svi zahtevi su obradjeni!"));
       }
       
             return "admin";
          // return "proba";
        
    }
    
    public void dodajKorisnika() {
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        int n = cr.list().size();
        
         for (int i = 0; i < n; i++){
            Korisnik kor = (Korisnik) cr.list().get(i);
            if((kor.getPrihvacen()).equals("Z")) {
            k1.add(kor);}
        }
        
        session.getTransaction().commit();
        session.close();
    }
    
      public String odbijPojamZan (ZanGeografija zanGeo) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(ZanGeografija.class);
        
        ZanGeografija zan = (ZanGeografija) cr.add(Restrictions.eq("korisnik", zanGeo.getKorisnik())).add(Restrictions.eq("vrsta", zanGeo.getVrsta())).add(Restrictions.eq("ime", zanGeo.getIme())).uniqueResult();
        
        zan.setPrihvacen("O");
        
        session.save(zan);
        
        session.getTransaction().commit();
        session.close();

           FacesContext.getCurrentInstance().addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       zanlista.remove(zanGeo);
       if(zanlista.size() == 0) {
           adminZanimljiva = false;
          // FacesContext.getCurrentInstance().addMessage("m12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       }
           return "supervizor";
    }
    
    public String odbij (Korisnik kor) {
        
        SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Korisnik.class);
        
        Korisnik k = (Korisnik) cr.add(Restrictions.eq("username", kor.getUsername())).uniqueResult();
        
        k.setPrihvacen("O");
        
        session.save(k);
        
        session.getTransaction().commit();
        session.close();

           FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Baza je uspesno azurirana!"));
       korisnici.remove(kor);
       
       if(korisnici.size() <=0){
           adminKorNaloga = false;
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Svi zahtevi su obradjeni!"));
       }
       
           return "admin";
        // return "proba";
    }
    
   
    public void stopMojBroj() {
        System.out.println("STOP");
        zaustaviMojBroj = true;
        if(prOk){
            prOk = false;
            prviStartPoll = true;
        } else {
            if(drOk){
                drOk = false;
                drugiStartPoll = true;
            }
            else {
                if (trOk){
                    trOk = false;
                    treciStartPoll = true;
                }
                else {
                    if(ctOk) {
                        ctOk = false;
                        cetvrtiStartPoll = true;
                    }
                    else {
                        if(ptOk) {
                            ptOk = false;
                            petiStartPoll = true;
                        }
                        else { if(stOk) {
                            stOk = false;
                            sestiStartPoll = true;
                            } 
                        else {
                            sdOk = false;
                            sedmiStartPoll = true;
                            sviBrojevi = true;
                            disabled1 = false; 
                        }
                        
                        }
                    }
                }
            }
        }
    }
    
    public static boolean sviBrojevi=false;

    public boolean isSviBrojevi() {
        return sviBrojevi;
    }

    public void setSviBrojevi(boolean sviBrojevi) {
        this.sviBrojevi = sviBrojevi;
    }
    public void dajBrojeve(){
        System.out.println("HELLO " + sviBrojevi);
        Random  rand = new Random();
        if(prOk){
                int n = rand.nextInt(9);
                n++;
                prvi = n;
                
        } 
            if(drOk){
                int n = rand.nextInt(9);
                n++;
                drugi = n;
            }
                if(trOk){
                int n = rand.nextInt(9);
                n++;
                treci = n;
            }
                    if(ctOk){
                int n = rand.nextInt(9);
                n++;
                cetvrti = n;
            }
                        if(ptOk){
                int n = rand.nextInt(3) + 2;
                n = n * 5;
                peti = n;
            }
                            if(stOk){
                int n = rand.nextInt(4) + 1;
                n = n * 25;
                sesti = n;
            }
                            if(sdOk){
                int n = rand.nextInt(999);
                n ++;
                sedmi = n;
            }
                        }
    
    
    
    public static boolean prvoJeKorisceno = false;
    public static boolean drugoJeKorisceno = false;
    public static boolean treceJeKorisceno = false;
    public static boolean cetvrtoJeKorisceno = false;
    public static boolean petoJeKorisceno = false;
    public static boolean sestooJeKorisceno = false;

    public boolean isPrvoJeKorisceno() {
        return prvoJeKorisceno;
    }

    public boolean isDrugoJeKorisceno() {
        return drugoJeKorisceno;
    }

    public void setDrugoJeKorisceno(boolean drugoJeKorisceno) {
        this.drugoJeKorisceno = drugoJeKorisceno;
    }

    public boolean isTreceJeKorisceno() {
        return treceJeKorisceno;
    }

    public void setTreceJeKorisceno(boolean treceJeKorisceno) {
        this.treceJeKorisceno = treceJeKorisceno;
    }

    public boolean isCetvrtoJeKorisceno() {
        return cetvrtoJeKorisceno;
    }

    public void setCetvrtoJeKorisceno(boolean cetvrtoJeKorisceno) {
        this.cetvrtoJeKorisceno = cetvrtoJeKorisceno;
    }

    public boolean isPetoJeKorisceno() {
        return petoJeKorisceno;
    }

    public void setPetoJeKorisceno(boolean petoJeKorisceno) {
        this.petoJeKorisceno = petoJeKorisceno;
    }

    public boolean isSestooJeKorisceno() {
        return sestooJeKorisceno;
    }

    public void setSestooJeKorisceno(boolean sestooJeKorisceno) {
        this.sestooJeKorisceno = sestooJeKorisceno;
    }

    public void setPrvoJeKorisceno(boolean prvoJeKorisceno) {
        this.prvoJeKorisceno = prvoJeKorisceno;
    }
    
    public static boolean poslednjiJeBroj = false;

    public boolean isPoslednjiJeBroj() {
        return poslednjiJeBroj;
    }

    public void setPoslednjiJeBroj(boolean poslednjiJeBroj) {
        SupervizorController.poslednjiJeBroj = poslednjiJeBroj;
    }
    
    public void pritisniPrvoDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = prvi + "";
            prvoJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += prvi + ""; 
        prvoJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    
    public void pritisniDrugpDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = drugi + "";
            drugoJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += drugi + ""; 
        drugoJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    public void pritisniTreceDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = treci + "";
            treceJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += treci + "";
        treceJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    public void pritisniCetvrtoDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = cetvrti + "";
            cetvrtoJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += cetvrti + ""; 
        cetvrtoJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    public void pritisniPetoDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = peti + "";
            petoJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += peti + ""; 
        petoJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    public void pritisniSestoDugme(){
        if(dobijeniBroj == null){
            dobijeniBroj = sesti + "";
            sestooJeKorisceno = true;
            poslednjiJeBroj = true;
        } else {
        dobijeniBroj += sesti + ""; 
        sestooJeKorisceno = true;
        poslednjiJeBroj = true;
        }
    }
    public void pritisniPlus(){
        if(dobijeniBroj == null){
            dobijeniBroj = "+";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += "+";
        poslednjiJeBroj = false;
        }
    }
    public void pritisniMinus(){
        if(dobijeniBroj == null){
            dobijeniBroj = "-";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += "-";
        poslednjiJeBroj = false;
        }
    }
    public void pritisniPuta(){
        if(dobijeniBroj == null){
            dobijeniBroj = "*";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += "*";
        poslednjiJeBroj = false;
        }
    }
    public void pritisniPodeljeno(){
        if(dobijeniBroj == null){
            dobijeniBroj = "/";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += "/";
        poslednjiJeBroj = false;
        }
    }
    public void pritisniOtvorenaZagrada(){
        if(dobijeniBroj == null){
            dobijeniBroj = "(";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += "(";
        poslednjiJeBroj = false;
        }
    }
    public void pritisniZatvorenaZagrada(){
        if(dobijeniBroj == null){
            dobijeniBroj = ")";
            poslednjiJeBroj = false;
        } else {
        dobijeniBroj += ")";
        poslednjiJeBroj = false;
        }
    }
    
    private String izabranoSlovo = "";
    public static boolean slovoIzabrano = false;

    public boolean isSlovoIzabrano() {
        return slovoIzabrano;
    }

    public void setSlovoIzabrano(boolean slovoIzabrano) {
        SupervizorController.slovoIzabrano = slovoIzabrano;
    }

    public String getIzabranoSlovo() {
        return izabranoSlovo;
    }

    public void setIzabranoSlovo(String izabranoSlovo) {
        this.izabranoSlovo = izabranoSlovo;
    }
     
    
    public void dajSlovo(){
        System.out.println("HELLO " + slovoIzabrano);
        if(!slovoIzabrano) {
        Random  rand = new Random();
        String[] azbuka = new String[]{"A", "B", "V", "G", "D", "Dj", "E", "Z", "I", "J", "K", "L", "Lj", "M", "N", "Nj", "O", "P", "R", "S", "T", "U", "F", "H", "C"};
        int n = rand.nextInt(25);
  // String[] azbuka = new String[]{"A", "B", "C", "H" };
       //  int n = rand.nextInt(4);
        izabranoSlovo = azbuka[n]; }
    }
    
    public void stopMojeSlovo(){
        System.out.println("STOP");
        
        slovoIzabrano = true;
        disabled2 = false;
    }
    
    public static boolean adminKorNaloga = false;

    public boolean isAdminKorNaloga() {
        return adminKorNaloga;
    }

    public void setAdminKorNaloga(boolean adminKorNaloga) {
        this.adminKorNaloga = adminKorNaloga;
    }
    
    public void adminKorNaloga(){
        if(adminKorNaloga) {
            adminKorNaloga = false;
        }
        if(korisnici.size() > 0){
        adminKorNaloga = true;} 
        else {
            FacesContext.getCurrentInstance().addMessage("mesii", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Svi korisnicki nalozi su obradjeni!"));
        }
    }
    
    public String logout(){
        return "index";
    }
    
    public String logout1(){
           HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesija.removeAttribute("user");
        return "index";
    }
    
    private boolean rend = true;

    public boolean isRend() {
        return rend;
    }

    public void setRend(boolean rend) {
        this.rend = rend;
    }
    
    public String predaj() {
         HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Korisnik k = (Korisnik) sesija.getAttribute("user");
        
       String usern = k.getUsername();
       Date date = new Date();
       java.sql.Date sqlDanasnji = new java.sql.Date(date.getTime());
       
       SessionFactory SessionFactory = DB.HibernateUtil.getSessionFactory();
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        
        Criteria cr = session.createCriteria(Igrac.class);
        
        
           cr.add(Restrictions.eq("username", usern)); 
     int n1 = cr.list().size();

     for(int i = 0; i<n1; i++){
         Igrac igrac = (Igrac) cr.list().get(i);
      long  danS = igrac.getDatum().getDate();
      long mesecS = igrac.getDatum().getMonth();
      long godinaS = igrac.getDatum().getYear();
      
      long danD = sqlDanasnji.getDate();
      long mesecD = sqlDanasnji.getMonth();
      long godinaD = sqlDanasnji.getYear();
         
      if (danS == danD && mesecS == mesecD && godinaS == godinaD){   
         
          int uk = igrac.getUkPoeni();
        rend = false;
        FacesContext.getCurrentInstance().addMessage("mess12", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Osvojili ste u igri dana " + uk + " poena!"));
       
        return "takmicar";
    }
    
}
        return null;
            
    } 

    public String takmicarStr(){
        return "takmicar";
    }
    
    public String gostStr(){
        return "gost";
    }
}      

//ako je vec kliknuo na to dugme, ako jos uvek nije izabrao svih sedam brojeva i ako je poslednji karakter bio broj
//brisi izraz 
//!svibrojevi

// <!--     <p:poll interval="1" listener="#{supervizorKontroler.zanGeoPredaj()}" update="panelZanGeo" stop="#{supervizorKontroler.stopPoll2}"></p:poll>
            //        <p:commandButton value="Predaj" action="#{supervizorKontroler.zanGeoPredajOdmah()}"  update="panelZanGeo" disabled="#{supervizorKontroler.disabled2}"></p:commandButton>





// korisnik kada popuni polja onda gleda 

/*    <h:form id="form2">
            <p:panel id="p1">
                 
                <p:commandLink value="Zaboravili ste lozinku?" action="#{lozinkaKontroler.show()}">
                    <f:ajax execute="form2" render="form2"></f:ajax>
                </p:commandLink>
                <h:panelGrid columns="3" rendered="#{lozinkaKontroler.isValid}">
                    <p:outputLabel value="Username:" ></p:outputLabel>
                    <p:inputText id="userJ" value="#{lozinkaKontroler.username}" required="true" requiredMessage="Polje za username mora biti popunjeno." ></p:inputText>
                    <p:message for="userJ" display="text"></p:message>
                    
                    <p:outputLabel value="JMBG:"></p:outputLabel>
                    <p:inputText id="JMBGJ" value="#{lozinkaKontroler.JMBG}" required="true" requiredMessage="Polje za JMBG mora biti popunjeno." ></p:inputText>
                    <p:message for="JMBGJ" display="text"></p:message>
                    
                    <p:commandButton value="Dalje" action="#{lozinkaKontroler.zaboravljenaLozinka()}" update="p1" ></p:commandButton>
                </h:panelGrid>
            </p:panel>
        </h:form> 
*/