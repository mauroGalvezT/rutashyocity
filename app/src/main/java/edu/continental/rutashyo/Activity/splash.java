package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.settings.PrefManager;


public class splash extends AppCompatActivity {

    private PrefManager prefManager;
    private static int SPLASH_TIME_OUT = 1000;
    private Handler handler = new Handler();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);
        context = splash.this;

        // making notification bar transparent
        changeStatusBarColor();

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }else{
            timeHandler();
        }
    }

    private void timeHandler(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                launchHomeScreen();
            }
        },SPLASH_TIME_OUT);
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
//        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        startActivity(new Intent(splash.this, IntroActivity.class));
        finish();
    }

    /**
     * Hacer transparente la barra de notificaciones
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}