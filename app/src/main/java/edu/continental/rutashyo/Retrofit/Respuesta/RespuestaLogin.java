
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaLogin {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ID_Usuario")
    @Expose
    private String iDUsuario;
    @SerializedName("US_Email")
    @Expose
    private String uSEmail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaLogin() {
    }

    /**
     * 
     * @param uSEmail
     * @param message
     * @param iDUsuario
     * @param status
     */
    public RespuestaLogin(Boolean status, String message, String iDUsuario, String uSEmail) {
        super();
        this.status = status;
        this.message = message;
        this.iDUsuario = iDUsuario;
        this.uSEmail = uSEmail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getUSEmail() {
        return uSEmail;
    }

    public void setUSEmail(String uSEmail) {
        this.uSEmail = uSEmail;
    }

}
