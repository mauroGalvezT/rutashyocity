package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarPass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CambiarPassActivity extends AppCompatActivity {

    EditText edtChangeEmail, edtChangePass;
    FloatingActionButton btnCambiarPass;
    SmartCityClient smartCityClient;
    SmartCityService smartCityService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);
        retrofitInit();
        findViews();
        events();
    }
    private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();
    }
    private void events() {
        btnCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPass();
            }
        });
    }

    private void CambiarPass() {
        String email = edtChangeEmail.getText().toString();
        String pass = edtChangePass.getText().toString();
        if(email.isEmpty()){
            edtChangeEmail.setError("El email es requerido");
        } else if(pass.isEmpty()){
            edtChangePass.setError("Contraseña requerida");
        } else {
            SolicitudCambiarPass solicitudCambiarPass =new SolicitudCambiarPass(email, pass);
            Call<RespuestaRegistro> call = smartCityService.doChangePass(solicitudCambiarPass);
            call.enqueue(new Callback<RespuestaRegistro>() {
                @Override
                public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(CambiarPassActivity.this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CambiarPassActivity.this, InicioUserActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(CambiarPassActivity.this, "Revise el correo acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                    Toast.makeText(CambiarPassActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void findViews() {
        btnCambiarPass = findViewById(R.id.btnCambiarPass);
        edtChangeEmail = findViewById(R.id.edtCambiarEmail);
        edtChangePass = findViewById(R.id.edtCambiarPass);
    }


}