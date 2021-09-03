package com.example.langtranslater;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocalHelper {

    private static final String SELECTED_LANGUAGE="Locale.Helper.Selected.Language";


    //The methode is used to select language at run time
    public static Context setLocale(Context context,String language)
    {
        
        presist(context,language);
        // updating the language for devices above android nougat
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N)
        {
            return updateResource(context,language);
        }

        // for devices having lower version of android os
        return updateResourcesLegacy(context, language);

    }

    private static void presist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language)
    {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);

        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }



    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResource(Context context, String language)
    {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);

    }



}
