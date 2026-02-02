package modelo.contenido;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public abstract class Contenido {

    protected String id;
    protected  String titulo;
    protected int reproducciones;
    protected int likes;
    protected int duracionSegundos;
    protected ArrayList<String> tags;
    protected boolean disponible;
    protected Date fechaPublicacion;


    public Contenido(String titulo, int duracionSegundos) throws DuracionInvalidaException {

        // metodo para generar un id aleatorio
        this.id = java.util.UUID.randomUUID().toString();

        this.titulo = titulo;
        this.duracionSegundos = duracionSegundos;

        //valores por defecto (pueden ir en los atributos tambien)

        this.reproducciones = 0;
        this.likes = 0;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();
    }

    // metodo abstracto

    public abstract void reproducir() throws ContenidoNoDisponibleException;

    // metodos concretos

    public void aumentarReproducciones(){

    }

    public void agregarLike(){

    }

    public boolean esPopular(){

    }

    public void validarDuracion() throws  DuracionInvalidaException{

    }

    public void agregarTag (String tag){

    }

    public boolean tieneTag (String Tag){

    }

    public void marcarNoDisponible(){

    }

    public void marcarDisponible(){

    }

    public String getDuracionFormateada(){

    }


    // gets sets

    public String getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){

    }

    public int getReproducciones(){
        return reproducciones;
    }

    public void setReproducciones(int reproducciones){

    }

    public int getLikes(){
        return likes;
    }

    public int getDuracionSegundos(){
        return duracionSegundos;
    }

    public ArrayList<String> getTags(){

    }

    public boolean isDisponible(){

    }

    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj){
    }

    @Override
    public int hashCode(){

    }



}
