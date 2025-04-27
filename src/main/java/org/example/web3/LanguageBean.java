
package org.example.web3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.Locale;

@Named("languageBean")
@ApplicationScoped
public class LanguageBean {
    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setEnglishLocale() {
        currentLocale = Locale.ENGLISH;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
    }

    public void setRussianLocale() {
        currentLocale = new Locale("ru", "RU");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
    }
}
