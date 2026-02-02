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

    public boolean isSinAnuncios() {
        return sinAnuncios;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }


    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public boolean tieneReproducionesIlimitadas(){
        return limiteReproducciones == -1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
