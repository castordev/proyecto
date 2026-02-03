package modelo.usuarios;


import enums.TipoSuscripcion;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoExcepcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Playlist;

import java.util.ArrayList;
import java.util.Date;

public abstract class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String password;
    private TipoSuscripcion suscripcion;
    private ArrayList<Playlist> misPlaylists;
    private ArrayList<Contenido> historial;
    private Date fechaRegistro;
    private ArrayList<Playlist> playlistsSeguidas;
    private ArrayList<Contenido> contenidosLiked;


    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, AnuncioRequeridoExcepcion {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
    }


    //reproduce contenido segun tipo de usuario
    public abstract void reproducir (Contenido contenido) throws  ContenidoNoDisponibleException, LimiteEpisodiosException, AnuncioRequeridoExcepcion;

    public Playlist crearPlaylists( String nombrePlaylist){

    }

    public void seguirPlaylist(Playlist playlist){

    }

    public void dejarDeSeguirPlaylist( Playlist playlist){

    }

    public void darLike (Contenido contenido){

    }

    public void quitarLike (Contenido contenido){

    }

    public boolean validarEmail() throws EmailInvalidoException{

    }

    public boolean validarPassword() throws PasswordDebilException{

    }

    public void agregarAlHistorial( Contenido contenido){

    }

    public void limpiarHistorial(){

    }

    public boolean esPremium(){

    }

    public String getId(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidoException {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordDebilException {
        this.password = password;
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylists(){

    }

    public ArrayList<Contenido> getHistorial(){

    }

    public Date getFechaRegistro(){

    }

    public ArrayList<Playlist> getPlaylistsSeguidas(){

    }

    public ArrayList<Contenido> getContenidosLiked(){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
