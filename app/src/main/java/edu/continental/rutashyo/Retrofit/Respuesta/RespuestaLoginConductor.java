package edu.continental.rutashyo.Retrofit.Respuesta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaLoginConductor {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ID_Conductor")
    @Expose
    private String iDConductor;
    @SerializedName("CON_Email")
    @Expose
    private String conEmail;

    public RespuestaLoginConductor(Boolean status, String message, String iDConductor, String conEmail) {
        super();
        this.status = status;
        this.message = message;
        this.iDConductor = iDConductor;
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

    public String getiDConductor() {
        return iDConductor;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDConductor = iDUsuario;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}
