package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudCambiarEstado {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("estado")
    @Expose
    private String estado;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitudCambiarEstado() {
    }

    /**
     *
     * @param estado
     * @param token
     */
    public SolicitudCambiarEstado(String token, String estado) {
        super();
        this.token = token;
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}