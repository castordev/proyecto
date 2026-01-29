package modelo.usuarios;

import java.util.ArrayList;
import java.util.Date;

public abstract class Usuario {

    protected String id;
    protected String nombre;
    protected String password;
    protected TipoSuscripcion suscripcion;
    protected ArrayList<Playlist> misPlaylists;
    protected ArrayList<Contenido> historial;
    protected Date fechaRegistro;

    public Usuario(String id, String nombre, String password, TipoSuscripcion suscripcion, ArrayList<Playlist> misPlaylists, ArrayList<Contenido> historial, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.suscripcion = suscripcion;
        this.misPlaylists = misPlaylists;
        this.historial = historial;
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylists() {
        return misPlaylists;
    }

    public void setMisPlaylists(ArrayList<Playlist> misPlaylists) {
        this.misPlaylists = misPlaylists;
    }

    public ArrayList<Contenido> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Contenido> historial) {
        this.historial = historial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }



    public abstract void reproducir (Contenido contenido);


    public void crearPlaylist (String nombre){

    }

    public void seguirPlaylist (Playlist playlist){

    }

    public void darLike (Contenido contenido){

    }

    public boolean validarEmail (){

        return true;
    }

    public boolean validarPassword(){

        return true;
    }

    public void agregarAlHistorial (Contenido contenido){

    }


}
