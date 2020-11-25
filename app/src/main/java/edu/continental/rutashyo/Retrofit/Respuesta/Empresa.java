
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("ID_Empresa_Transp")
    @Expose
    private String iDEmpresaTransp;
    @SerializedName("EMT_Nombre")
    @Expose
    private String eMTNombre;
    @SerializedName("EMT_Ruc")
    @Expose
    private String eMTRuc;
    @SerializedName("EMT_Telefono")
    @Expose
    private String eMTTelefono;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Empresa() {
    }

    /**
     * 
     * @param eMTTelefono
     * @param eMTRuc
     * @param iDEmpresaTransp
     * @param eMTNombre
     */
    public Empresa(String iDEmpresaTransp, String eMTNombre, String eMTRuc, String eMTTelefono) {
        super();
        this.iDEmpresaTransp = iDEmpresaTransp;
        this.eMTNombre = eMTNombre;
        this.eMTRuc = eMTRuc;
        this.eMTTelefono = eMTTelefono;
    }

    public String getIDEmpresaTransp() {
        return iDEmpresaTransp;
    }

    public void setIDEmpresaTransp(String iDEmpresaTransp) {
        this.iDEmpresaTransp = iDEmpresaTransp;
    }

    public String getEMTNombre() {
        return eMTNombre;
    }

    public void setEMTNombre(String eMTNombre) {
        this.eMTNombre = eMTNombre;
    }

    public String getEMTRuc() {
        return eMTRuc;
    }

    public void setEMTRuc(String eMTRuc) {
        this.eMTRuc = eMTRuc;
    }

    public String getEMTTelefono() {
        return eMTTelefono;
    }

    public void setEMTTelefono(String eMTTelefono) {
        this.eMTTelefono = eMTTelefono;
    }

}
