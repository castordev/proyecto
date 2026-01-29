package utilidades;

import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class RecomendadorIA {

    private HashMap<String, ArrayList<Conenido>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilud;
    private boolean modeloEntrenado;


    public RecomendadorIA(HashMap<String, ArrayList<Conenido>> matrizPreferencias, HashMap<String, ArrayList<Contenido>> historialCompleto, AlgoritmoRecomendacion algoritmo, double umbralSimilud, boolean modeloEntrenado) {
        this.matrizPreferencias = matrizPreferencias;
        this.historialCompleto = historialCompleto;
        this.algoritmo = algoritmo;
        this.umbralSimilud = umbralSimilud;
        this.modeloEntrenado = modeloEntrenado;
    }


    public HashMap<String, ArrayList<Conenido>> getMatrizPreferencias() {
        return matrizPreferencias;
    }

    public void setMatrizPreferencias(HashMap<String, ArrayList<Conenido>> matrizPreferencias) {
        this.matrizPreferencias = matrizPreferencias;
    }

    public HashMap<String, ArrayList<Contenido>> getHistorialCompleto() {
        return historialCompleto;
    }

    public void setHistorialCompleto(HashMap<String, ArrayList<Contenido>> historialCompleto) {
        this.historialCompleto = historialCompleto;
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

    public boolean isModeloEntrenado() {
        return modeloEntrenado;
    }

    public void setModeloEntrenado(boolean modeloEntrenado) {
        this.modeloEntrenado = modeloEntrenado;
    }

    public ArrayList<Contenido> recomendar (Usuario usuario){

    }

    public ArrayList<Contenido> obtenerSimilares (Contenido contenido){

    }

    public void entrenarModelo (ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo){

    }

    public double calcularSimilitud (Usuario u1, Usuario u2){

    }

    public void actualizarPreferencias (Usuario usuario){

    }

}
