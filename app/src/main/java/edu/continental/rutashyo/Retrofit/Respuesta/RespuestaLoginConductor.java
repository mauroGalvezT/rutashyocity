
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaLoginConductor {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_empresa_trasnporte")
    @Expose
    private String idEmpresaTrasnporte;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;

    /**
     * No args constructor for use in serialization
     *
     */
    public RespuestaLoginConductor() {
    }

    /**
     *
     * @param idUser
     * @param idEmpresaTrasnporte
     * @param apellido
     * @param nombre
     * @param status
     * @param token
     */
    public RespuestaLoginConductor(Boolean status, String token, String idUser, String idEmpresaTrasnporte, String nombre, String apellido) {
        super();
        this.status = status;
        this.token = token;
        this.idUser = idUser;
        this.idEmpresaTrasnporte = idEmpresaTrasnporte;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdEmpresaTrasnporte() {
        return idEmpresaTrasnporte;
    }

    public void setIdEmpresaTrasnporte(String idEmpresaTrasnporte) {
        this.idEmpresaTrasnporte = idEmpresaTrasnporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}