package enums;

public enum TipoSuscripcion {

    GRATUITO (0.0, false, 50, false),
    PREMIUM (9.99, true, -1, true),
    FAMILIAR (14.99,true, -1, true),
    ESTUDIANTE (4.99, true, -1, true);


    private double precioMensual;
    private boolean sinAnuncios;
    private int limiteReproducciones;
    private boolean descargasOffline;


    // constructor
    TipoSuscripcion(double v, boolean b, int i, boolean b1) {
    }



    //gets sets

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public boolean isSinAnuncios() {
        return sinAnuncios;
    }

    public void setSinAnuncios(boolean sinAnuncios) {
        this.sinAnuncios = sinAnuncios;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public void setLimiteReproducciones(int limiteReproducciones) {
        this.limiteReproducciones = limiteReproducciones;
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }


}
