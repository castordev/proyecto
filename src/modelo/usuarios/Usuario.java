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
import java.util.Objects;

public abstract class Usuario {

    protected String id;
    protected String nombre;
    protected String email;
    protected String password;
    protected TipoSuscripcion suscripcion;
    protected ArrayList<Playlist> misPlaylists;
    protected ArrayList<Contenido> historial;
    protected Date fechaRegistro;
    protected ArrayList<Playlist> playlistsSeguidas;
    protected ArrayList<Contenido> contenidosLiked;


    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, AnuncioRequeridoExcepcion {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
    }


    //reproduce contenido segun tipo de usuario
    public abstract void reproducir (Contenido contenido) throws  ContenidoNoDisponibleException, LimiteEpisodiosException, AnuncioRequeridoExcepcion;

    public Playlist crearPlaylists( String nombrePlaylist){
        Playlist playlist = new Playlist(nombrePlaylist, this);
        misPlaylists.add(playlist);
        return playlist;
    }


    public void seguirPlaylist(Playlist playlist) {
        if (playlist == null || !playlist.isEsPublica() ) return;

        // si en playlistSeguidas no esta playlist, la agregamos y llamamos al metodo incrementarSeguidores
        if (!playlistsSeguidas.contains(playlist)) {
            playlistsSeguidas.add(playlist);
            playlist.incrementarSeguidores();
        }
    }


    public void dejarDeSeguirPlaylist( Playlist playlist){
        if(playlist == null) return;

        if(playlistsSeguidas.contains(playlist)){
            playlistsSeguidas.remove(playlist);
            playlist.decrementarSeguidores();
        }
    }

    public void darLike (Contenido contenido){
        if(contenido == null) return;

        // si al contenido aun no se le ha dado like contenidosLiked.add(contenido)
        if(!contenidosLiked.contains(contenido)){
            contenidosLiked.add(contenido);
        }
    }


    public void quitarLike (Contenido contenido){
        if(contenido == null) return;

        //remove busca el objeto y si esta lo elimina y si no, no hace nada, por eso no hace falta poner
        // if ( contenidosLiked.contains(contenido)
        contenidosLiked.remove(contenido);
    }

    public boolean validarEmail() throws EmailInvalidoException{
        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            throw new  EmailInvalidoException ("Email no valido");
        }
        return true;
    }

    public boolean validarPassword() throws PasswordDebilException{
        if(password == null || password.length() < 8){
            throw new PasswordDebilException("contraseña debe tener al menos 8 caracteres");
        }
        return true;
    }

    public void agregarAlHistorial( Contenido contenido){
        if(contenido != null){
            historial.remove(contenido); //si ya existe lo eliminamos para ponerlo al principio

            //si el historial es mas grande que 100, borramos el ultimo (index = 0)
            if(historial.size() >= 100){
                historial.remove(0);
            }
            historial.add(contenido);
        }
    }

    public void limpiarHistorial(){
        historial.clear();
    }

    public boolean esPremium(){
        return suscripcion != TipoSuscripcion.GRATUITO;
    }

    public String getId(){
        return id;
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

        //valida y lanza excepcion si es invalido
        validarEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordDebilException {
        this.password = password;
        validarPassword();
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylists(){

        //devolvemos una copia de la playlist y no la playlist, porque si no cualquiera podria modificarla o borrarla
        return new ArrayList<>(misPlaylists);
    }

    public ArrayList<Contenido> getHistorial(){
        return new ArrayList<>(historial);
    }

    public Date getFechaRegistro(){
        return fechaRegistro;
    }

    public ArrayList<Playlist> getPlaylistsSeguidas(){
        return new ArrayList<>(playlistsSeguidas);
    }

    public ArrayList<Contenido> getContenidosLiked(){
        return new ArrayList<>(contenidosLiked);
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ") - " + suscripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
