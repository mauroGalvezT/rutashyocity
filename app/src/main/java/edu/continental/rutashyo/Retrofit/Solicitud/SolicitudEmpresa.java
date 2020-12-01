
package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudEmpresa {

    @SerializedName("ID_Empresa_Transp")
    @Expose
    private String iDEmpresaTransp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitudEmpresa() {
    }

    /**
     * 
     * @param iDEmpresaTransp
     */
    public SolicitudEmpresa(String iDEmpresaTransp) {
        super();
        this.iDEmpresaTransp = iDEmpresaTransp;
    }

    public String getIDEmpresaTransp() {
        return iDEmpresaTransp;
    }

    public void setIDEmpresaTransp(String iDEmpresaTransp) {
        this.iDEmpresaTransp = iDEmpresaTransp;
    }

}
