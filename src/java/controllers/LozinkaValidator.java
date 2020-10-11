/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author korisnik
 */


@ManagedBean
@SessionScoped
@FacesValidator("lozinkaValidator")
@Named(value="lozinkaValidator")
public class LozinkaValidator implements Validator, Serializable{
    
    public static String lozinka;

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    
    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
      
        String model = (String) value;
        int passLen = model.length();
        if(passLen >= 8 && passLen <= 12){
                    if(model.equals(model.toLowerCase())){
                      //  registerMessage += "Lozinka mora da sadrži barem 1 veliko slovo.\n";
                     //    FacesContext.getCurrentInstance().
                    //    addMessage("message3", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Lozinka mora da sadrži barem 1 veliko slovo"));
                    FacesMessage msg = new FacesMessage(
                    "Lozinka mora da sadrži barem 1 veliko slovo.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);
                    }
                    int numOfLower = 0;
                    for (int i = 0; i < model.length(); i++) if (Character.isLowerCase(model.charAt(i))) numOfLower++;                    
                    if(numOfLower < 3){
                      //  registerMessage += "Lozinka mora da sadrži barem 3 malih slova.\n";
                        FacesMessage msg = new FacesMessage(
                    "Lozinka mora da sadrži barem 3 malih slova.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);
                    }
                    if(model.matches(".*\\d+.*")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 broj.\n";
                     FacesMessage msg = new FacesMessage(
                    "Lozinka mora da sadrži barem 1 broj.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);    
                    }
                    if(model.matches("[A-Za-z0-9 ]*")){
                       // registerMessage += "Lozinka mora da sadrži barem 1 specijalni znak.\n";
                      FacesMessage msg = new FacesMessage(
                    "Lozinka mora da sadrži barem 1 specijalni znak.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);   
                    }
                    if(!model.matches("^[A-Za-z].*$")){
                      //  registerMessage += "Lozinka mora počinjati sa slovom.\n";
                         FacesMessage msg = new FacesMessage(
                    "Lozinka mora počinjati sa slovom.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);
                    }
                } else {
                    //registerMessage += "Dužina lozinke mora biti između 6 i 12 karaktera.\n";
                    FacesMessage msg = new FacesMessage(
                    "Dužina lozinke mora biti između 8 i 12 karaktera.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 
            throw new ValidatorException(msg);
                }
        
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
