package modelo.plataforma;

import java.util.UUID;
import java.util.Objects;

import enums.TipoAnuncio;


public class Anuncio {

    private String id;
    private String empresa;
    private int duracionSegundos;
    private String audioURL;
    private TipoAnuncio tipo;
    private int impresiones;
    private double presupuesto;
    private boolean activo;


    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto) {
        this.id = UUID.randomUUID().toString();
        this.empresa = empresa;
        this.tipo = tipo;
        this.presupuesto = presupuesto;
        this.duracionSegundos = tipo.getDuracionSegundos();
        this.impresiones = 0;
        this.activo = true;
    }


    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto, String audioURL) {
        this.id = UUID.randomUUID().toString();
        this.empresa = empresa;
        this.tipo = tipo;
        this.presupuesto = presupuesto;
        this.duracionSegundos = tipo.getDuracionSegundos();
        this.audioURL = audioURL;
        this.impresiones = 0;
        this.activo = true;
    }


    public void reproducir() {
        if (activo) {
            registrarImpresion();
        }
    }

    public void registrarImpresion() {
        impresiones++;
        if (calcularCostoTotal() >= presupuesto) {
            desactivar();
        }
    }

    public double calcularCostoPorImpresion() {
        return tipo.getCostoPorImpresion();
    }

    public double calcularCostoTotal() {
        return impresiones * calcularCostoPorImpresion();
    }

    public int calcularImpresionesRestantes() {
        double costoRestante = presupuesto - calcularCostoTotal();
        if (costoRestante <= 0) {
            return 0;
        }
        return (int) (costoRestante / calcularCostoPorImpresion());
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    public boolean puedeMostrarse() {
        return activo && calcularCostoTotal() < presupuesto;
    }

    public String getId() {
        return id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public int getImpresiones() {
        return impresiones;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Anuncio de " + empresa + " [" + tipo + "] - " + impresiones + " impresiones";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anuncio anuncio = (Anuncio) obj;
        return Objects.equals(id, anuncio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
