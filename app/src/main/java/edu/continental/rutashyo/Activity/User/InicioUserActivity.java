package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.Activity.Fragments.HomeFragment;
import edu.continental.rutashyo.Activity.Fragments.ProfileFragment;
import edu.continental.rutashyo.Activity.Fragments.ProfilesFragment;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudPerfil;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.PrefManager;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioUserActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    //String emailPref;
    AlertDialog mDialog;
    Dialog dialogPersonalizado;
    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    EditText edtEmailConfirmPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_user);


        String empresaID=getIntent().getStringExtra("idEmpresa");

        Toast.makeText(this, "empresa "+empresaID, Toast.LENGTH_SHORT).show();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();
        retrofitInit();
        openFragment(new HomeFragment());
      //  emailPref = PrefManager.getSomeStringValue(AppConst.PREF_EMAIL);

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemHome:
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.itemProfile:
                            mDialog.show();

                            dialogPersonalizado = new Dialog(InicioUserActivity.this);
                            dialogPersonalizado.setContentView(R.layout.dialog_confirm_email);
// Podemos obtener los elementos dentro del layout ;)
                            Button btnAlertaPasswordOk = dialogPersonalizado.findViewById(R.id.btnAlertaPasswordOk);
                            Button btnCancelar = dialogPersonalizado.findViewById(R.id.btnAlertaPasswordCancelar);
                            btnCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogPersonalizado.dismiss();
                                    mDialog.dismiss();
                                }
                            });

                            edtEmailConfirmPerfil = dialogPersonalizado.findViewById(R.id.editTextConfirmEmail);
                            btnAlertaPasswordOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String email = edtEmailConfirmPerfil.getText().toString();
                                    if(email.isEmpty()){
                                        edtEmailConfirmPerfil.setError("El email es requerido");
                                        dialogPersonalizado.dismiss();
                                    } else {
                                        SolicitudPerfil solicitudPerfil = new SolicitudPerfil(email);

                                        Call<RespuestaPerfil> call = smartCityService.getProfile(solicitudPerfil);
                                        call.enqueue(new Callback<RespuestaPerfil>() {
                                            @Override
                                            public void onResponse(Call<RespuestaPerfil> call, Response<RespuestaPerfil> response) {
                                                if(response.isSuccessful()){
                                                    mDialog.dismiss();
                                                    SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERNAMES, response.body().getUSNombres());
                                                    SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERAPELLIDO, response.body().getUSApellidos());
                                                    SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERTELEFONO, response.body().getUSTelefono());
                                                    SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USEREMAIL, response.body().getUSEmail());
                                                    SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERDIRECCION, response.body().getUSDireccion());
                                                    openFragment(new ProfileFragment());
                                                    dialogPersonalizado.dismiss();
                                                }else{
                                                    Toast.makeText(InicioUserActivity.this, "Inicio sus datos de acceso", Toast.LENGTH_SHORT).show();

                                                }



                                                // SharedPreferencesManager.setSomeStringValue(AppConst.PREF_USERNAME, response.body().getUSNombres());
                                            }

                                            @Override
                                            public void onFailure(Call<RespuestaPerfil> call, Throwable t) {
                                                Toast.makeText(InicioUserActivity.this, "problemas de conexion", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }

                                }
                            });
                            dialogPersonalizado.show();

                            return true;
                    }
                    return false;
                }
            };


}