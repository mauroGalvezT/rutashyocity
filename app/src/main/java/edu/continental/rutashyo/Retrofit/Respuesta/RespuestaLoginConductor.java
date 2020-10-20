package edu.continental.rutashyo.Retrofit.Respuesta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaLoginConductor {

    @SerializedName("CON_Status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ID_Usuario")
    @Expose
    private String iDUsuario;
    @SerializedName("CON_Email")
    @Expose
    private String conEmail;

    public RespuestaLoginConductor(Boolean status, String message, String iDUsuario, String conEmail) {
        super();
        this.status = status;
        this.message = message;
        this.iDUsuario = iDUsuario;
        this.conEmail = conEmail;
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

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}
