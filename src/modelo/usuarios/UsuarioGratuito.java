package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoExcepcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Anuncio;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioGratuito  extends Usuario{

    private static final int LIMITE_DIARIO = 30;
    private static final int CANCIONES_ENTRE_ANUNCIOS = 3;

    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    public UsuarioGratuito (String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException{
        super(nombre,email,password, TipoSuscripcion.GRATUITO);

    }

    // Aplica limite diario y logica de anuncios
    @Override
    public void reproducir (Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoExcepcion{
        if(!contenido.isDisponible()){
            throw new ContenidoNoDisponibleException("Contenido no disponible");
        }

        if(reproduccionesHoy > 30){
            throw new LimiteDiarioAlcanzadoException("limite diario alcanzado");
        }

        if(debeVerAnuncio()){
            throw new AnuncioRequeridoExcepcion("anuncio");
        }


        contenido.reproducir();
        reproduccionesHoy++;
        cancionesSinAnuncio++;
        fechaUltimaReproduccion = new Date();
        agregarAlHistorial(contenido);

    }

    public void verAnuncio(){
        anunciosEscuchados++;
        cancionesSinAnuncio = 0;
        ultimoAnuncio = new Date();
    }

    public void verAnuncio(Anuncio anuncio){
        if(anuncio == null) return;

        anuncio.reproducir();
        verAnuncio();
    }

    public boolean puedeReproducir(){
        return reproduccionesHoy < LIMITE_DIARIO && !debeVerAnuncio();
    }

    public boolean debeVerAnuncio(){

        //si el numero de canciones es mayor o igual a las canciones que tiene que haber entre anuncios
        // devuelve true, si no lo es devuelve false
        return cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS;
    }

    public void reiniciarContadorDiario(){
        reproduccionesHoy = 0;
        cancionesSinAnuncio = 0;
    }

    public int getReproduccionesRestantes(){
        return limiteReproducciones - reproduccionesHoy;
    }

    public int getCancionesHastaAnuncio(){
        return CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio;
    }


    public int getAnunciosEscuchados(){
        return anunciosEscuchados;
    }

    public Date getUltimoAnuncio(){
        return ultimoAnuncio;
    }

    public int getReproduccionesHoy(){

    }

    public void setReproduccionesHoy(int reproduccionesHoy){
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones(){
        return limiteReproducciones;
    }

    public int getCancionesSinAnuncio(){
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio){
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ") - GRATUITO [" + reproduccionesHoy + "/" + limiteReproducciones + " reproducciones hoy]";
    }
}
