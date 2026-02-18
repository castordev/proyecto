package modelo.artistas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;
import java.util.Objects;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;


public class Creador {

    private static final int MAX_EPISODIOS = 500;

    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String, String> redesSociales;
    private ArrayList<CategoriaPodcast> categoriasPrincipales;

    public Creador(String nombreCanal, String nombre) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.episodios = new ArrayList<>();
        this.suscriptores = 0;
        this.descripcion = "";
        this.redesSociales = new HashMap<>();
        this.categoriasPrincipales = new ArrayList<>();
    }

    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.episodios = new ArrayList<>();
        this.suscriptores = 0;
        this.descripcion = descripcion;
        this.redesSociales = new HashMap<>();
        this.categoriasPrincipales = new ArrayList<>();
    }

    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if (episodios.size() >= MAX_EPISODIOS) {
            throw new LimiteEpisodiosException("Se ha alcanzado el límite de  episodios");
        }
        if (episodio != null && !episodios.contains(episodio)) {
            episodios.add(episodio);
            episodio.setCreador(this);
        }
    }

    public EstadisticasCreador obtenerEstadisticas() {
        return new EstadisticasCreador(this);
    }

    public void agregarRedSocial(String usuario, String red) {
        if (red != null && usuario != null) {
            redesSociales.put(usuario.toLowerCase(), red.toLowerCase());
        }
    }

    public double calcularPromedioReproducciones() {
        if (episodios.isEmpty()) {
            return 0.0;
        }
        int total = getTotalReproducciones();
        return (double) total / episodios.size();
    }

    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException {
        boolean encontrado = false;
        for (int i = 0; i < episodios.size(); i++) {
            if (episodios.get(i).getId().equals(idEpisodio)) {
                episodios.remove(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new EpisodioNoEncontradoException("No se encontró el episodio");
        }
    }

    public int getTotalReproducciones() {
        int total = 0;
        for (Podcast podcast : episodios) {
            total += podcast.getReproducciones();
        }
        return total;
    }

    public void incrementarSuscriptores() {
        this.suscriptores++;
    }

    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad) {
        ArrayList<Podcast> copia = new ArrayList<>(episodios);
        copia.sort(Comparator.comparingInt(Podcast::getReproducciones).reversed());
        return new ArrayList<>(copia.subList(0, Math.min(cantidad, copia.size())));
    }

    public int getUltimaTemporada() {
        int maxTemporada = 0;
        for (Podcast podcast : episodios) {
            if (podcast.getTemporada() > maxTemporada) {
                maxTemporada = podcast.getTemporada();
            }
        }
        return maxTemporada;
    }

    public String getId() {
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
        return new ArrayList<>(episodios);
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
        return new HashMap<>(redesSociales);
    }

    public ArrayList<CategoriaPodcast> getCategoriasPrincipales() {
        return new ArrayList<>(categoriasPrincipales);
    }

    public int getNumEpisodios() {
        return episodios.size();
    }

    @Override
    public String toString() {
        return nombreCanal + " - " + nombre + " (" + suscriptores + " suscriptores)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creador creador = (Creador) obj;
        return Objects.equals(id, creador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
