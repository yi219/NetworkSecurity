package com.example.myapplication;

import android.app.Application;

public class MyApplication extends Application {
    boolean PWLOCK = false;

    public void setLOCK(boolean PWLOCK){
        this.PWLOCK = PWLOCK;
    }

    public boolean getLOCK(){
        return PWLOCK;
    }
}
