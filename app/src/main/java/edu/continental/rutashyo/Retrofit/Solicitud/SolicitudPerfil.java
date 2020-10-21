
package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudPerfil {

    @SerializedName("US_Email")
    @Expose
    private String uSEmail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitudPerfil() {
    }

    /**
     * 
     * @param uSEmail
     */
    public SolicitudPerfil(String uSEmail) {
        super();
        this.uSEmail = uSEmail;
    }

    public String getUSEmail() {
        return uSEmail;
    }

    public void setUSEmail(String uSEmail) {
        this.uSEmail = uSEmail;
    }

}
