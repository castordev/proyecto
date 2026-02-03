package modelo.usuarios;

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
        super(nombre,email,password);

    }

    // Aplica limite diario y logica de anuncios
    public void reproducir (Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoExcepcion{

    }

    public void verAnuncio(){

    }

    public void verAnuncio(Anuncio anuncio){

    }

    public boolean puedeReproducir(){

    }

    public boolean debeVerAnuncio(){

    }

    public void reiniciarContadorDiario(){

    }

    public int getReproduccionesRestantes(){

    }

    public int getCancionesHastaAnuncio(){

    }


    public int getAnunciosEscuchados(){

    }

    public Date getUltimoAnuncio(){

    }

    public int getLimiteReproduccionesHoy(){

    }

    public void setReproduccionesHoy(int ReproduccionesHoy){

    }

    public int getLimiteReproducciones(){

    }

    public int getCancionesSinAnuncio(){

    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
