package modelo.plataforma;

import java.util.ArrayList;
import java.util.Date;

public class Playlist {

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

    public Playlist(String id, String nombre, Usuario creador, ArrayList<Contenido> contenidos, boolean esPublica, int seguidores, String descripcion, String portadaURL, Date fechaCreacion, int maxContenidos) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.contenidos = contenidos;
        this.esPublica = esPublica;
        this.seguidores = seguidores;
        this.descripcion = descripcion;
        this.portadaURL = portadaURL;
        this.fechaCreacion = fechaCreacion;
        this.maxContenidos = maxContenidos;
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

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public ArrayList<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<Contenido> contenidos) {
        this.contenidos = contenidos;
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

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

    public void setMaxContenidos(int maxContenidos) {
        this.maxContenidos = maxContenidos;
    }

    public void agregarContenido (Contenido contenido){

    }

    public boolean eliminarContenido (String idContenido){

    }

    public void ordenarPor (CriterioOrden criterio){

    }

    public int getDuracionTotal (){

    }

    public void ordenarPor (CriterioOrden criterio){

    }

    public int getDurationTotal (){

    }

    public void shuffle (){

    }

    public ArrayList<Contenido> buscarContenido (String termino){

    }

    public void hacePublica (){

    }

    public void hacerPrivada (){

    }

}
