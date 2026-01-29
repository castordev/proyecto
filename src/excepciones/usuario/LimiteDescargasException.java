package excepciones.usuario;

public class LimiteDescargasException extends RuntimeException {
    public LimiteDescargasException(String message) {
        super(message);
    }
}
