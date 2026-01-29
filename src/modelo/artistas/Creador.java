package modelo.artistas;

import java.util.ArrayList;
import java.util.HashMap;

public class Creador {

    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String ,String> redesSociales;
    private ArrayList<CategoriaPodcast> catergoriasPrincipales;


    public Creador(String id, String nombreCanal, String nombre, ArrayList<Podcast> episodios, int suscriptores, String descripcion, HashMap<String, String> redesSociales, ArrayList<CategoriaPodcast> catergoriasPrincipales) {
        this.id = id;
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.episodios = episodios;
        this.suscriptores = suscriptores;
        this.descripcion = descripcion;
        this.redesSociales = redesSociales;
        this.catergoriasPrincipales = catergoriasPrincipales;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Podcast> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(ArrayList<Podcast> episodios) {
        this.episodios = episodios;
    }

    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HashMap<String, String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(HashMap<String, String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public ArrayList<CategoriaPodcast> getCatergoriasPrincipales() {
        return catergoriasPrincipales;
    }

    public void setCatergoriasPrincipales(ArrayList<CategoriaPodcast> catergoriasPrincipales) {
        this.catergoriasPrincipales = catergoriasPrincipales;
    }


    public void publicarPodcast ( Podcast episodio){

    }

    public EstadisticasCreador obtenerEstadisticas (){

    }

    public void agregarRedSocial (String red, String usuario){

    }

    public double calcularPromedioReproducciones(){

    }

    public void eliminarEpisodio (String idEpisodio){

    }

}
