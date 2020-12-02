
package edu.continental.rutashyo.Retrofit.Respuesta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaEmpresas {

    @SerializedName("idconducto1")
    @Expose
    private String idconducto1;
    @SerializedName("lat1")
    @Expose
    private String lat1;
    @SerializedName("long1")
    @Expose
    private String long1;
    @SerializedName("idconducto2")
    @Expose
    private String idconducto2;
    @SerializedName("lat2")
    @Expose
    private String lat2;
    @SerializedName("long2")
    @Expose
    private String long2;
    @SerializedName("idconducto3")
    @Expose
    private String idconducto3;
    @SerializedName("lat3")
    @Expose
    private String lat3;
    @SerializedName("long3")
    @Expose
    private String long3;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaEmpresas() {
    }

    /**
     * 
     * @param lat1
     * @param long2
     * @param lat2
     * @param long3
     * @param idconducto1
     * @param long1
     * @param idconducto2
     * @param idconducto3
     * @param lat3
     */
    public RespuestaEmpresas(String idconducto1, String lat1, String long1, String idconducto2, String lat2, String long2, String idconducto3, String lat3, String long3) {
        super();
        this.idconducto1 = idconducto1;
        this.lat1 = lat1;
        this.long1 = long1;
        this.idconducto2 = idconducto2;
        this.lat2 = lat2;
        this.long2 = long2;
        this.idconducto3 = idconducto3;
        this.lat3 = lat3;
        this.long3 = long3;
    }

    public String getIdconducto1() {
        return idconducto1;
    }

    public void setIdconducto1(String idconducto1) {
        this.idconducto1 = idconducto1;
    }

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLong1() {
        return long1;
    }

    public void setLong1(String long1) {
        this.long1 = long1;
    }

    public String getIdconducto2() {
        return idconducto2;
    }

    public void setIdconducto2(String idconducto2) {
        this.idconducto2 = idconducto2;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLong2() {
        return long2;
    }

    public void setLong2(String long2) {
        this.long2 = long2;
    }

    public String getIdconducto3() {
        return idconducto3;
    }

    public void setIdconducto3(String idconducto3) {
        this.idconducto3 = idconducto3;
    }

    public String getLat3() {
        return lat3;
    }

    public void setLat3(String lat3) {
        this.lat3 = lat3;
    }

    public String getLong3() {
        return long3;
    }

    public void setLong3(String long3) {
        this.long3 = long3;
    }

}
