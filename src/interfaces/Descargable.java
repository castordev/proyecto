package interfaces;

public interface Descargable {

    boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException;

    boolean eliminarDescarga();
    int espacioRequerido();


}
