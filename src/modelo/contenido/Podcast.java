package modelo.contenido;

import enums.CategoriaPodcast;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.EpisodioNoEncontradoException;
import excepciones.contenido.TranscripcionNoDisponibleException;
import modelo.artistas.Creador;

import java.util.ArrayList;
import java.util.Date;

public class Podcast extends  Contenido{

    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private ArrayList<String> invitados;
    private String transcipcion;
    private boolean reproduccion;
    private boolean pausado;
    private boolean descargado;


    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
    }

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria, String descripcion) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    @Override
    public void reproducir() throws ContenidoNoDisponibleException{

    }


    //implementacion interfaz Reproducible:

    public void play(){

    }

    public void pause(){

    }

    public void stop(){

    }

    public int getDuracion(){
        return duracionSegundos;
    }


    //metodos propios

    public String obtenerDescripcion(){
        return descripcion;
    }

    public void agregarInvitado(String nombre){

    }

    public boolean esTemporadaNueva(){

    }

    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException{

    }

    public void validarEpisodio() throws EpisodioNoEncontradoException{

    }



    //gets sets

    public Creador getCreador(){
        return creador;
    }

    public void setCreador (Creador creador){

    }

    public int getNumeroEpisodio(){
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio){

    }

    public int getTemporada(){
        return temporada;
    }

    public void setTemporada(int temporada){

    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){

    }

    public CategoriaPodcast getCategoria(){
        return categoria;
    }

    public void setCategoria(String descripcion){

    }

    public ArrayList<String> getInvitados(){
        return invitados;
    }

    public String getTranscipcion() {
        return transcipcion;
    }

    public void setTranscipcion(String transcipcion){

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
