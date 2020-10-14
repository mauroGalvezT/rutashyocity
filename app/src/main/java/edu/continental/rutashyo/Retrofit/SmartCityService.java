package edu.continental.rutashyo.Retrofit;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarPass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SmartCityService {


    @POST("Usuario/login.php")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

    @POST("Usuario/change_password.php")
    Call<RespuestaRegistro> doChangePass(@Body SolicitudCambiarPass solicitudCambiarPass);

}
