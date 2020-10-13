package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import edu.continental.rutashyo.R;


public class LoginUserActivity extends AppCompatActivity {

    EditText edtEmailLogin, edtPassLogin;
    String valEmailLogin, valPassLogin;
    FloatingActionButton btnIniciarSesion;

    SmartCityService smartCityService;
    SmartCityClient smartCityClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_user);

        retrofitInit();
        findViews();
        events();



    }

    private void events() {
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valEmailLogin=edtEmailLogin.getText().toString();
                valPassLogin=edtPassLogin.getText().toString();
                Login();
            }
        });
    }

    private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();
    }
    private void findViews() {
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
    }


    public void OpenSignupPage(View view) {
        startActivity(new Intent(LoginUserActivity.this, RegistroUserActivity.class));
    }
    void Login(){
        String email = edtEmailLogin.getText().toString();
        String pass = edtPassLogin.getText().toString();
        if(email.isEmpty()){
            edtEmailLogin.setError("El email es requerido");
        } else if(pass.isEmpty()){
            edtPassLogin.setError("Contraseña requerida");
        } else {
            SolicitarLogin solicitarLogin =new SolicitarLogin(email, pass);
            Call<RespuestaLogin> call = smartCityService.doLogin(solicitarLogin);
            call.enqueue(new Callback<RespuestaLogin>() {
                @Override
                public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(LoginUserActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginUserActivity.this, InicioUserActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginUserActivity.this, "Revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                    Toast.makeText(LoginUserActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

