package edu.continental.rutashyo.Retrofit.Solicitud;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SolicitarLoginConductor {

    @SerializedName("CON_Email")
    @Expose
    private String conEmail;
    @SerializedName("CON_Contrasena")
    @Expose
    private String conContrasena;

    public SolicitarLoginConductor(String conEmail, String conContrasena) {
        super();
        this.setConEmail(conEmail);
        this.setConContrasena(conContrasena);
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getConContrasena() {
        return conContrasena;
    }

    public void setConContrasena(String conContrasena) {
        this.conContrasena = conContrasena;
    }
}
