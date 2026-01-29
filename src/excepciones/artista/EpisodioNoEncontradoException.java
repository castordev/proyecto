package excepciones.artista;

public class EpisodioNoEncontradoException extends RuntimeException {
    public EpisodioNoEncontradoException(String message) {
        super(message);
    }
}
