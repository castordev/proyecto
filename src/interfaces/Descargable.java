package interfaces;

import excepciones.usuario.ContenidoYaDescargadoException;
import excepciones.usuario.LimiteDescargasException;

public interface Descargable {

    boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException;

    boolean eliminarDescarga();
    int espacioRequerido();


}
