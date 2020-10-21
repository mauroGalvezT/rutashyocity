
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaPerfil {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ID_Usuario")
    @Expose
    private String iDUsuario;
    @SerializedName("US_Nombres")
    @Expose
    private String uSNombres;
    @SerializedName("US_Apellidos")
    @Expose
    private String uSApellidos;
    @SerializedName("US_Direccion")
    @Expose
    private String uSDireccion;
    @SerializedName("US_Fecha_Nacimiento")
    @Expose
    private String uSFechaNacimiento;
    @SerializedName("US_Nacionalidad")
    @Expose
    private String uSNacionalidad;
    @SerializedName("US_Telefono")
    @Expose
    private String uSTelefono;
    @SerializedName("US_Email")
    @Expose
    private String uSEmail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaPerfil() {
    }

    /**
     * 
     * @param uSTelefono
     * @param uSEmail
     * @param uSNombres
     * @param uSApellidos
     * @param message
     * @param uSFechaNacimiento
     * @param iDUsuario
     * @param uSDireccion
     * @param uSNacionalidad
     * @param status
     */
    public RespuestaPerfil(Boolean status, String message, String iDUsuario, String uSNombres, String uSApellidos, String uSDireccion, String uSFechaNacimiento, String uSNacionalidad, String uSTelefono, String uSEmail) {
        super();
        this.status = status;
        this.message = message;
        this.iDUsuario = iDUsuario;
        this.uSNombres = uSNombres;
        this.uSApellidos = uSApellidos;
        this.uSDireccion = uSDireccion;
        this.uSFechaNacimiento = uSFechaNacimiento;
        this.uSNacionalidad = uSNacionalidad;
        this.uSTelefono = uSTelefono;
        this.uSEmail = uSEmail;
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

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getUSNombres() {
        return uSNombres;
    }

    public void setUSNombres(String uSNombres) {
        this.uSNombres = uSNombres;
    }

    public String getUSApellidos() {
        return uSApellidos;
    }

    public void setUSApellidos(String uSApellidos) {
        this.uSApellidos = uSApellidos;
    }

    public String getUSDireccion() {
        return uSDireccion;
    }

    public void setUSDireccion(String uSDireccion) {
        this.uSDireccion = uSDireccion;
    }

    public String getUSFechaNacimiento() {
        return uSFechaNacimiento;
    }

    public void setUSFechaNacimiento(String uSFechaNacimiento) {
        this.uSFechaNacimiento = uSFechaNacimiento;
    }

    public String getUSNacionalidad() {
        return uSNacionalidad;
    }

    public void setUSNacionalidad(String uSNacionalidad) {
        this.uSNacionalidad = uSNacionalidad;
    }

    public String getUSTelefono() {
        return uSTelefono;
    }

    public void setUSTelefono(String uSTelefono) {
        this.uSTelefono = uSTelefono;
    }

    public String getUSEmail() {
        return uSEmail;
    }

    public void setUSEmail(String uSEmail) {
        this.uSEmail = uSEmail;
    }

}
