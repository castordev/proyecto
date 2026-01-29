package modelo.plataforma;

import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Contenido;
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
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistsPublicas;
    private HashMap<String, Artista> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendadorIA;

    // constructor


    public Plataforma(String nombre, HashMap<String, Usuario> usuarios, HashMap<String, Usuario> usuariosPorEmail, ArrayList<Contenido> catalogo, ArrayList<Playlist> playlistsPublicas, HashMap<String, Artista> creadores, ArrayList<Album> albumes, ArrayList<Anuncio> anuncios, RecomendadorIA recomendadorIA) {
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.usuariosPorEmail = usuariosPorEmail;
        this.catalogo = catalogo;
        this.playlistsPublicas = playlistsPublicas;
        this.creadores = creadores;
        this.albumes = albumes;
        this.anuncios = anuncios;
        this.recomendadorIA = recomendadorIA;
    }


    //gets sets

    public static void setInstancia(Plataforma instancia) {
        Plataforma.instancia = instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public HashMap<String, Usuario> getUsuariosPorEmail() {
        return usuariosPorEmail;
    }

    public void setUsuariosPorEmail(HashMap<String, Usuario> usuariosPorEmail) {
        this.usuariosPorEmail = usuariosPorEmail;
    }

    public ArrayList<Contenido> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Contenido> catalogo) {
        this.catalogo = catalogo;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return playlistsPublicas;
    }

    public void setPlaylistsPublicas(ArrayList<Playlist> playlistsPublicas) {
        this.playlistsPublicas = playlistsPublicas;
    }

    public HashMap<String, Artista> getCreadores() {
        return creadores;
    }

    public void setCreadores(HashMap<String, Artista> creadores) {
        this.creadores = creadores;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(ArrayList<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public RecomendadorIA getRecomendadorIA() {
        return recomendadorIA;
    }

    public void setRecomendadorIA(RecomendadorIA recomendadorIA) {
        this.recomendadorIA = recomendadorIA;
    }



    // metodos del patron singleton
    public Plataforma getInstancia (){

    }

    public Plataforma getInstancia (String nombre){

    }

    public void reiniciarInstancia (){

    }



    // metodos de registro de usuarios

    public UsuarioPremium registrarUsuarioPremium (String nombre, String email, String password){

    }

    public UsuarioGratuito registrarUsuarioGratuito ( String nombre, String email, String password){

    }

    public void getUsuariosGratuitos (){

    }

    public void getUsuariosPremium (){

    }

    public void getTodosLosUsuarios (){

    }



    //metodos de gestion de artistas

    public Artista registrarArtista (String nombre, String nombreReal, String paisOrigen, Boolean verificado){

    }

    public Artista buscarArtista (String nombre){

    }

    public Artista getArtistas(){

    }

    public Artista getArtistasVerificados(){

    }

    public Artista getArtistaNoVerificados(){

    }



    // metodos de gestion de contenido

    public Album crearAlbum (Artista artista, String titulo, Date fecha){

    }

    public void crearCancion (String titulo, int duracion, Artista artista, GeneroMusical genero){

    }

    public void crearCancionEnAlbum (String titulo, int duracion, Artista artista, Album album, GeneroMusical genero){

    }

    public Creador registrarCreador (String nombreCanal, String nombre, String descripcion){

    }

    public void cearPodcast (String titulo, int duracion, Creador creador, int temporada, int episodio, CategoriaPodcast categoria){

    }

    public void agregarContenidoCatalogo (Contenido contenido){

    }

    public void getCatalogo(){

    }

    public void getCanciones(){

    }

    public void getPodcasts(){

    }

    public void getAlbumes (){

    }

    public void getTodosLosCreadores (){

    }



    //metodos de busqueda

    public ArrayList<Contenido> buscarContenido (String termino){

    }

    public ArrayList<Contenido> buscarPorGenero (GeneroMusical genero){

    }

    public ArrayList<Contenido> buscarPorCategoria (CategoriaPodcast categoria){

    }

    public ArrayList<Contenido> obtenerTopContenido (int cantidad){

    }



    //metodos de playlists

    public void crearPlaylistPublica (String nombre, Usuario creador){

    }

    public void getPlaylistsPublicas (){

    }



    //metodos de anuncios

    public void agregarAnuncio (Anuncio anuncio){

    }

    public void obtenerAnuncioAleatorio(){

    }

    public void getAnuncios(){

    }



    //metodos de estadisticas y utilidades

    public String obtenerEstadisticasGenerales(){

    }

    public void getRecomendador (){

    }

    public void getNombre(){

    }


}
