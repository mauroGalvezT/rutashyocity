
package edu.continental.rutashyo.Retrofit.Respuesta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaEmpresa {

    @SerializedName("ID_Conductor")
    @Expose
    private String iDConductor;
    @SerializedName("CON_Latitud")
    @Expose
    private String cONLatitud;
    @SerializedName("CON_Longitud")
    @Expose
    private String cONLongitud;
    @SerializedName("CON_Status")
    @Expose
    private String cONStatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaEmpresa() {
    }

    /**
     * 
     * @param iDConductor
     * @param cONLatitud
     * @param cONStatus
     * @param cONLongitud
     */
    public RespuestaEmpresa(String iDConductor, String cONLatitud, String cONLongitud, String cONStatus) {
        super();
        this.iDConductor = iDConductor;
        this.cONLatitud = cONLatitud;
        this.cONLongitud = cONLongitud;
        this.cONStatus = cONStatus;
    }

    public RespuestaEmpresa(String lat, String lon) {
        super();

        this.cONLatitud = lat;
        this.cONLongitud = lon;
    }

    public String getIDConductor() {
        return iDConductor;
    }

    public void setIDConductor(String iDConductor) {
        this.iDConductor = iDConductor;
    }

    public String getCONLatitud() {
        return cONLatitud;
    }

    public void setCONLatitud(String cONLatitud) {
        this.cONLatitud = cONLatitud;
    }

    public String getCONLongitud() {
        return cONLongitud;
    }

    public void setCONLongitud(String cONLongitud) {
        this.cONLongitud = cONLongitud;
    }

    public String getCONStatus() {
        return cONStatus;
    }

    public void setCONStatus(String cONStatus) {
        this.cONStatus = cONStatus;
    }


}
