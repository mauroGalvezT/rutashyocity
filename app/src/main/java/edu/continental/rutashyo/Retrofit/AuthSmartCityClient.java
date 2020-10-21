package edu.continental.rutashyo.Retrofit;

import edu.continental.rutashyo.settings.AppConst;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthSmartCityClient {
    private static AuthSmartCityClient instance = null;
    private SmartCityService smartCityService;
    private Retrofit retrofit;

    public AuthSmartCityClient(){
//incluir en la cabecera de la peticion el token que autoriza el usuario
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConst.Server_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();
        smartCityService = retrofit.create(SmartCityService.class);
    }

    //la instancia solo se creara una vez: singleton
    public static AuthSmartCityClient getInstance(){
        if(instance == null){
            instance = new AuthSmartCityClient();
        }
        return instance;
    }

    public SmartCityService getSmartCityService(){
        return smartCityService;
    }
}
