package edu.continental.rutashyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.continental.rutashyo.Activity.LoginConductorActivity;
import edu.continental.rutashyo.Activity.LoginUserActivity;

public class MainActivity extends AppCompatActivity {

    Button btnConductor, btnPasajero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConductor = findViewById(R.id.btnConductor);
        btnPasajero = findViewById(R.id.btnPasajero);
        btnPasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginUserActivity.class));
            }
        });
        btnConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginConductorActivity.class));
            }
        });
    }
}