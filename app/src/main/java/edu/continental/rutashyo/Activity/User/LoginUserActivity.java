package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudPerfil;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.PrefManager;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import edu.continental.rutashyo.R;


public class LoginUserActivity extends AppCompatActivity {

    EditText edtEmailLogin, edtPassLogin;
    String valEmailLogin, valPassLogin;
    FloatingActionButton btnIniciarSesion;
    TextView txtRecuperarPass;

    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    String emailPref;

    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_user);

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();

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
        txtRecuperarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUserActivity.this, CambiarPassActivity.class);
                startActivity(i);
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
        txtRecuperarPass = findViewById(R.id.txtRecuperarPass);
    }


    public void OpenSignupPage(View view) {
        startActivity(new Intent(LoginUserActivity.this, RegistroUserActivity.class));
    }
    void Login(){
        mDialog.show();
        String email = edtEmailLogin.getText().toString();
        String pass = edtPassLogin.getText().toString();
        if(email.isEmpty()){
            mDialog.dismiss();
            edtEmailLogin.setError("El email es requerido");
        } else if(pass.isEmpty()){
            mDialog.dismiss();
            edtPassLogin.setError("Contraseña requerida");
        } else {
            SolicitarLogin solicitarLogin =new SolicitarLogin(email, pass);
            Call<RespuestaLogin> call = smartCityService.doLogin(solicitarLogin);
            call.enqueue(new Callback<RespuestaLogin>() {
                @Override
                public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                    if(response.isSuccessful()){
                        mDialog.dismiss();

                        //guardando datos(de acuerdo a la respuesta)
                        SharedPreferencesManager.setSomeStringValue(AppConst.PREF_ID_USUARIO, response.body().getIdUser());
                        SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERTOKEN, response.body().getToken());
                        SharedPreferencesManager.setSomeStringValue(AppConst.PREF_ID_USUARIO, response.body().getIdUser());
                        SharedPreferencesManager.setSomeBooleanValue(AppConst.PREF_STATUS, response.body().getStatus());

                        //SharedPreferencesManager.setSomeStringValue(AppConst.PREF_, response.body().getMessage());
                        String emailPref = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);
                        Toast.makeText(LoginUserActivity.this, "Bienvendo: "+emailPref, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(LoginUserActivity.this, InicioUserActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        mDialog.dismiss();
                        Toast.makeText(LoginUserActivity.this, "Revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                    mDialog.dismiss();
                    Toast.makeText(LoginUserActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}

