package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import excepciones.usuario.AnuncioRequeridoExcepcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioPremium extends Usuario{

    private static final int MAX_DESCARGAS_DEFAULT = 100;

    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;



    public UsuarioPremium (String nombre, String email, String password)throws EmailInvalidoException, PasswordDebilException {
        super(nombre,email,password,TipoSuscripcion.PREMIUM);

        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();

    }

    public UsuarioPremium (String nombre, String email, String password, TipoSuscripcion suscripcion)throws EmailInvalidoException, PasswordDebilException {
        super(nombre,email,password, TipoSuscripcion.PREMIUM);

    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteEpisodiosException, AnuncioRequeridoExcepcion {
        if(!contenido.isDisponible()){
            throw new ContenidoNoDisponibleException("Contenido no disponible");
        }
        contenido.reproducir();
        agregarAlHistorial(contenido);
    }

    public void descargar (Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException{

    }

    public boolean eliminarDescarga (Contenido contenido){

    }

    public boolean verificarEspacioDescarga(){

    }

    public int getDescargasRestantes(){

    }

    public void cambiarCalidadAudio(String calidad){

    }

    public void limpiarDescargas(){

    }

    public boolean isDescargasOffline(){

    }

    public void setDescargasOffline(boolean descargasOffline){

    }

    public int getMaxDescargas(){

    }

    public ArrayList<Contenido> getDescargados(){

    }

    public int getNumDescargados(){

    }

    public String getCalidadAudio(){

    }

    public void setCalidadAudio(String calidadAudio){

    }

    @Override
    public String toString() {
        return super.toString();
    }

}
