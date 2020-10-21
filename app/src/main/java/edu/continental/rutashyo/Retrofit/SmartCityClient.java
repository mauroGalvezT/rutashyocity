package edu.continental.rutashyo.Retrofit;

import edu.continental.rutashyo.settings.AppConst;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmartCityClient {
    private static SmartCityClient instance = null;
    private SmartCityService smartCityService;
    private Retrofit retrofit;

    public SmartCityClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConst.Server_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        smartCityService = retrofit.create(SmartCityService.class);
    }

    public static SmartCityClient getInstance(){
        if(instance == null){
            instance = new SmartCityClient();
        }
        return instance;
    }

    public  SmartCityService getSmartCityService(){
        return smartCityService;
    }


}
