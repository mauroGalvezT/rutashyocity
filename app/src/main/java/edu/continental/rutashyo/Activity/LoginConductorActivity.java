package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.settings.AppConst;

public class LoginConductorActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText edtEmailLogin, edtPassLogin;
    String valEmailLogin, valPassLogin;
    FloatingActionButton btnIniciarSesion;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_conductor);

        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valEmailLogin=edtEmailLogin.getText().toString();
                valPassLogin=edtPassLogin.getText().toString();
                Login();
            }
        });
    }

    void Login(){
        String url  = AppConst.Server_url+
                AppConst.Registro+
                "US_Email="+ valEmailLogin+
                "&US_Contrasena="+valPassLogin;
        url = url.replace(" ", "%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        startActivity(new Intent(LoginConductorActivity.this, InicioUserActivity.class));
        finish();
        Toast.makeText(this, "Login Correcto", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // progreso.hide();
        /*
        Toast.makeText(this, "No se puede guardar", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());

         */
        Toast.makeText(this, "no registrado", Toast.LENGTH_SHORT).show();


    }
}