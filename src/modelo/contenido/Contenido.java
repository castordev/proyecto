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

        //generar un id aleatorio
        this.id = java.util.UUID.randomUUID().toString();

        this.titulo = titulo;
        this.duracionSegundos = duracionSegundos;
        this.reproducciones = 0;
        this.likes = 0;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();
    }


    public abstract void reproducir() throws ContenidoNoDisponibleException;


    public void aumentarReproducciones(){
        reproducciones ++;
    }

    public void agregarLike(){
        likes ++;
    }

    public boolean esPopular(){
        return reproducciones >= 1000 || likes >= 100;
    }

    public void validarDuracion() throws  DuracionInvalidaException{
        if (duracionSegundos <= 0){
            throw new   DuracionInvalidaException("La duración no puede ser menor que 0 segundos")
        }
    }


    // --- TAGS ---

    public void agregarTag (String tag){

        //si tag es nulo o vacio, no vale
        if (tag == null || tag.trim().isEmpty()){
            return;
        }

        // si el array no contiene el tag, lo añadimos
        if(!tags.contains(tag)){
            tags.add(tag);
        }
    }

    //tags.contains(tag) revisa el array de tags y devuelve true si lo hay
    public boolean tieneTag (String tag){
        return tags.contains(tag);
    }


    // --- DISPONIBILIDAD DEL CONTENIDO ---

    public void marcarNoDisponible(){
        disponible = false;
    }

    public void marcarDisponible(){
        disponible = true;
    }

    public boolean isDisponible(){
        return disponible;
    }


     // ---

    public String getDuracionFormateada(){
        int minutos = duracionSegundos/60;
        int segundos = duracionSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }


    public String getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public int getReproducciones(){
        return reproducciones;
    }

    public void setReproducciones(int reproducciones){
        this.reproducciones = reproducciones;
    }

    public int getLikes(){
        return likes;
    }

    public int getDuracionSegundos(){
        return duracionSegundos;
    }

    public ArrayList<String> getTags(){
        return tags;
    }


    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
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
