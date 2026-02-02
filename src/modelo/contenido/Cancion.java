package modelo.contenido;

import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.LimiteDescargasException;
import modelo.artistas.Album;
import modelo.artistas.Artista;

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
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;


    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
    }

    public Cancion (String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit) throws DuracionInvalidaException{
        super(titulo,duracionSegundos);
        this.artista  = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;
    }

    // metodos privados

    private String generarISRC(){

    }


    // Overrides

    @Override
    public void reproducir() throws ContenidoNoDisponibleException{

    }


    //Implementacion interfaz Reproducible:

    public void play(){

    }

    public void pause(){

    }

    public void stop(){

    }

    public int getDuracion(){
        return duracionSegundos;
    }


    //Implementacion interfaz Descargable:

    public boolean descargar() throws LimiteDescargasException{

    }

    public boolean eliminarDescarga(){

    }

    public int espacioRequerido(){

    }



    //Metodos propios

    public String obtenerLetra() throws LetraNoDisponibleException{
        return letra;
    }

    public boolean exExplicit(){

    }

    public void cambiarGenero(GeneroMusical nuevoGenero){

    }

    public void validarAudioURL() throws ArchivoAudioNoEncontradoException {

    }


    //gets sets

    public String  getLetra(){
        return letra;
    }

    public void setLetra(String letra){

    }

    public Artista getArtista(){
        return artista;
    }

    public void setArtista(Artista artista){

    }

    public Album getAlbum(){
        return album;
    }

    public void setAlbum(Album album){

    }

    public GeneroMusical getGenero(){
        return genero;
    }

    public void setGenero (GeneroMusical genero){

    }

    public String getAudioURL(){
        return audioURL;
    }

    public void setAudioURL (String audioURL){

    }

    public boolean isExplicit(){

    }

    public void setExplicit (boolean explicit){

    }

    public String getISRC(){
        return ISRC;
    }

    public boolean isReproduciendo(){

    }

    public boolean isPausado(){

    }

    public boolean isDescargado(){

    }

    public void setDescargado(boolean descargado){

    }


    @Override
    public String toString(){

    }




}
