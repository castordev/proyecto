package modelo.artistas;

import java.util.ArrayList;

public class Artista {

    private String id;
    private String nombreArtistico;
    private String paisOrigen;
    private ArrayList<Cancion> discografia;
    private ArrayList<Album> albumes;
    private int oyentesMensuales;
    private boolean verificado;
    private String biografia;

    public Artista(String id, String nombreArtistico, String paisOrigen, ArrayList<Cancion> discografia, ArrayList<Album> albumes, int oyentesMensuales, boolean verificado, String biografia) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.paisOrigen = paisOrigen;
        this.discografia = discografia;
        this.albumes = albumes;
        this.oyentesMensuales = oyentesMensuales;
        this.verificado = verificado;
        this.biografia = biografia;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public ArrayList<Cancion> getDiscografia() {
        return discografia;
    }

    public void setDiscografia(ArrayList<Cancion> discografia) {
        this.discografia = discografia;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void publicarCancion (Cancion cancion){

    }

    public Album crearAlbum (String titulo, Date fecha){

    }

    public ArrayList<Cancion> obtenerTopCanciones (int cantidad){

    }

    public double calcularPromedioReproducciones(){

    }

    public boolean esVerificado (){

    }


}
