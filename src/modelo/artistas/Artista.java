package modelo.artistas;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;
import java.util.Comparator;

import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import modelo.contenido.Cancion;

public class Artista {

    private String id;
    private String nombreArtistico;
    private String nombreReal;
    private String paisOrigen;
    private ArrayList<Cancion> discografia;
    private ArrayList<Album> albumes;
    private int oyentesMensuales;
    private boolean verificado;
    private String biografia;

    public Artista(String nombreArtistico, String nombreReal, String paisOrigen) {
        this.id = UUID.randomUUID().toString();
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.discografia = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.oyentesMensuales = 0;
        this.verificado = false;
        this.biografia = "";
    }

    public Artista(String nombreArtistico, String nombreReal, String paisOrigen,
                   boolean verificado, String biografia) {
        this.id = UUID.randomUUID().toString();
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.discografia = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.oyentesMensuales = 0;
        this.verificado = verificado;
        this.biografia = biografia;
    }

    public void publicarCancion(Cancion cancion) {
        if (cancion != null && !discografia.contains(cancion)) {
            discografia.add(cancion);
            cancion.setArtista(this);
        }
    }

    public Album crearAlbum(String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if (!verificado) {
            throw new ArtistaNoVerificadoException("El artista '" + nombreArtistico + "' no está verificado");
        }
        for (Album album : albumes) {
            if (album.getTitulo().equalsIgnoreCase(titulo)) {
                throw new AlbumYaExisteException("Ya existe un álbum con el título '" + titulo + "'");
            }
        }
        Album nuevoAlbum = new Album(titulo, this, fecha);
        albumes.add(nuevoAlbum);
        return nuevoAlbum;
    }

    public ArrayList<Cancion> obtenerTopCanciones(int cantidad) {
        ArrayList<Cancion> copia = new ArrayList<>(discografia);
        copia.sort(Comparator.comparingInt(Cancion::getReproducciones).reversed());
        return new ArrayList<>(copia.subList(0, Math.min(cantidad, copia.size())));
    }

    public double calcularPromedioReproducciones() {
        if (discografia.isEmpty()) {
            return 0.0;
        }
        int total = getTotalReproducciones();
        return (double) total / discografia.size();
    }

    public boolean esVerificado() {
        return verificado;
    }

    public int getTotalReproducciones() {
        int total = 0;
        for (Cancion cancion : discografia) {
            total += cancion.getReproducciones();
        }
        return total;
    }

    public void verificar() {
        this.verificado = true;
    }

    public void incrementarOyentes() {
        this.oyentesMensuales++;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public ArrayList<Cancion> getDiscografia() {
        return new ArrayList<>(discografia);
    }

    public ArrayList<Album> getAlbumes() {
        return new ArrayList<>(albumes);
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return nombreArtistico + (verificado ? " ✓" : "") + " - " + oyentesMensuales + " oyentes mensuales";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artista artista = (Artista) obj;
        return Objects.equals(id, artista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
