package com.sunsta.demo;

import android.app.Application;

import com.sunsta.demo.translator.AuthTranslator;

public class AuthApplication extends Application {

    private AuthTranslator translator;

    @Override
    public void onCreate() {
        super.onCreate();
        translator = new AuthTranslator(new AuthManager());
    }

    public AuthTranslator getTranslator() {
        return translator;
    }
}
