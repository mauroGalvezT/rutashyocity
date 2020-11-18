
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaLogin {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public RespuestaLogin() {
    }

    /**
     *
     * @param idUser
     * @param estado
     * @param status
     * @param token
     */
    public RespuestaLogin(Boolean status, String idUser, String estado, String token) {
        super();
        this.status = status;
        this.idUser = idUser;
        this.estado = estado;
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}