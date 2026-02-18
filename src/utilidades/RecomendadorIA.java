package utilidades;

import java.util.ArrayList;
import java.util.HashMap;

import enums.AlgoritmoRecomendacion;
import excepciones.recomendacion.HistorialVacioException;
import excepciones.recomendacion.ModeloNoEntrenadoException;
import excepciones.recomendacion.RecomendacionException;
import interfaces.Recomendador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;

public class RecomendadorIA implements Recomendador {

    private static final double UMBRAL_DEFAULT = 0.6;

    private HashMap<String, ArrayList<String>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido> catalogoReferencia;

    public RecomendadorIA() {
        this.matrizPreferencias = new HashMap<>();
        this.historialCompleto = new HashMap<>();
        this.algoritmo = AlgoritmoRecomendacion.HIBRIDO;
        this.umbralSimilitud = UMBRAL_DEFAULT;
        this.modeloEntrenado = false;
        this.catalogoReferencia = new ArrayList<>();
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this.matrizPreferencias = new HashMap<>();
        this.historialCompleto = new HashMap<>();
        this.algoritmo = algoritmo;
        this.umbralSimilitud = UMBRAL_DEFAULT;
        this.modeloEntrenado = false;
        this.catalogoReferencia = new ArrayList<>();
    }

    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        if (!modeloEntrenado) {
            throw new ModeloNoEntrenadoException("El modelo de recomendación no está entrenado");
        }
        if (usuario.getHistorial().isEmpty()) {
            throw new HistorialVacioException("El usuario no tiene historial de reproducciones");
        }

        ArrayList<Contenido> recomendaciones = new ArrayList<>();
        ArrayList<String> preferencias = matrizPreferencias.get(usuario.getId());

        if (preferencias == null || preferencias.isEmpty()) {
            actualizarPreferencias(usuario);
            preferencias = matrizPreferencias.get(usuario.getId());
        }

        // Buscar contenidos similares basados en preferencias
        for (Contenido contenido : catalogoReferencia) {
            if (!usuario.getHistorial().contains(contenido)) {
                double similitud = calcularSimilitudContenido(contenido, preferencias);
                if (similitud >= umbralSimilitud) {
                    recomendaciones.add(contenido);
                }
            }
        }

        // Si no hay suficientes recomendaciones, bajar el umbral
        if (recomendaciones.size() < 5) {
            for (Contenido contenido : catalogoReferencia) {
                if (!usuario.getHistorial().contains(contenido) && !recomendaciones.contains(contenido)) {
                    recomendaciones.add(contenido);
                    if (recomendaciones.size() >= 10) break;
                }
            }
        }

        return new ArrayList<>(recomendaciones.subList(0, Math.min(10, recomendaciones.size())));
    }

    @Override
    public ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException {
        ArrayList<Contenido> similares = new ArrayList<>();

        // Obtener características del contenido
        String generoCategoria = "";
        if (contenido instanceof Cancion) {
            generoCategoria = ((Cancion) contenido).getGenero().name();
        } else if (contenido instanceof Podcast) {
            generoCategoria = ((Podcast) contenido).getCategoria().name();
        }

        // Buscar contenidos con el mismo género/categoría
        for (Contenido otro : catalogoReferencia) {
            if (!otro.equals(contenido)) {
                String otroGeneroCategoria = "";
                if (otro instanceof Cancion) {
                    otroGeneroCategoria = ((Cancion) otro).getGenero().name();
                } else if (otro instanceof Podcast) {
                    otroGeneroCategoria = ((Podcast) otro).getCategoria().name();
                }

                if (generoCategoria.equals(otroGeneroCategoria)) {
                    similares.add(otro);
                }
            }
        }

        // Limitar resultados
        return new ArrayList<>(similares.subList(0, Math.min(10, similares.size())));
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios) {
        matrizPreferencias.clear();
        historialCompleto.clear();

        for (Usuario usuario : usuarios) {
            actualizarPreferencias(usuario);
            historialCompleto.put(usuario.getId(), new ArrayList<>(usuario.getHistorial()));
        }

        modeloEntrenado = true;
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
        entrenarModelo(usuarios);
    }

    public double calcularSimilitud(Usuario u1, Usuario u2) {
        ArrayList<String> pref1 = matrizPreferencias.get(u1.getId());
        ArrayList<String> pref2 = matrizPreferencias.get(u2.getId());

        if (pref1 == null || pref2 == null || pref1.isEmpty() || pref2.isEmpty()) {
            return 0.0;
        }

        int coincidencias = 0;
        for (String pref : pref1) {
            if (pref2.contains(pref)) {
                coincidencias++;
            }
        }

        return (double) coincidencias / Math.max(pref1.size(), pref2.size());
    }

    public void actualizarPreferencias(Usuario usuario) {
        ArrayList<String> preferencias = new ArrayList<>();

        for (Contenido contenido : usuario.getHistorial()) {
            if (contenido instanceof Cancion) {
                String genero = ((Cancion) contenido).getGenero().name();
                if (!preferencias.contains(genero)) {
                    preferencias.add(genero);
                }
            } else if (contenido instanceof Podcast) {
                String categoria = ((Podcast) contenido).getCategoria().name();
                if (!preferencias.contains(categoria)) {
                    preferencias.add(categoria);
                }
            }

            // Agregar tags
            for (String tag : contenido.getTags()) {
                if (!preferencias.contains(tag)) {
                    preferencias.add(tag);
                }
            }
        }

        matrizPreferencias.put(usuario.getId(), preferencias);
    }

    public HashMap<String, Integer> obtenerGenerosPopulares() {
        HashMap<String, Integer> popularidad = new HashMap<>();

        for (ArrayList<String> preferencias : matrizPreferencias.values()) {
            for (String pref : preferencias) {
                popularidad.put(pref, popularidad.getOrDefault(pref, 0) + 1);
            }
        }

        return popularidad;
    }

    private double calcularSimilitudContenido(Contenido contenido, ArrayList<String> preferencias) {
        if (preferencias == null || preferencias.isEmpty()) {
            return 0.0;
        }

        int coincidencias = 0;

        // Verificar género/categoría
        if (contenido instanceof Cancion) {
            String genero = ((Cancion) contenido).getGenero().name();
            if (preferencias.contains(genero)) {
                coincidencias += 2; // Mayor peso al género
            }
        } else if (contenido instanceof Podcast) {
            String categoria = ((Podcast) contenido).getCategoria().name();
            if (preferencias.contains(categoria)) {
                coincidencias += 2;
            }
        }

        // Verificar tags
        for (String tag : contenido.getTags()) {
            if (preferencias.contains(tag)) {
                coincidencias++;
            }
        }

        return Math.min(1.0, (double) coincidencias / 3);
    }


    public AlgoritmoRecomendacion getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getUmbralSimilitud() {
        return umbralSimilitud;
    }

    public void setUmbralSimilitud(double umbralSimilitud) {
        this.umbralSimilitud = umbralSimilitud;
    }

    public boolean isModeloEntrenado() {
        return modeloEntrenado;
    }

    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        HashMap<String, ArrayList<String>> copia = new HashMap<>();
        for (String key : matrizPreferencias.keySet()) {
            copia.put(key, new ArrayList<>(matrizPreferencias.get(key)));
        }
        return copia;
    }

    public void setCatalogoReferencia(ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
    }
}
