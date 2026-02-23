package modelo.contenido;

import java.util.UUID;

import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Album;
import modelo.artistas.Artista;


public class Cancion extends Contenido implements Reproducible, Descargable {

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
        this.ISRC = generarISRC();
        this.letra = null;
        this.explicit = false;
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
        this.album = null;
    }


    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;
        this.ISRC = generarISRC();
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
        this.album = null;
    }


    private String generarISRC() {
        return "ES-SW-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!disponible) {
            throw new ContenidoNoDisponibleException("La canción '" + titulo + "' no está disponible");
        }
        play();
        aumentarReproducciones();
    }

    // Implementación de Reproducible

    @Override
    public void play() {
        reproduciendo = true;
        pausado = false;
        System.out.println("♪ Reproduciendo: " + titulo + " - " +
                (artista != null ? artista.getNombreArtistico() : "Artista desconocido"));
    }

    @Override
    public void pause() {
        if (reproduciendo) {
            reproduciendo = false;
            pausado = true;
            System.out.println("⏸ Pausado: " + titulo);
        }
    }

    @Override
    public void stop() {
        reproduciendo = false;
        pausado = false;
        System.out.println("⏹ Detenido: " + titulo);
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getDuracion() {
        return duracionSegundos;
    }

    // Implementación de Descargable

    @Override
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("La canción '" + titulo + "' ya está descargada");
        }
        descargado = true;
        System.out.println("⬇ Descargando: " + titulo);
        return true;
    }

    @Override
    public boolean eliminarDescarga() {
        if (descargado) {
            descargado = false;
            System.out.println("🗑 Descarga eliminada: " + titulo);
            return true;
        }
        return false;
    }

    @Override
    public int espacioRequerido() {
        // Aproximación: 1MB por minuto de audio
        return Math.max(1, duracionSegundos / 60);
    }

    // Métodos propios

    public String obtenerLetra() throws LetraNoDisponibleException {
        if (letra == null || letra.trim().isEmpty()) {
            throw new LetraNoDisponibleException("La letra de '" + titulo + "' no está disponible");
        }
        return letra;
    }


    public boolean esExplicit() {
        return explicit;
    }


    public void cambiarGenero(GeneroMusical nuevoGenero) {
        this.genero = nuevoGenero;
    }


    public void validarAudioURL() throws ArchivoAudioNoEncontradoException {
        if (audioURL == null || audioURL.trim().isEmpty()) {
            throw new ArchivoAudioNoEncontradoException("No se encontró el archivo de audio para '" + titulo + "'");
        }
    }

    // Getters y Setters

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
        return titulo + " - " + (artista != null ? artista.getNombreArtistico() : "Desconocido") +
                " [" + getDuracionFormateada() + "]" + (explicit ? " (E)" : "");
    }
}
