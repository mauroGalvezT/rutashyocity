package edu.continental.rutashyo.Retrofit;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLogin;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaLoginConductor;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaVehiculo;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLogin;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarLoginConductor;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarRegistro;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarPass;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudVehiculos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SmartCityService {


    @POST("Usuario/login.php")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Conductor/login.php")
    Call<RespuestaLoginConductor> doLoginConductor(@Body SolicitarLoginConductor solicitarLoginConductor);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

    @POST("Usuario/change_password.php")
    Call<RespuestaRegistro> doChangePass(@Body SolicitudCambiarPass solicitudCambiarPass);

    @POST("Vehiculo/Insertar_Vehiculo.php")
    Call<RespuestaVehiculo> doResVehiculo(@Body SolicitudVehiculos solicitudVehiculos);

}
