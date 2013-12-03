package com.me.mygdxgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
//        cfg.useGL20 = false;
//        
//        initialize(new MyGdxGame(), cfg);
        System.out.println("11");
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        System.out.println("12");
        cfg.useAccelerometer = false;
        System.out.println("13");
        cfg.useCompass = false;
        System.out.println("14");
        initialize(new BarreBalle(), cfg);
        System.out.println("15");
    }
}