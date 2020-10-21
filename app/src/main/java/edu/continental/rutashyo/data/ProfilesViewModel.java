package edu.continental.rutashyo.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;

public class ProfilesViewModel extends AndroidViewModel {
    public  ProfileRepository profileRepository;
    public LiveData<RespuestaPerfil> userProfile;
    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository();
        userProfile = profileRepository.getProfile();
    }


}