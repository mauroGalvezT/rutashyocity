package edu.continental.rutashyo.Retrofit.Respuesta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Respuestaruta {
    @SerializedName("rutas")
    @Expose
    private Rutas rutas;

    /**
     * No args constructor for use in serialization
     *
     */
    public Respuestaruta() {
    }

    /**
     *
     * @param rutas
     */
    public Respuestaruta(Rutas rutas) {
        super();
        this.rutas = rutas;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }
}

