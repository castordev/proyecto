package excepciones.usuario;

public class ContenidoYaDescargadoException extends RuntimeException {
    public ContenidoYaDescargadoException(String message) {
        super(message);
    }
}
