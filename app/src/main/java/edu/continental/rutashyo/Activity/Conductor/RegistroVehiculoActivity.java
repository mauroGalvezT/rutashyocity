package edu.continental.rutashyo.Activity.Conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.Activity.User.InicioUserActivity;
import edu.continental.rutashyo.Activity.User.RegistroUserActivity;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaTipoVehiculo;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaVehiculo;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudVehiculos;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroVehiculoActivity extends AppCompatActivity {

    private String valmarca,valmodelo,valcolor,valplaca;
    EditText edtmarca,edtmodelo,edtcolor,edtnum_placa;
    FloatingActionButton btnRegistrar;
    private TextInputLayout input_layout_marca,input_layout_modelo,input_layout_color,input_layout_placa;
    Spinner tipoVehiculosSpinner;


    SmartCityService smartCityService;
    SmartCityClient smartCityClient;

    AlertDialog mDialog;

    String idConductor;

    String mTipoVehic;
    List<RespuestaTipoVehiculo> tipVehic = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();

        idConductor = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_ID_CONDUCTOR);



        retrofitInit();
        findViews();

        final ArrayAdapter<RespuestaTipoVehiculo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipVehic);
        Call<List<RespuestaTipoVehiculo>> calls = smartCityService.getTipoVehiculos();
        calls.enqueue(new Callback<List<RespuestaTipoVehiculo>>() {
            @Override
            public void onResponse(Call<List<RespuestaTipoVehiculo>> call, Response<List<RespuestaTipoVehiculo>> response) {
                if(response.isSuccessful()){
                    for(RespuestaTipoVehiculo post : response.body()){
                        String id = post.getIDTipoVehiculo();
                        String name = post.getTVNombre();

                        tipVehic.add(new RespuestaTipoVehiculo(id, name));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        tipoVehiculosSpinner.setAdapter(adapter);


                        tipoVehiculosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                                //mTipoVehic = parent.getItemAtPosition(i).toString();
                                mTipoVehic = tipVehic.get(i).getIDTipoVehiculo();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<List<RespuestaTipoVehiculo>> call, Throwable t) {

            }
        });


        events();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Registre su vehiculo porfavor", Toast.LENGTH_SHORT).show();

    }

    private void events() {
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                valmarca=edtmarca.getText().toString();
                valmodelo=edtmodelo.getText().toString();
                valcolor=edtcolor.getText().toString();
                valplaca=edtnum_placa.getText().toString();

                enviarRegistro();
            }

        });
    }

    private void findViews() {
        edtmarca=findViewById(R.id.edtMarca);
        edtmodelo=findViewById(R.id.edtModelo);
        edtcolor=findViewById(R.id.edtColor);
        edtnum_placa=findViewById(R.id.edtNum_placa);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        input_layout_marca=findViewById(R.id.input_layout_marca);
        input_layout_modelo=findViewById(R.id.input_layout_modelo);
        input_layout_color=findViewById(R.id.input_layout_color);
        input_layout_placa=findViewById(R.id.input_layout_placa);
        tipoVehiculosSpinner = findViewById(R.id.spinnerVehiculos);

        spinnerTipoVehiculos();



    }

    private void spinnerTipoVehiculos() {

    }

    private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();
    }

    private void enviarRegistro(){
        if (!validarMarca()) {
            return;
        }
        if (!validarModelo()) {
            return;
        }
        if (!validarColor()) {
            return;
        }
        if (!validarPlaca()) {
            return;
        }
        registrarVehiculo();
    }
    private boolean validarMarca() {
        if (edtmarca.getText().toString().trim().isEmpty()) {
            input_layout_marca.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtmarca);
            return false;
        } else {
            input_layout_marca.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validarModelo() {
        if (edtmodelo.getText().toString().trim().isEmpty()) {
            input_layout_modelo.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtmodelo);
            return false;
        } else {
            input_layout_modelo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarColor() {
        if (edtcolor.getText().toString().trim().isEmpty()) {
            input_layout_color.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtcolor);
            return false;
        } else {
            input_layout_color.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarPlaca() {
        if (edtnum_placa.getText().toString().trim().isEmpty()) {
            input_layout_placa.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtnum_placa);
            return false;
        } else {
            input_layout_placa.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void registrarVehiculo(){
        mDialog.show();
        String token = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);
        String numPlaca=edtnum_placa.getText().toString();
        String color=edtcolor.getText().toString();
        String modelo=edtmodelo.getText().toString();
        String marca=edtmarca.getText().toString();

        SolicitudVehiculos solicitudVehiculos=new SolicitudVehiculos(token,numPlaca,color,modelo,marca,mTipoVehic);

        Call<RespuestaVehiculo> call = smartCityService.doResVehiculo(solicitudVehiculos);
        call.enqueue(new Callback<RespuestaVehiculo>() {
            @Override
            public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                if (response.isSuccessful()){
                    mDialog.dismiss();
                    //Toast.makeText(RegistroVehiculoActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistroVehiculoActivity.this, HomeDriverActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    mDialog.dismiss();
                    Toast.makeText(RegistroVehiculoActivity.this, "Revise sus datos de registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(RegistroVehiculoActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }



}