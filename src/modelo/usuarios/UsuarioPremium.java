package modelo.usuarios;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioPremium extends Usuario{

    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    public UsuarioPremium(String id, String nombre, String password, TipoSuscripcion suscripcion, ArrayList<Playlist> misPlaylists, ArrayList<Contenido> historial, Date fechaRegistro, boolean descargasOffline, int maxDescargas, ArrayList<Contenido> descargados, String calidadAudio) {
        super(id, nombre, password, suscripcion, misPlaylists, historial, fechaRegistro);
        this.descargasOffline = descargasOffline;
        this.maxDescargas = maxDescargas;
        this.descargados = descargados;
        this.calidadAudio = calidadAudio;
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public void setMaxDescargas(int maxDescargas) {
        this.maxDescargas = maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return descargados;
    }

    public void setDescargados(ArrayList<Contenido> descargados) {
        this.descargados = descargados;
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    @Override
    public void reproducir (Contenido contenido){
    }

    public void descargar (Contenido contenido){

    }

    public void  eliminarDescarga (Contenido contenido){

    }

    public boolean verificarEspacioDescarga(){

        return true;
    }




}
