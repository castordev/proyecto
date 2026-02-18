package modelo.plataforma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;

import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

public class Playlist {

    private static final int MAX_CONTENIDOS_DEFAULT = 500;

    private String id;
    private String nombre;
    private Usuario creador;
    private ArrayList<Contenido> contenidos;
    private boolean esPublica;
    private int seguidores;
    private String descripcion;
    private String portadaURL;
    private Date fechaCreacion;
    private int maxContenidos;

    public Playlist(String nombre, Usuario creador) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.creador = creador;
        this.contenidos = new ArrayList<>();
        this.esPublica = false;
        this.seguidores = 0;
        this.descripcion = "";
        this.portadaURL = "https://soundwave.com/playlists/" + id + ".jpg";
        this.fechaCreacion = new Date();
        this.maxContenidos = MAX_CONTENIDOS_DEFAULT;
    }

    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.creador = creador;
        this.contenidos = new ArrayList<>();
        this.esPublica = esPublica;
        this.seguidores = 0;
        this.descripcion = descripcion;
        this.portadaURL = "https://soundwave.com/playlists/" + id + ".jpg";
        this.fechaCreacion = new Date();
        this.maxContenidos = MAX_CONTENIDOS_DEFAULT;
    }

    public void agregarContenido(Contenido contenido) throws PlaylistLlenaException, ContenidoDuplicadoException {
        if (contenidos.size() >= maxContenidos) {
            throw new PlaylistLlenaException("La playlist '" + nombre + "' está llena");
        }
        if (contenidos.contains(contenido)) {
            throw new ContenidoDuplicadoException("El contenido ya existe en la playlist");
        }
        contenidos.add(contenido);
    }

    public boolean eliminarContenido(String idContenido) {
        for (int i = 0; i < contenidos.size(); i++) {
            if (contenidos.get(i).getId().equals(idContenido)) {
                contenidos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarContenido(Contenido contenido) {
        return contenidos.remove(contenido);
    }

    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException {
        if (contenidos.isEmpty()) {
            throw new PlaylistVaciaException("La playlist está vacía");
        }

        switch (criterio) {
            case POPULARIDAD:
                contenidos.sort(Comparator.comparingInt(Contenido::getReproducciones).reversed());
                break;
            case DURACION:
                contenidos.sort(Comparator.comparingInt(Contenido::getDuracionSegundos));
                break;
            case ALFABETICO:
                contenidos.sort(Comparator.comparing(Contenido::getTitulo));
                break;
            case ARTISTA:
                contenidos.sort((c1, c2) -> {
                    String artista1 = (c1 instanceof Cancion) ?
                            ((Cancion) c1).getArtista().getNombreArtistico() : "";
                    String artista2 = (c2 instanceof Cancion) ?
                            ((Cancion) c2).getArtista().getNombreArtistico() : "";
                    return artista1.compareTo(artista2);
                });
                break;
            case ALEATORIO:
                Collections.shuffle(contenidos);
                break;
            case FECHA_AGREGADO:
            default:
                // Ya está ordenado por fecha de agregado
                break;
        }
    }

    public int getDuracionTotal() {
        int total = 0;
        for (Contenido contenido : contenidos) {
            total += contenido.getDuracionSegundos();
        }
        return total;
    }

    public String getDuracionTotalFormateada() {
        int totalSegundos = getDuracionTotal();
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        if (horas > 0) {
            return String.format("%d:%02d:%02d", horas, minutos, segundos);
        } else {
            return String.format("%d:%02d", minutos, segundos);
        }
    }

    public void shuffle() {
        Collections.shuffle(contenidos);
    }

    public ArrayList<Contenido> buscarContenido(String termino) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        String terminoLower = termino.toLowerCase();
        for (Contenido contenido : contenidos) {
            if (contenido.getTitulo().toLowerCase().contains(terminoLower)) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }

    public void hacerPublica() {
        this.esPublica = true;
    }

    public void hacerPrivada() {
        this.esPublica = false;
    }

    public void incrementarSeguidores() {
        this.seguidores++;
    }

    public void decrementarSeguidores() {
        if (seguidores > 0) {
            seguidores--;
        }
    }

    public int getNumContenidos() {
        return contenidos.size();
    }

    public boolean estaVacia() {
        return contenidos.isEmpty();
    }

    public Contenido getContenido(int posicion) {
        if (posicion < 0 || posicion >= contenidos.size()) {
            return null;
        }
        return contenidos.get(posicion);
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getCreador() {
        return creador;
    }

    public ArrayList<Contenido> getContenidos() {
        return new ArrayList<>(contenidos);
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

    @Override
    public String toString() {
        return nombre + " (" + contenidos.size() + " canciones) - " + seguidores + " seguidores";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Playlist playlist = (Playlist) obj;
        return Objects.equals(id, playlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
