package excepciones.artista;

public class CancionNoEncontradaException extends RuntimeException {
    public CancionNoEncontradaException(String message) {
        super(message);
    }
}
