package edu.continental.rutashyo.Retrofit.Solicitud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudVehiculos {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("placa")
    @Expose
    private String placa;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("idTipoVehiculo")
    @Expose
    private String idTipoVehiculo;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitudVehiculos() {
    }

    /**
     *
     * @param idTipoVehiculo
     * @param marca
     * @param color
     * @param modelo
     * @param token
     * @param placa
     */
    public SolicitudVehiculos(String token, String placa, String color, String modelo, String marca, String idTipoVehiculo) {
        super();
        this.token = token;
        this.placa = placa;
        this.color = color;
        this.modelo = modelo;
        this.marca = marca;
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(String idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

}