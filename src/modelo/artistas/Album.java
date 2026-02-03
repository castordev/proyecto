package modelo.artistas;

import enums.GeneroMusical;
import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;

import java.util.ArrayList;
import java.util.Date;


public class Album  {

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
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
    }


    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }


    // crear cancion y agregar al album

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero) throws AlbumCompletoException, DuracionInvalidaException{

    }

    //crear cancion y establece letra/explicit

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero, String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException{

    }


    //eliminar cancion por posicion (1-based)
    public void eliminarCancion(int posicion) throws CancionNoEncontradaException{

    }

    //eliminar cancion por nombre
    public void eliminarCancion(Cancion cancion) throws  CancionNoEncontradaException{

    }

    public int getDuracionTotal(){

    }

    public String getDuracionTotalFormateada(){

    }

    public int getNumCanciones(){

    }

    public void ordenarPorPopularidad(){

    }

    //obtener cancion por posicion (1-based)
    public Cancion getCancion (int posicion) throws CancionNoEncontradaException{

    }

    public int getTotalReproducciones(){

    }


    public String getId(){
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
        return canciones;
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

    public int getMaxCanciones(){

    }


    @Override
    public String toString(){

    }

    @Override
    public boolean equals(Object obj){

    }

    @Override
    public int hashCode(){

    }


}
