package edu.continental.rutashyo.Activity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;


public class ProfileFragment extends Fragment {
    EditText edtCambiarNombre, edtCambiarApellido, edtCambiarTelefono, edtCambiarEmail, edtCambiarPassProfile;
    Button btnSave;

    public ProfileFragment() {
        // Required empty public constructor
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
        btnSave = v.findViewById(R.id.btnSave);
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



        return v;
    }
}