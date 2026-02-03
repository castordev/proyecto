package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.ArrayList;
import java.util.HashMap;

public class Creador {

    private static final int MAX_EPISODIOS = 500;

    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String ,String> redesSociales;
    private ArrayList<CategoriaPodcast> catergoriasPrincipales;


    public Creador(String nombreCanal, String nombre) {
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
    }

    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException{

    }

    public EstadisticasCreador obtenerEstadisticas(){

    }

    public void agregarRedSocial(String red, String usuario){

    }

    public double calcularPromedioReproducciones(){

    }

    //eliminar episodio por ID
    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException{

    }

    public int getTotalReproducciones(){

    }

    public void incrementarSuscriptores(){

    }


    //obtener los x episodios mas reproducidos
    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad){

    }

    public int getUltimaTemporada(){

    }


    public String getId(){
        return id;
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


    public ArrayList<CategoriaPodcast> getCatergoriasPrincipales() {
        return catergoriasPrincipales;
    }

    public int getNumEpisodios(){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
