package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaTipoVehiculo {

    @SerializedName("ID_Tipo_Vehiculo")
    @Expose
    private String iDTipoVehiculo;
    @SerializedName("TV_Nombre")
    @Expose
    private String tVNombre;

    /**
     * No args constructor for use in serialization
     *
     */
    public RespuestaTipoVehiculo() {
    }

    /**
     *
     * @param tVNombre
     * @param iDTipoVehiculo
     */
    public RespuestaTipoVehiculo(String iDTipoVehiculo, String tVNombre) {
        super();
        this.iDTipoVehiculo = iDTipoVehiculo;
        this.tVNombre = tVNombre;
    }


    public String getIDTipoVehiculo() {
        return iDTipoVehiculo;
    }

    public void setIDTipoVehiculo(String iDTipoVehiculo) {
        this.iDTipoVehiculo = iDTipoVehiculo;
    }

    public String getTVNombre() {
        return tVNombre;
    }

    public void setTVNombre(String tVNombre) {
        this.tVNombre = tVNombre;
    }
    public RespuestaTipoVehiculo(String tVNombre) {
        this.tVNombre = tVNombre;
    }
    public String toString(){
        return tVNombre;
    }
}

