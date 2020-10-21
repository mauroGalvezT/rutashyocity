package edu.continental.rutashyo.Activity.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;
import edu.continental.rutashyo.data.ProfilesViewModel;
import edu.continental.rutashyo.R;

public class ProfilesFragment extends Fragment {

    private ProfilesViewModel profilesViewModel;
    EditText edtCambiarNombre, edtCambiarApellido, edtCambiarTelefono, edtCambiarEmail, edtCambiarPassProfile;
    Button btnSave;

    public static ProfilesFragment newInstance() {
        return new ProfilesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.profiles_fragment, container, false);
        edtCambiarNombre = v.findViewById(R.id.edtCambiarNombre);
        edtCambiarApellido = v.findViewById(R.id.edtCambiarApellido);
        edtCambiarTelefono = v.findViewById(R.id.edtCambiarTelefono);
        edtCambiarEmail = v.findViewById(R.id.edtCambiarEmail);
        edtCambiarPassProfile = v.findViewById(R.id.edtCambiarPassProfile);
        btnSave = v.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "guardar", Toast.LENGTH_SHORT).show();
            }
        });

        profilesViewModel.userProfile.observe(getActivity(), new Observer<RespuestaPerfil>() {
            @Override
            public void onChanged(RespuestaPerfil responseUserProfile) {
                edtCambiarNombre.setText(responseUserProfile.getUSNombres());
                edtCambiarEmail.setText(responseUserProfile.getUSEmail());
                edtCambiarApellido.setText(responseUserProfile.getUSApellidos());
                edtCambiarTelefono.setText(responseUserProfile.getUSTelefono());;


            }
        });


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // mViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);
        // TODO: Use the ViewModel
    }

}