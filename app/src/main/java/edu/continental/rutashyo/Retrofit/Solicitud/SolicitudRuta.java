package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudRuta {

    @SerializedName("idEmpresa")
    @Expose
    private String idEmpresa;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitudRuta() {
    }

    /**
     *
     * @param idEmpresa
     */
    public SolicitudRuta(String idEmpresa) {
        super();
        this.idEmpresa = idEmpresa;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

}