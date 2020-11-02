package edu.continental.rutashyo.Activity.Conductor;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.Activity.User.InicioUserActivity;
import edu.continental.rutashyo.Activity.User.LoginUserActivity;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLoginConductor;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLoginConductor;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginConductorActivity extends AppCompatActivity {

    EditText edtEmailLogin, edtPassLogin;
    String valEmailLogin, valPassLogin;
    FloatingActionButton btnIniciarSesion;
    TextView txtRecuperarPass;
    SharedPreferences mPref;

    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;

    String idPref;
    String emailPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_conductor);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();

        retrofitInit();
        findViews();
        events();
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

            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String user = mPref.getString("user", "");
                        if (user.equals("driver")) {
                            Intent intent = new Intent(LoginConductorActivity.this, ConductorMapActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                    }
                    else {
                        Toast.makeText(LoginConductorActivity.this, "La contraseña o el password son incorrectos", Toast.LENGTH_SHORT).show();
                    }
                    mDialog.dismiss();
                }
            });
            /*
            SolicitarLoginConductor solicitarLoginconductor =new SolicitarLoginConductor(email, pass);
            Call<RespuestaLoginConductor> call = smartCityService.doLoginConductor(solicitarLoginconductor);
            call.enqueue(new Callback<RespuestaLoginConductor>() {
                @Override
                public void onResponse(Call<RespuestaLoginConductor> call, retrofit2.Response<RespuestaLoginConductor> response) {
                    if(response.isSuccessful()){
                        mDialog.dismiss();
                        SharedPreferencesManager.setSomeStringValue(AppConst.PREF_EMAIL, response.body().getConEmail());
                        SharedPreferencesManager.setSomeStringValue(AppConst.PREF_ID_CONDUCTOR, response.body().getiDConductor());
                        idPref=SharedPreferencesManager.getSomeStringValue(AppConst.PREF_ID_CONDUCTOR);
                        emailPref=SharedPreferencesManager.getSomeStringValue(AppConst.PREF_EMAIL);


                        Toast.makeText(LoginConductorActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginConductorActivity.this, RegistroVehiculoActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        mDialog.dismiss();
                        Toast.makeText(LoginConductorActivity.this, "Revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLoginConductor> call, Throwable t) {
                    mDialog.dismiss();
                    Toast.makeText(LoginConductorActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
            */

        }
    }

}