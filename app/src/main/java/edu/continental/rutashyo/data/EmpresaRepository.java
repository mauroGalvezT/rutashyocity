package edu.continental.rutashyo.data;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.continental.rutashyo.Retrofit.AuthSmartCityClient;
import edu.continental.rutashyo.Retrofit.Respuesta.Empresa;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.settings.MyApp;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpresaRepository {
    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    MutableLiveData<List<Empresa>> allEmpresas;
    String username;

    EmpresaRepository(){
        smartCityClient=SmartCityClient.getInstance();
        smartCityService=smartCityClient.getSmartCityService();
        allEmpresas=getAllEmpresas();
        //username= SharedPreferencesManager.getSomeStringValue()
    }

    public MutableLiveData<List<Empresa>> getAllEmpresas() {

        if (allEmpresas==null){
            allEmpresas=new MutableLiveData<>();
        }
        Call<List<Empresa>> call=smartCityService.getAllEmpresas();
        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if (response.isSuccessful()){
                    allEmpresas.setValue(response.body());
                }else{
                    Toast.makeText(MyApp.getContext(), "Algo ha salido mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Algo ha salido mal", Toast.LENGTH_SHORT).show();

            }
        });
        return allEmpresas;
    }

}
