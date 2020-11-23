package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitarLoginConductor {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitarLoginConductor() {
    }

    /**
     *
     * @param password
     * @param usuario
     */
    public SolicitarLoginConductor(String usuario, String password) {
        super();
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
