package modelo.usuarios;

import java.util.ArrayList;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import interfaces.Descargable;
import modelo.contenido.Contenido;

public class UsuarioPremium extends Usuario {

    private static final int MAX_DESCARGAS_DEFAULT = 100;

    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    public UsuarioPremium(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, TipoSuscripcion.PREMIUM);
        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "alta";
    }

    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.descargasOffline = suscripcion.isDescargasOffline();
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "alta";
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        if (!contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido '" + contenido.getTitulo() + "' no está disponible");
        }
        // Premium reproduce sin anuncios ni límite diario
        contenido.reproducir();
        agregarAlHistorial(contenido);
    }

    public void descargar(Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (!descargasOffline) {
            throw new LimiteDescargasException("Tu plan no permite descargas offline");
        }
        if (descargados.size() >= maxDescargas) {
            throw new LimiteDescargasException("Has alcanzado el límite de " + maxDescargas + " descargas");
        }
        if (descargados.contains(contenido)) {
            throw new ContenidoYaDescargadoException("El contenido ya está descargado");
        }

        // Si el contenido implementa Descargable, usamos su metodo
        if (contenido instanceof Descargable) {
            try {
                ((Descargable) contenido).descargar();
            } catch (ContenidoYaDescargadoException e) {
                // El contenido ya estaba marcado como descargado en su estado interno
            }
        }
        descargados.add(contenido);
        System.out.println("⬇ Descargado: " + contenido.getTitulo());
    }

    public boolean eliminarDescarga(Contenido contenido) {
        if (descargados.remove(contenido)) {
            if (contenido instanceof Descargable) {
                ((Descargable) contenido).eliminarDescarga();
            }
            return true;
        }
        return false;
    }

    public boolean verificarEspacioDescarga() {
        return descargados.size() < maxDescargas;
    }

    public int getDescargasRestantes() {
        return maxDescargas - descargados.size();
    }

    public void cambiarCalidadAudio(String calidad) {
        if (calidad != null && (calidad.equals("baja") || calidad.equals("media") ||
                calidad.equals("alta") || calidad.equals("muy alta"))) {
            this.calidadAudio = calidad;
        }
    }

    public void limpiarDescargas() {
        for (Contenido contenido : descargados) {
            if (contenido instanceof Descargable) {
                ((Descargable) contenido).eliminarDescarga();
            }
        }
        descargados.clear();
    }

    // Getters y Setters

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return new ArrayList<>(descargados);
    }

    public int getNumDescargados() {
        return descargados.size();
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ") - " + suscripcion + " [" + getNumDescargados() + "/" + maxDescargas + " descargas]";
    }
}
