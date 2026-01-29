package modelo.contenido;

import java.util.ArrayList;
import java.util.Date;

public abstract class Cancion extends Contenido {

    private String letra;
    private Artista artista;
    private Album album;
    private GeneroMusical genero;
    private String audioURL;
    private boolean explicit;
    private String ISRC;


    public Cancion(String id, String titulo, int reproducciones, int likes, int duracionSegundos, ArrayList<String> tags, boolean disponible, Date fechaPublicacion, String letra, Artista artista, Album album, GeneroMusical genero, String audioURL, boolean explicit, String ISRC) {
        super(id, titulo, reproducciones, likes, duracionSegundos, tags, disponible, fechaPublicacion);
        this.letra = letra;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.audioURL = audioURL;
        this.explicit = explicit;
        this.ISRC = ISRC;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }


    @Override
    public void reproducir(){

    }

    public String obtenerLetra(){

    }

    public boolean esExplicit(){
        return true;
    }

    public void cambiarGenero (GeneroMusical genero){

    }


    public void play(){

    }

    public void pause(){

    }

    public void stop(){

    }

    public boolean descargar (){

    }

    public boolean eliminarDescarga (){

    }




}
