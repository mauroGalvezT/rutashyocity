package edu.continental.rutashyo.Retrofit;

import java.io.IOException;

import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {//sera invocado cada vez q la interceptamos
        String idUser = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_ID_USUARIO);
        Request request = chain.request().newBuilder().addHeader("ID_Usuario", idUser).build();
        return chain.proceed(request);//proceda a enviar la peticion
    }
}

