package modelo.contenido;

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

    public Podcast(String id, String titulo, int reproducciones, int likes, int duracionSegundos, ArrayList<String> tags, boolean disponible, Date fechaPublicacion, Creador creador, int numeroEpisodio, int temporada, String descripcion, CategoriaPodcast categoria, ArrayList<String> invitados, String transcipcion) {
        super(id, titulo, reproducciones, likes, duracionSegundos, tags, disponible, fechaPublicacion);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.invitados = invitados;
        this.transcipcion = transcipcion;
    }

    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPodcast getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getInvitados() {
        return invitados;
    }

    public void setInvitados(ArrayList<String> invitados) {
        this.invitados = invitados;
    }

    public String getTranscipcion() {
        return transcipcion;
    }

    public void setTranscipcion(String transcipcion) {
        this.transcipcion = transcipcion;
    }

    @Override
    public void  reproducir(){

    }

    public String obtenerDescripcion (){

    }


    public void agregarInvitado (String nombre){

    }

    public boolean esTemporadaNueva (){

    }

    public void play (){

    }

    public void pause (){

    }

    public void stop (){

    }

    public boolean descargar (){

    }

    public boolean eliminarDescarga (){

    }


}
