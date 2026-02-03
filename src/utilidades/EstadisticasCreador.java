package utilidades;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;

import java.util.HashMap;

public class EstadisticasCreador {

    private Creador creador;
    private int totalEpisodios;
    private int totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;


    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
    }

    private void calcularEstadisticas(){

    }

    private String formatearDuracion(int segundos){

    }

    public String generarReporte(){

    }

    public double calcularEngagement(){

    }

    public int estimarCrecimientoMensual(){

    }

    public Creador getCreador() {
        return creador;
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public int getTotalReproducciones() {
        return totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public int getTotalSuscriptores() {
        return totalSuscriptores;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getDuracionTotalSegundos() {
        return duracionTotalSegundos;
    }

    public Podcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public HashMap<Integer, Integer> getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
