package modelo.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import enums.CategoriaPodcast;
import enums.GeneroMusical;
import enums.TipoAnuncio;
import enums.TipoSuscripcion;
import excepciones.artista.AlbumCompletoException;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.plataforma.ArtistaNoEncontradoException;
import excepciones.plataforma.ContenidoNoEncontradoException;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;
import utilidades.RecomendadorIA;

public class Plataforma {

    private static Plataforma instancia;

    private String nombre;
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistsPublicas;
    private HashMap<String, Artista> artistas;
    private HashMap<String, Creador> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;

    private Plataforma(String nombre) {
        this.nombre = nombre;
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.catalogo = new ArrayList<>();
        this.playlistsPublicas = new ArrayList<>();
        this.artistas = new HashMap<>();
        this.creadores = new HashMap<>();
        this.albumes = new ArrayList<>();
        this.anuncios = new ArrayList<>();
        this.recomendador = new RecomendadorIA();
        this.totalAnunciosReproducidos = 0;

        // Inicializar anuncios por defecto
        anuncios.add(new Anuncio("Spotify", TipoAnuncio.AUDIO, 1000.0));
        anuncios.add(new Anuncio("Nike", TipoAnuncio.VIDEO, 2000.0));
        anuncios.add(new Anuncio("Coca-Cola", TipoAnuncio.BANNER, 500.0));
    }

    public static synchronized Plataforma getInstancia(String nombre) {
        if (instancia == null) {
            instancia = new Plataforma(nombre);
        }
        return instancia;
    }

    public static synchronized Plataforma getInstancia() {
        return getInstancia("SoundWave");
    }

    public static synchronized void reiniciarInstancia() {
        instancia = null;
    }

