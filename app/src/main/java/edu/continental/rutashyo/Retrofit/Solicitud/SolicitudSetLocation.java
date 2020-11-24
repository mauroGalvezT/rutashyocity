package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudSetLocation {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitudSetLocation() {
    }

    /**
     *
     * @param latitud
     * @param longitud
     * @param token
     */
    public SolicitudSetLocation(String token, String latitud, String longitud) {
        super();
        this.token = token;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}