package modelo.plataforma;

import enums.CategoriaPodcast;
import enums.GeneroMusical;
import enums.TipoAnuncio;
import enums.TipoSuscripcion;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.descarga.LimiteDescargasException;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Plataforma {

    private static Plataforma instancia;
    private String nombre;
    private HashMap<String,Usuario> usuarios;
    private HashMap<String,Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistsPublicas;
    private HashMap<String,Artista> artistas;
    private HashMap<String,Creador> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;


    // singleton
    private Plataforma(String nombre) {
        this.nombre = nombre;
    }

    //devuelve/crea instancia unica
    public static synchronized Plataforma getInstancia (String nombre){

    }

    //devuelve instancia con nombre por defecto
    public static synchronized Plataforma getInstancia(){

    }

    public static synchronized void reiniciarInstancia(){

    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{

    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{

    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{

    }

    public ArrayList<UsuarioPremium> getUsuariosPremium(){

    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos(){

    }

    public ArrayList<Usuario> getTodosLosUsuarios(){

    }

    public Usuario buscarUsuarioPorEmail(String email){

    }

    public Artista registraArtista (String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado){

    }

    public void registrarArtista(Artista artista){

    }

    public ArrayList<Artista> getArtistasVerificados(){

    }

    public ArrayList<Artista> getArtistasNoVerificados(){

    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException{

    }

    public Album crearAlbum(Artista artista, String titulo, Date fecha) throws ArtistaNoEncontradoException, AlbumYaExisteException{

    }

    public ArrayList<Album> getAlbumes(){

    }

    // crear cancion independiente
    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero) throws DuracionInvalidaException{

    }

    public Cancion crearCancionEnAlbum(String titulo, int duracion, Artista artista, GeneroMusical genero, Album album) throws DuracionInvalidaException, LimiteDescargasException{

    }

    public void agregarContenidoCatalogo(Contenido contenido){

    }

    public ArrayList<Cancion> getCanciones(){

    }

    public Creador registrarCreador(String nombreCanal, String nombrem, String descripcion){

    }

    public void registrarCreador(Creador creador){

    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador, int numEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException, LimiteEpisodiosException{

    }

    public ArrayList<Podcast> getPodcasts(){

    }

    public ArrayList<Creador> getTodosLosCreadores(){

    }

    public Playlist crearPlaylistPublica (String nombre, Usuario creador){

    }

    public ArrayList<Playlist> getPlaylistsPublicas(){

    }

    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException{

    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws  ContenidoNoEncontradoException{

    }

    public ArrayList<Podcast> buscarPorCategoria (CategoriaPodcast categoria) throws ContenidoNoEncontradoException{

    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad){

    }

    public Anuncio obtenerAnuncioAleatorio(){

    }

    public void incrementarAnunciosReproducidos(){

    }

    public String obtenerEstadisticasGenerales(){

    }

    public String getNombre(){

    }

    public ArrayList<Contenido> getCatalogo(){

    }

    public HashMap<String, Artista> getArtistas() {
        return artistas;
    }

    public HashMap<String, Creador> getCreadores() {
        return creadores;
    }

    public ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public RecomendadorIA getRecomendador() {
        return recomendador;
    }

    public int getTtotalUsuarios(){

    }

    public int getTotalContenido(){

    }

    public int getTotalAnunciosReproducidos() {
        return totalAnunciosReproducidos;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
