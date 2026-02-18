package utilidades;

import java.util.HashMap;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;

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
        this.episodiosPorTemporada = new HashMap<>();
        calcularEstadisticas();
    }

    private void calcularEstadisticas() {
        totalEpisodios = creador.getNumEpisodios();
        totalSuscriptores = creador.getSuscriptores();
        totalReproducciones = 0;
        totalLikes = 0;
        duracionTotalSegundos = 0;
        episodioMasPopular = null;
        int maxReproducciones = 0;

        for (Podcast podcast : creador.getEpisodios()) {
            totalReproducciones += podcast.getReproducciones();
            totalLikes += podcast.getLikes();
            duracionTotalSegundos += podcast.getDuracionSegundos();

            // Episodio más popular
            if (podcast.getReproducciones() > maxReproducciones) {
                maxReproducciones = podcast.getReproducciones();
                episodioMasPopular = podcast;
            }

            // Contar episodios por temporada
            int temporada = podcast.getTemporada();
            episodiosPorTemporada.put(temporada, episodiosPorTemporada.getOrDefault(temporada, 0) + 1);
        }

        promedioReproducciones = totalEpisodios > 0 ? (double) totalReproducciones / totalEpisodios : 0;
    }

    private String formatearDuracion(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;

        if (horas > 0) {
            return String.format("%dh %dm %ds", horas, minutos, segs);
        } else if (minutos > 0) {
            return String.format("%dm %ds", minutos, segs);
        } else {
            return String.format("%ds", segs);
        }
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Estadísticas de ").append(creador.getNombreCanal()).append(" ===\n");
        sb.append("Creador: ").append(creador.getNombre()).append("\n");
        sb.append("Total episodios: ").append(totalEpisodios).append("\n");
        sb.append("Total reproducciones: ").append(totalReproducciones).append("\n");
        sb.append("Promedio reproducciones: ").append(String.format("%.2f", promedioReproducciones)).append("\n");
        sb.append("Total suscriptores: ").append(totalSuscriptores).append("\n");
        sb.append("Total likes: ").append(totalLikes).append("\n");
        sb.append("Duración total: ").append(formatearDuracion(duracionTotalSegundos)).append("\n");

        if (episodioMasPopular != null) {
            sb.append("Episodio más popular: ").append(episodioMasPopular.getTitulo())
                    .append(" (").append(episodioMasPopular.getReproducciones()).append(" reproducciones)\n");
        }

        sb.append("Temporadas: ").append(episodiosPorTemporada.size()).append("\n");
        for (Integer temporada : episodiosPorTemporada.keySet()) {
            sb.append("  - T").append(temporada).append(": ").append(episodiosPorTemporada.get(temporada)).append(" episodios\n");
        }

        sb.append("Engagement: ").append(String.format("%.2f%%", calcularEngagement())).append("\n");
        sb.append("Estimación crecimiento mensual: ").append(estimarCrecimientoMensual()).append(" suscriptores\n");

        return sb.toString();
    }

    public double calcularEngagement() {
        if (totalSuscriptores == 0 || totalEpisodios == 0) {
            return 0.0;
        }
        // Engagement = (reproducciones promedio / suscriptores) * 100
        return (promedioReproducciones / totalSuscriptores) * 100;
    }

    public int estimarCrecimientoMensual() {
        // Fórmula simple: (reproducciones totales / 100) * engagement
        double engagement = calcularEngagement();
        if (engagement == 0) {
            return totalReproducciones / 1000;
        }
        return (int) ((totalReproducciones / 100.0) * (engagement / 100.0));
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
        return new HashMap<>(episodiosPorTemporada);
    }

    @Override
    public String toString() {
        return generarReporte();
    }
}
