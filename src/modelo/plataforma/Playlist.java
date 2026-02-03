package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.Date;

public class Playlist {

    public static final int MAX_CONTENIDOS_DEFAULT = 500;

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
        this.nombre = nombre;
        this.creador = creador;
    }

    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
    }

    public void agregarContenido(Contenido contenido)throws PlaylistLlenaException{

    }

    //borrar contenido por id
    public boolean eliminarContenido(String idContenido){

    }

    //borrar contenido por referencia
    public boolean eliminarContenidoContenido(Contenido contenido){

    }

    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException{

    }

    public int getDuracionTotal(){

    }

    public String getDuracionTotalFormateada(){

    }

    public void shuffle(){

    }

    //buscar contenido por coincidencia en titulo
    public ArrayList<Contenido> buscarContenido(String termino){

    }

    public void hacerPublica(){

    }

    public void hacerPrivada(){

    }

    public void incrementarSeguidores(){

    }

    //sin bajar de 0
    public void decrementarSeguidores(){

    }

    public int getNumContenidos(){

    }

    public boolean estaVacia(){

    }

    //devuelve el contenido en indice (0-based) o null
    public Contenido getContenido(int posicion){

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

    public Usuario getCreador() {
        return creador;
    }

    public ArrayList<Contenido> getContenidos() {
        return contenidos;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
