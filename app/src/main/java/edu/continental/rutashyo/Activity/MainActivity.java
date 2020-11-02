package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.continental.rutashyo.Activity.Conductor.LoginConductorActivity;
import edu.continental.rutashyo.Activity.User.LoginUserActivity;
import edu.continental.rutashyo.R;

public class MainActivity extends AppCompatActivity {

    Button btnConductor, btnPasajero;
    SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();

        btnConductor = findViewById(R.id.btnConductor);
        btnPasajero = findViewById(R.id.btnPasajero);
        btnPasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "client");
                editor.apply();
                startActivity(new Intent(MainActivity.this, LoginUserActivity.class));
            }
        });
        btnConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "driver");
                editor.apply();
                startActivity(new Intent(MainActivity.this, LoginConductorActivity.class));
            }
        });
    }
}