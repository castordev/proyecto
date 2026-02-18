package modelo.usuarios;

import java.util.Date;
import java.util.Random;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Anuncio;

public class UsuarioGratuito extends Usuario {

    private static final int LIMITE_DIARIO = 50;
    private static final int CANCIONES_ENTRE_ANUNCIOS = 3;

    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    public UsuarioGratuito(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, TipoSuscripcion.GRATUITO);
        this.anunciosEscuchados = 0;
        this.ultimoAnuncio = null;
        this.reproduccionesHoy = 0;
        this.limiteReproducciones = LIMITE_DIARIO;
        this.cancionesSinAnuncio = 0;
        this.fechaUltimaReproduccion = new Date();
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        if (!contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido '" + contenido.getTitulo() + "' no está disponible");
        }

        // Verificar si es un nuevo día
        verificarNuevoDia();

        // Verificar límite diario
        if (!puedeReproducir()) {
            throw new LimiteDiarioAlcanzadoException("Has alcanzado el límite de " + limiteReproducciones + " reproducciones diarias");
        }

        // Verificar si debe ver anuncio
        if (debeVerAnuncio()) {
            throw new AnuncioRequeridoException("Debes ver un anuncio antes de continuar");
        }

        // Reproducir contenido
        contenido.reproducir();
        reproduccionesHoy++;
        cancionesSinAnuncio++;
        fechaUltimaReproduccion = new Date();
        agregarAlHistorial(contenido);
    }

    private void verificarNuevoDia() {
        Date ahora = new Date();
        if (fechaUltimaReproduccion != null) {
            // Comparar solo la fecha (día)
            if (!esMismoDia(fechaUltimaReproduccion, ahora)) {
                reiniciarContadorDiario();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private boolean esMismoDia(Date fecha1, Date fecha2) {
        return fecha1.getYear() == fecha2.getYear() &&
                fecha1.getMonth() == fecha2.getMonth() &&
                fecha1.getDate() == fecha2.getDate();
    }

    public void verAnuncio() {
        String[] empresas = {"Spotify", "Nike", "Coca-Cola", "Apple", "Samsung"};
        Random random = new Random();
        String empresa = empresas[random.nextInt(empresas.length)];
        System.out.println("Anuncio de " + empresa + " [15s]");
        anunciosEscuchados++;
        cancionesSinAnuncio = 0;
        ultimoAnuncio = new Date();
    }

    public void verAnuncio(Anuncio anuncio) {
        if (anuncio == null) {
            verAnuncio();
        } else {
            anuncio.reproducir();
            anunciosEscuchados++;
            cancionesSinAnuncio = 0;
            ultimoAnuncio = new Date();
        }
    }

    public boolean puedeReproducir() {
        return reproduccionesHoy < limiteReproducciones;
    }

    public boolean debeVerAnuncio() {
        return cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS;
    }

    public void reiniciarContadorDiario() {
        reproduccionesHoy = 0;
        cancionesSinAnuncio = 0;
    }

    public int getReproduccionesRestantes() {
        return limiteReproducciones - reproduccionesHoy;
    }

    public int getCancionesHastaAnuncio() {
        return CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio;
    }

    // Getters y Setters

    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ") - GRATUITO [" + reproduccionesHoy + "/" + limiteReproducciones + " reproducciones hoy]";
    }
}
