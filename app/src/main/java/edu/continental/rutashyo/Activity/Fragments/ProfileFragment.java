package edu.continental.rutashyo.Activity.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.Activity.User.RegistroUserActivity;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudUpdatePerfil;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    EditText edtCambiarNombre, edtCambiarApellido, edtCambiarTelefono, edtCambiarEmail, edtCambiarPassProfile;
    Button btnSave;
    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    AlertDialog mDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new SpotsDialog.Builder()
                .setContext(getActivity())
                .setMessage("Espere un momento")
                .setCancelable(false).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        edtCambiarNombre = v.findViewById(R.id.edtCambiarNombre);
        edtCambiarApellido = v.findViewById(R.id.edtCambiarApellido);
        edtCambiarTelefono = v.findViewById(R.id.edtCambiarTelefono);
        edtCambiarEmail = v.findViewById(R.id.edtCambiarEmail);
        edtCambiarPassProfile = v.findViewById(R.id.edtCambiarPassProfile);
        btnSave = v.findViewById(R.id.btnSveUpdateProfile);
        String nombre = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERNAMES);
        String apellido = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERAPELLIDO);
        String telefono = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTELEFONO);
        String email = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USEREMAIL);
        String direccion = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERDIRECCION);
        edtCambiarNombre.setText(nombre);
        edtCambiarApellido.setText(apellido);
        edtCambiarTelefono.setText(telefono);
        edtCambiarEmail.setText(email);
        edtCambiarPassProfile.setText(direccion);
        retrofitInit();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerfil();

            }
        });




        return v;
    }

    private void updatePerfil() {
        mDialog.show();
        String idPerfil = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_ID_USUARIO);
        String name = edtCambiarNombre.getText().toString();
        String apellido = edtCambiarApellido.getText().toString();
        String direccion = edtCambiarPassProfile.getText().toString();
        String nacimiento = "null";

        String nacionalidad = "PERU";
        String telefono = edtCambiarTelefono.getText().toString();
        String email = edtCambiarEmail.getText().toString();

        SolicitudUpdatePerfil solicitudUpdatePerfil = new SolicitudUpdatePerfil(idPerfil, name, apellido,direccion,nacimiento,nacionalidad,telefono,email);
        Call<RespuestaRegistro> call = smartCityService.doUpdateProfile(solicitudUpdatePerfil);
        call.enqueue(new Callback<RespuestaRegistro>() {
            @Override
            public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                if(response.isSuccessful()){
                    mDialog.dismiss();
                    Toast.makeText(getActivity(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();



                }else{
                    mDialog.dismiss();
                    Toast.makeText(getActivity(), "Revise su datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(getActivity(), "Problemas de conexion", Toast.LENGTH_SHORT).show();


            }
        });





    }


        private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();

    }
}