package edu.continental.rutashyo.data;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import edu.continental.rutashyo.Retrofit.AuthSmartCityClient;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudPerfil;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.MyApp;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    SmartCityService smartCityService;
    AuthSmartCityClient smartCityClient;
    MutableLiveData<RespuestaPerfil> userProfile;

    ProfileRepository() {
        smartCityClient = AuthSmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();
        userProfile = getProfile();
    }

    public MutableLiveData<RespuestaPerfil> getProfile() {
        if (userProfile == null) {
            userProfile = new MutableLiveData<>();
        }
        String idUser = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_ID_USUARIO);
        SolicitudPerfil solicitudPerfil =new SolicitudPerfil(idUser);

        Call<RespuestaPerfil> call = smartCityService.getProfile(solicitudPerfil);
        call.enqueue(new Callback<RespuestaPerfil>() {
            @Override
            public void onResponse(Call<RespuestaPerfil> call, Response<RespuestaPerfil> response) {
                if(response.isSuccessful()){
                    userProfile.setValue(response.body());
                }else{
                    Toast.makeText(MyApp.getContext(), "algo ha ido mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaPerfil> call, Throwable t) {

                Toast.makeText(MyApp.getContext(), "error en la conexin", Toast.LENGTH_SHORT).show();
            }
        });


        return userProfile;
    }

}
