package org.example.web3;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import jakarta.faces.context.FacesContext;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

    private String locale = "ru";

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void changeLanguage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Locale newLocale = new Locale(locale);
        facesContext.getViewRoot().setLocale(newLocale);
    }
}
