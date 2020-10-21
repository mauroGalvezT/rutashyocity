
package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudVehiculos {

    @SerializedName("VEH_Placa")
    @Expose
    private String vEHPlaca;
    @SerializedName("VEH_Color")
    @Expose
    private String vEHColor;
    @SerializedName("VEH_Modelo")
    @Expose
    private String vEHModelo;
    @SerializedName("VEH_Marca")
    @Expose
    private String vEHMarca;
    @SerializedName("ID_Tipo_Vehiculo")
    @Expose
    private String iDTipoVehiculo;
    @SerializedName("ID_Conductor")
    @Expose
    private String iDConductor;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitudVehiculos() {
    }

    /**
     * 
     * @param iDConductor
     * @param vEHModelo
     * @param vEHColor
     * @param vEHMarca
     * @param vEHPlaca
     * @param iDTipoVehiculo
     */
    public SolicitudVehiculos(String vEHPlaca, String vEHColor, String vEHModelo, String vEHMarca, String iDTipoVehiculo, String iDConductor) {
        super();
        this.vEHPlaca = vEHPlaca;
        this.vEHColor = vEHColor;
        this.vEHModelo = vEHModelo;
        this.vEHMarca = vEHMarca;
        this.iDTipoVehiculo = iDTipoVehiculo;
        this.iDConductor = iDConductor;
    }

    public String getVEHPlaca() {
        return vEHPlaca;
    }

    public void setVEHPlaca(String vEHPlaca) {
        this.vEHPlaca = vEHPlaca;
    }

    public String getVEHColor() {
        return vEHColor;
    }

    public void setVEHColor(String vEHColor) {
        this.vEHColor = vEHColor;
    }

    public String getVEHModelo() {
        return vEHModelo;
    }

    public void setVEHModelo(String vEHModelo) {
        this.vEHModelo = vEHModelo;
    }

    public String getVEHMarca() {
        return vEHMarca;
    }

    public void setVEHMarca(String vEHMarca) {
        this.vEHMarca = vEHMarca;
    }

    public String getIDTipoVehiculo() {
        return iDTipoVehiculo;
    }

    public void setIDTipoVehiculo(String iDTipoVehiculo) {
        this.iDTipoVehiculo = iDTipoVehiculo;
    }

    public String getIDConductor() {
        return iDConductor;
    }

    public void setIDConductor(String iDConductor) {
        this.iDConductor = iDConductor;
    }

}
