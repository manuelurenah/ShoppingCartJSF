package com.cookiebutter.ManagedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by MEUrena on 9/19/16.
 * All rights reserved.
 */

@ManagedBean
@SessionScoped
public class LocaleBean {

    private String selectedLang = "en";
    private Map<String, Object> langMap;

    @PostConstruct
    private void init() {
        langMap = new LinkedHashMap<>();
        langMap.put("English", "en");
        langMap.put("Spanish", "es");
    }

    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }

    public Map<String, Object> getLangMap() {
        return langMap;
    }

    public void setLangMap(Map<String, Object> langMap) {
        this.langMap = langMap;
    }

    public void changeLanguage(ValueChangeEvent event) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(selectedLang));
    }

}