    // Gestión de usuarios

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password,
                                                  TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        if (usuariosPorEmail.containsKey(email.toLowerCase())) {
            throw new UsuarioYaExisteException("Ya existe un usuario con el email: " + email);
        }
        UsuarioPremium usuario = new UsuarioPremium(nombre, email, password, tipo);
        usuarios.put(usuario.getId(), usuario);
        usuariosPorEmail.put(email.toLowerCase(), usuario);
        return usuario;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password)
            throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return registrarUsuarioPremium(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password)
            throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        if (usuariosPorEmail.containsKey(email.toLowerCase())) {
            throw new UsuarioYaExisteException("Ya existe un usuario con el email: " + email);
        }
        UsuarioGratuito usuario = new UsuarioGratuito(nombre, email, password);
        usuarios.put(usuario.getId(), usuario);
        usuariosPorEmail.put(email.toLowerCase(), usuario);
        return usuario;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium() {
        ArrayList<UsuarioPremium> premium = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof UsuarioPremium) {
                premium.add((UsuarioPremium) usuario);
            }
        }
        return premium;
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        ArrayList<UsuarioGratuito> gratuitos = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof UsuarioGratuito) {
                gratuitos.add((UsuarioGratuito) usuario);
            }
        }
        return gratuitos;
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuariosPorEmail.get(email.toLowerCase());
    }

    // Gestión de artistas

    public Artista registrarArtista(String nombreArtistico, String nombreReal,
                                    String paisOrigen, boolean verificado) {
        Artista artista = new Artista(nombreArtistico, nombreReal, paisOrigen, verificado, "");
        artistas.put(artista.getId(), artista);
        return artista;
    }

    public void registrarArtista(Artista artista) {
        if (artista != null) {
            artistas.put(artista.getId(), artista);
        }
    }

    public ArrayList<Artista> getArtistasVerificados() {
        ArrayList<Artista> verificados = new ArrayList<>();
        for (Artista artista : artistas.values()) {
            if (artista.isVerificado()) {
                verificados.add(artista);
            }
        }
        return verificados;
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        ArrayList<Artista> noVerificados = new ArrayList<>();
        for (Artista artista : artistas.values()) {
            if (!artista.isVerificado()) {
                noVerificados.add(artista);
            }
        }
        return noVerificados;
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException {
        for (Artista artista : artistas.values()) {
            if (artista.getNombreArtistico().equalsIgnoreCase(nombre)) {
                return artista;
            }
        }
        throw new ArtistaNoEncontradoException("No se encontró el artista: " + nombre);
    }

    // Gestión de álbumes

    public Album crearAlbum(Artista artista, String titulo, Date fecha)
            throws ArtistaNoVerificadoException, AlbumYaExisteException {
        Album album = artista.crearAlbum(titulo, fecha);
        albumes.add(album);
        return album;
    }

    public ArrayList<Album> getAlbumes() {
        return new ArrayList<>(albumes);
    }

    // Gestión de canciones

    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero)
            throws DuracionInvalidaException {
        Cancion cancion = new Cancion(titulo, duracion, artista, genero);
        catalogo.add(cancion);
        if (artista != null) {
            artista.publicarCancion(cancion);
        }
        return cancion;
    }

    public Cancion crearCancionEnAlbum(String titulo, int duracion, Artista artista,
                                       GeneroMusical genero, Album album) throws DuracionInvalidaException, AlbumCompletoException {
        Cancion cancion = album.crearCancion(titulo, duracion, genero);
        catalogo.add(cancion);
        return cancion;
    }

    public void agregarContenidoCatalogo(Contenido contenido) {
        if (contenido != null && !catalogo.contains(contenido)) {
            catalogo.add(contenido);
        }
    }

    public ArrayList<Cancion> getCanciones() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido instanceof Cancion) {
                canciones.add((Cancion) contenido);
            }
        }
        return canciones;
    }

    // Gestión de creadores/podcasts

    public Creador registrarCreador(String nombreCanal, String nombre, String descripcion) {
        Creador creador = new Creador(nombreCanal, nombre, descripcion);
        creadores.put(creador.getId(), creador);
        return creador;
    }

    public void registrarCreador(Creador creador) {
        if (creador != null) {
            creadores.put(creador.getId(), creador);
        }
    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador,
                                int numEpisodio, int temporada, CategoriaPodcast categoria)
            throws DuracionInvalidaException, LimiteEpisodiosException {
        Podcast podcast = new Podcast(titulo, duracion, creador, numEpisodio, temporada, categoria);
        creador.publicarPodcast(podcast);
        catalogo.add(podcast);
        return podcast;
    }

    public ArrayList<Podcast> getPodcasts() {
        ArrayList<Podcast> podcasts = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido instanceof Podcast) {
                podcasts.add((Podcast) contenido);
            }
        }
        return podcasts;
    }

    public ArrayList<Creador> getTodosLosCreadores() {
        return new ArrayList<>(creadores.values());
    }

    // Gestión de playlists públicas

    public Playlist crearPlaylistPublica(String nombre, Usuario creador) {
        Playlist playlist = new Playlist(nombre, creador, true, "");
        playlistsPublicas.add(playlist);
        return playlist;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return new ArrayList<>(playlistsPublicas);
    }

    // Búsquedas

    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException {
        ArrayList<Contenido> resultados = new ArrayList<>();
        String terminoLower = termino.toLowerCase();
        for (Contenido contenido : catalogo) {
            if (contenido.getTitulo().toLowerCase().contains(terminoLower)) {
                resultados.add(contenido);
            }
        }
        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontró contenido con el término: " + termino);
        }
        return resultados;
    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws ContenidoNoEncontradoException {
        ArrayList<Cancion> resultados = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido instanceof Cancion) {
                Cancion cancion = (Cancion) contenido;
                if (cancion.getGenero() == genero) {
                    resultados.add(cancion);
                }
            }
        }
        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontraron canciones del género: " + genero);
        }
        return resultados;
    }

    public ArrayList<Podcast> buscarPorCategoria(CategoriaPodcast categoria) throws ContenidoNoEncontradoException {
        ArrayList<Podcast> resultados = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido instanceof Podcast) {
                Podcast podcast = (Podcast) contenido;
                if (podcast.getCategoria() == categoria) {
                    resultados.add(podcast);
                }
            }
        }
        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontraron podcasts de la categoría: " + categoria);
        }
        return resultados;
    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad) {
        ArrayList<Contenido> copia = new ArrayList<>(catalogo);
        copia.sort(Comparator.comparingInt(Contenido::getReproducciones).reversed());
        return new ArrayList<>(copia.subList(0, Math.min(cantidad, copia.size())));
    }

    // Anuncios

    public Anuncio obtenerAnuncioAleatorio() {
        ArrayList<Anuncio> activos = new ArrayList<>();
        for (Anuncio anuncio : anuncios) {
            if (anuncio.puedeMostrarse()) {
                activos.add(anuncio);
            }
        }
        if (activos.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return activos.get(random.nextInt(activos.size()));
    }

    public void incrementarAnunciosReproducidos() {
        totalAnunciosReproducidos++;
    }

    // Estadísticas

    public String obtenerEstadisticasGenerales() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================\n");
        sb.append("ESTADÍSTICAS DE ").append(nombre.toUpperCase()).append("\n");
        sb.append("========================\n");

        // Usuarios
        int premiumCount = getUsuariosPremium().size();
        int gratuitoCount = getUsuariosGratuitos().size();
        double ingresos = 0;
        for (Usuario u : usuarios.values()) {
            ingresos += u.getSuscripcion().getPrecioMensual();
        }

        sb.append("Total Usuarios: ").append(usuarios.size())
                .append(" (").append(premiumCount).append(" Premium, ")
                .append(gratuitoCount).append(" Gratuitos)\n");

        // Contenido
        int canciones = getCanciones().size();
        int podcasts = getPodcasts().size();
        sb.append("Total Contenido: ").append(catalogo.size())
                .append(" (").append(canciones).append(" canciones, ")
                .append(podcasts).append(" podcasts)\n");

        // Artista más popular
        Artista artistaMasPopular = null;
        int maxRepros = 0;
        for (Artista a : artistas.values()) {
            int repros = a.getTotalReproducciones();
            if (repros > maxRepros) {
                maxRepros = repros;
                artistaMasPopular = a;
            }
        }
        if (artistaMasPopular != null) {
            sb.append("Artista más popular: ").append(artistaMasPopular.getNombreArtistico())
                    .append(" (").append(maxRepros).append(" reproducciones)\n");
        }

        // Creador más popular
        Creador creadorMasPopular = null;
        int maxSubs = 0;
        for (Creador c : creadores.values()) {
            if (c.getSuscriptores() > maxSubs) {
                maxSubs = c.getSuscriptores();
                creadorMasPopular = c;
            }
        }
        if (creadorMasPopular != null) {
            sb.append("Creador más popular: ").append(creadorMasPopular.getNombreCanal())
                    .append(" (").append(maxSubs).append(" suscriptores)\n");
        }

        // Género más popular
        HashMap<GeneroMusical, Integer> generoCount = new HashMap<>();
        for (Cancion c : getCanciones()) {
            GeneroMusical g = c.getGenero();
            generoCount.put(g, generoCount.getOrDefault(g, 0) + c.getReproducciones());
        }
        GeneroMusical generoTop = null;
        int maxGenero = 0;
        for (GeneroMusical g : generoCount.keySet()) {
            if (generoCount.get(g) > maxGenero) {
                maxGenero = generoCount.get(g);
                generoTop = g;
            }
        }
        if (generoTop != null) {
            sb.append("Género más escuchado: ").append(generoTop.getNombre()).append("\n");
        }

        // Categoría más popular
        HashMap<CategoriaPodcast, Integer> catCount = new HashMap<>();
        for (Podcast p : getPodcasts()) {
            CategoriaPodcast cat = p.getCategoria();
            catCount.put(cat, catCount.getOrDefault(cat, 0) + p.getReproducciones());
        }
        CategoriaPodcast catTop = null;
        int maxCat = 0;
        for (CategoriaPodcast cat : catCount.keySet()) {
            if (catCount.get(cat) > maxCat) {
                maxCat = catCount.get(cat);
                catTop = cat;
            }
        }
        if (catTop != null) {
            sb.append("Categoría podcast más popular: ").append(catTop.getNombre()).append("\n");
        }

        sb.append("Ingresos mensuales: $").append(String.format("%.2f", ingresos)).append("\n");
        sb.append("Total anuncios reproducidos: ").append(totalAnunciosReproducidos).append("\n");
        sb.append("========================\n");

        return sb.toString();
    }

    // Getters básicos

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Contenido> getCatalogo() {
        return new ArrayList<>(catalogo);
    }

    public HashMap<String, Artista> getArtistas() {
        return new HashMap<>(artistas);
    }

    public HashMap<String, Creador> getCreadores() {
        return new HashMap<>(creadores);
    }

    public ArrayList<Anuncio> getAnuncios() {
        return new ArrayList<>(anuncios);
    }

    public RecomendadorIA getRecomendador() {
        return recomendador;
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public int getTotalContenido() {
        return catalogo.size();
    }

    public int getTotalAnunciosReproducidos() {
        return totalAnunciosReproducidos;
    }

    @Override
    public String toString() {
        return nombre + " - " + getTotalUsuarios() + " usuarios, " + getTotalContenido() + " contenidos";
    }
}
