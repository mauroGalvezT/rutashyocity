package edu.continental.rutashyo.Retrofit;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SmartCityService {


    @POST("Usuario/login.php")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

}
