package edu.continental.rutashyo.Retrofit.Respuesta;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rutas {

    @SerializedName("longinicial")
    @Expose
    private String longinicial;
    @SerializedName("longfinal")
    @Expose
    private String longfinal;

    /**
     * No args constructor for use in serialization
     *
     */
    public Rutas() {
    }

    /**
     *
     * @param longinicial
     * @param longfinal
     */
    public Rutas(String longinicial, String longfinal) {
        super();
        this.longinicial = longinicial;
        this.longfinal = longfinal;
    }

    public String getLonginicial() {
        return longinicial;
    }

    public void setLonginicial(String longinicial) {
        this.longinicial = longinicial;
    }

    public String getLongfinal() {
        return longfinal;
    }

    public void setLongfinal(String longfinal) {
        this.longfinal = longfinal;
    }

}
