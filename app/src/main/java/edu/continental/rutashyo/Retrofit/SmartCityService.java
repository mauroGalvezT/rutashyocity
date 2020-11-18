package edu.continental.rutashyo.Retrofit;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaPerfil;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarPass;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudPerfil;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudUpdatePerfil;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SmartCityService {


    @POST("api/auth/login")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

    @POST("Usuario/change_password.php")
    Call<RespuestaRegistro> doChangePass(@Body SolicitudCambiarPass solicitudCambiarPass);

    @POST("Usuario/Consulta_Usuario_Email.php")
    Call<RespuestaPerfil> getProfile(@Body SolicitudPerfil solicitarPerfil);

    @POST("Usuario/Update_Usuario.php")
    Call<RespuestaRegistro> doUpdateProfile(@Body SolicitudUpdatePerfil solicitudUpdatePerfil);

}
