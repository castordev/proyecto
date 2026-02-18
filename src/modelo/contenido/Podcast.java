package modelo.contenido;

import java.util.ArrayList;

import enums.CategoriaPodcast;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.EpisodioNoEncontradoException;
import excepciones.contenido.TranscripcionNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Creador;

public class Podcast extends Contenido implements Reproducible, Descargable {

    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private ArrayList<String> invitados;
    private String transcripcion;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.descripcion = "";
        this.invitados = new ArrayList<>();
        this.transcripcion = null;
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
    }

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria, String descripcion) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.invitados = new ArrayList<>();
        this.transcripcion = null;
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
    }

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!disponible) {
            throw new ContenidoNoDisponibleException("El podcast no está disponible");
        }
        play();
        aumentarReproducciones();
    }

    @Override
    public void play() {
        reproduciendo = true;
        pausado = false;
        System.out.println("Reproduciendo: " + titulo + " - T" + temporada + "E" + numeroEpisodio);
    }

    @Override
    public void pause() {
        if (reproduciendo) {
            reproduciendo = false;
            pausado = true;
            System.out.println("Pausado");
        }
    }

    @Override
    public void stop() {
        reproduciendo = false;
        pausado = false;
        System.out.println("Detenido");
    }

    @Override
    public int getDuration() {
        return 0;
    }


    @Override
    public int getDuracion() {
        return duracionSegundos;
    }

    @Override
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("El podcast ya está descargado");
        }
        descargado = true;
        System.out.println("Descargando");
        return true;
    }

    @Override
    public boolean eliminarDescarga() {
        if (descargado) {
            descargado = false;
            System.out.println("Descarga eliminada");
            return true;
        }
        return false;
    }

    @Override
    public int espacioRequerido() {
        return Math.max(1, duracionSegundos / 60);
    }

    public String obtenerDescripcion() {
        return "T" + temporada + "E" + numeroEpisodio + " - " + titulo + ": " + descripcion;
    }

    public void agregarInvitado(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty() && !invitados.contains(nombre.trim())) {
            invitados.add(nombre.trim());
        }
    }

    public boolean esTemporadaNueva() {
        return temporada >= 2;
    }

    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException {
        if (transcripcion == null || transcripcion.trim().isEmpty()) {
            throw new TranscripcionNoDisponibleException("La transcripción no está disponible");
        }
        return transcripcion;
    }

    public void validarEpisodio() throws EpisodioNoEncontradoException {
        if (numeroEpisodio <= 0 || temporada <= 0) {
            throw new EpisodioNoEncontradoException("Episodio o temporada inválidos");
        }
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
        return new ArrayList<>(invitados);
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public boolean isPausado() {
        return pausado;
    }

    public boolean isDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    @Override
    public String toString() {
        return titulo + " - T" + temporada + "E" + numeroEpisodio + " [" + getDuracionFormateada() + "] - " + (creador != null ? creador.getNombreCanal() : "Canal desconocido");
    }
}
