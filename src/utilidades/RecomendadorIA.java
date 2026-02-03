package utilidades;

import enums.AlgoritmoRecomendacion;
import excepciones.recomendacion.RecomendacionException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class RecomendadorIA {

    private static final double UMBRAL_DEFAULT = 0.6;

    private HashMap<String, ArrayList<Contenido>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido> catalogoReferencia;

    public RecomendadorIA(){

    }

    public RecomendadorIA (AlgoritmoRecomendacion algoritmo) {

    }

    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException{

    }

    @Override
    public ArrayList<Contenido> obtenerSimilares (Contenido contenido) throws RecomendacionException{

    }


    public void entrenarModelo(ArrayList<Usuario> usuarios){

    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo){

    }

    public double calcularSimilitud (Usuario u1, Usuario u2){

    }

    public void actualizarPreferencias(Usuario usuario){

    }

    public HashMap<String, Integer> obtenerGenerosPopulares(){

    }


    //calcular similitud de un contenido frente a referencias
    private double calcularSimilitudContenido(Contenido contenido, ArrayList<String> preferencias){

    }

    public AlgoritmoRecomendacion getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getUmbralSimilud() {
        return umbralSimilud;
    }

    public void setUmbralSimilud(double umbralSimilud) {
        this.umbralSimilud = umbralSimilud;
    }

    public boolean isModeloEntrenado(){

    }

    public HashMap<String, ArrayList<String>> getMatrizPreferencias(){

    }

    public void setCatalogoReferencia (ArrayList<Contenido> catalogo){

    }


}
