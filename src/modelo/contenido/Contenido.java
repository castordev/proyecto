package modelo.contenido;

import java.util.ArrayList;
import java.util.Date;

public abstract class Contenido {

    protected String id;
    protected  String titulo;
    protected int reproducciones;
    protected int likes;
    protected int duracionSegundos;
    protected ArrayList<String> tags;
    protected boolean disponible;
    protected Date fechaPublicacion;


    public Contenido(String id, String titulo, int reproducciones, int likes, int duracionSegundos, ArrayList<String> tags, boolean disponible, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.reproducciones = reproducciones;
        this.likes = likes;
        this.duracionSegundos = duracionSegundos;
        this.tags = tags;
        this.disponible = disponible;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public abstract void reproducir();

    public void aumentarReproducciones(){

    }

    public void agregarLike(){

    }

    public boolean esPopular(){
        return true;
    }

    public void validarDuracion (){

    }

}
