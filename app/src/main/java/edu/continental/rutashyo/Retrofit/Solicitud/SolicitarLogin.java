
package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitarLogin {

    @SerializedName("US_Email")
    @Expose
    private String uSEmail;
    @SerializedName("US_Contrasena")
    @Expose
    private String uSContrasena;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitarLogin() {
    }

    /**
     * 
     * @param uSContrasena
     * @param uSEmail
     */
    public SolicitarLogin(String uSEmail, String uSContrasena) {
        super();
        this.uSEmail = uSEmail;
        this.uSContrasena = uSContrasena;
    }

    public String getUSEmail() {
        return uSEmail;
    }

    public void setUSEmail(String uSEmail) {
        this.uSEmail = uSEmail;
    }

    public String getUSContrasena() {
        return uSContrasena;
    }

    public void setUSContrasena(String uSContrasena) {
        this.uSContrasena = uSContrasena;
    }

}
