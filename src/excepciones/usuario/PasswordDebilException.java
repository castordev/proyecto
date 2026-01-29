package excepciones.usuario;

public class PasswordDebilException extends RuntimeException {
    public PasswordDebilException(String message) {
        super(message);
    }
}
