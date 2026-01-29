package excepciones.usuario;

public class AnuncioRequeridoExcepcion extends RuntimeException {
    public AnuncioRequeridoExcepcion(String message) {
        super(message);
    }
}
