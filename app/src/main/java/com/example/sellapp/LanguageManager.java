package com.example.sellapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public LanguageManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LANG", Context.MODE_PRIVATE);
    }

    public void updateResource(String code) {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        setLang(code);
    }

    public String getLang() {
        return sharedPreferences.getString("Lang", "en");
    }

    public void setLang(String code) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Lang", code);
        editor.commit();
    }

}
