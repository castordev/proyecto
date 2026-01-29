package enums;

public enum AlgoritmoRecomendacion {

    COLABORATIVO ("Basado en usuarios similares"),
    CONTENIDO ("Basado en características del contenido"),
    HIBRIDO ("Combinación de ambos");

    private  String descripcion;

    // contructor

    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }


    //gets sets


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
