package modelo.artistas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;

import enums.GeneroMusical;
import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;

public class Album {

    private static final int MAX_CANCIONES = 20;

    private String id;
    private String titulo;
    private Artista artista;
    private Date fechaLanzamiento;
    private ArrayList<Cancion> canciones;
    private String portadaURL;
    private String discografica;
    private String tipoAlbum;


    public Album(String titulo, Artista artista, Date fechaLanzamiento) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.discografica = "";
        this.tipoAlbum = "Album";
    }

    public Album(String titulo, Artista artista, Date fechaLanzamiento,
                 String discografica, String tipoAlbum) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero)
            throws AlbumCompletoException, DuracionInvalidaException {
        if (canciones.size() >= MAX_CANCIONES) {
            throw new AlbumCompletoException("El álbum '" + this.titulo + "' está completo");
        }
        Cancion cancion = new Cancion(titulo, duracionSegundos, artista, genero);
        cancion.setAlbum(this);
        canciones.add(cancion);
        if (artista != null) {
            artista.publicarCancion(cancion);
        }
        return cancion;
    }

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero,
                                String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException {
        if (canciones.size() >= MAX_CANCIONES) {
            throw new AlbumCompletoException("El álbum '" + this.titulo + "' está completo");
        }
        Cancion cancion = new Cancion(titulo, duracionSegundos, artista, genero, letra, explicit);
        cancion.setAlbum(this);
        canciones.add(cancion);
        if (artista != null) {
            artista.publicarCancion(cancion);
        }
        return cancion;
    }

    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("No se encontró canción");
        }
        canciones.remove(posicion - 1);
    }

    public void eliminarCancion(Cancion cancion) throws CancionNoEncontradaException {
        if (!canciones.remove(cancion)) {
            throw new CancionNoEncontradaException("No se encontró la canción '" + cancion.getTitulo() + "'");
        }
    }

    public int getDuracionTotal() {
        int total = 0;
        for (Cancion cancion : canciones) {
            total += cancion.getDuracionSegundos();
        }
        return total;
    }

    public String getDuracionTotalFormateada() {
        int totalSegundos = getDuracionTotal();
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    public int getNumCanciones() {
        return canciones.size();
    }

    public void ordenarPorPopularidad() {
        canciones.sort(Comparator.comparingInt(Cancion::getReproducciones).reversed());
    }

    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("No se encontró canción en la posición " + posicion);
        }
        return canciones.get(posicion - 1);
    }

    public int getTotalReproducciones() {
        int total = 0;
        for (Cancion cancion : canciones) {
            total += cancion.getReproducciones();
        }
        return total;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public ArrayList<Cancion> getCanciones() {
        return new ArrayList<>(canciones);
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    public int getMaxCanciones() {
        return MAX_CANCIONES;
    }

    @Override
    public String toString() {
        return titulo + " - " + (artista != null ? artista.getNombreArtistico() : "Desconocido") +
                " (" + canciones.size() + " canciones)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return Objects.equals(id, album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
