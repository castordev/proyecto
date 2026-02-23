package modelo.contenido;

import enums.Idioma;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;

import java.util.ArrayList;
import java.util.Date;

public class Audiolibro extends Contenido implements Descargable, Reproducible {

    private String sinopsis;
    private String autor;
    private String narrador;
    private String editorial;
    private int paginas;
    private String categoria;
    private Idioma idioma;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    public Audiolibro (String titulo,int duracionSegundos,String autor)throws DuracionInvalidaException{
        super(titulo,duracionSegundos);
        this.sinopsis = "";
        this.autor = autor;
        this.narrador = "";
        this.editorial = "";
        this.paginas = 0;
        this.categoria = "";

        // inicializar estados
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
    }

    public Audiolibro(String titulo, int duracionSegundos, String autor, String narrador,
                      String editorial, int paginas, String categoria, Idioma idioma, String sinopsis) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);

        this.autor = autor;
        this.narrador = narrador;
        this.editorial = editorial;
        this.paginas = paginas;
        this.categoria = categoria;
        this.idioma = idioma;
        this.sinopsis = sinopsis;

        // inicializar estados
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
    }


    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getNarrador() { return narrador; }
    public void setNarrador(String narrador) { this.narrador = narrador; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getPaginas() { return paginas; }
    public void setPaginas(int paginas) { this.paginas = paginas; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Idioma getIdioma() { return idioma; }
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }


    // --- Métodos para manejar la fecha de publicación ---
    // como el constructor padre no tiene fechaPublicacion tenemos que hacer esto si queremos tocarla
    public void actualizarFecha(Date nuevaFecha) {
        setFechaPublicacion(nuevaFecha);
    }

    public Date obtenerFecha() {
        return getFechaPublicacion();
    }


    // --- Implementación de métodos abstractos de Contenido ---
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!disponible) throw new ContenidoNoDisponibleException("Audiolibro no disponible");
        System.out.println("Reproduciendo audiolibro: " + titulo);
        aumentarReproducciones();
    }

    @Override
    public void play() {
        reproduciendo = true;
        pausado = false;
        System.out.println("Play audiolibro");
    }

    @Override
    public void pause() {
        if(reproduciendo){
            pausado = true;
            reproduciendo = false;
        }

        System.out.println("Pause audiolibro: " + titulo);
    }

    @Override
    public void stop() {
        reproduciendo = false;
        pausado = false;
        System.out.println("Stop audiolibro: " + titulo);
    }

    @Override
    public int getDuracion() {
        return duracionSegundos;
    }

    // Implementación del metodo requerido por Reproducible
    @Override
    public int getDuration() {
        return getDuracion();
    }

    @Override
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("El audiolibro'" + titulo + "' ya está descargado");
        }
        descargado = true;
        System.out.println("Descargando: " + titulo);
        return true;
    }

    public boolean eliminarDescarga() {
        if (descargado) {
            descargado = false;
            System.out.println("Descarga eliminada: " + titulo);
            return true;
        }
        return false;
    }

    @Override
    public int espacioRequerido() {
        // Aproximación: 1MB por minuto de audio
        return Math.max(1, duracionSegundos / 60);
    }

    @Override
    public String toString() {
        return "Audiolibro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", narrador='" + narrador + '\'' +
                ", duracionSegundos=" + duracionSegundos +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}

