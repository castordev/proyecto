import java.util.ArrayList;
import java.util.Date;

import enums.*;
import excepciones.artista.*;
import excepciones.contenido.*;
import excepciones.descarga.*;
import excepciones.plataforma.*;
import excepciones.playlist.*;
import excepciones.recomendacion.*;
import excepciones.usuario.*;
import modelo.artistas.*;
import modelo.contenido.*;
import modelo.plataforma.*;
import modelo.usuarios.*;
import utilidades.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           SOUNDWAVE - Escenarios de Validación               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // Reiniciar la instancia para tener un estado limpio
        Plataforma.reiniciarInstancia();
        Plataforma plataforma = Plataforma.getInstancia("SoundWave");

        // Ejecutar todos los escenarios
        escenario1_RegistroUsuarios(plataforma);
        escenario2_GestionArtistas(plataforma);
        escenario3_GestionAlbumesYCanciones(plataforma);
        escenario4_GestionPodcasts(plataforma);
        escenario5_Playlists(plataforma);
        escenario6_ReproduccionContenido(plataforma);
        escenario7_DescargasOffline(plataforma);
        escenario8_SistemaRecomendaciones(plataforma);
        escenario9_Excepciones(plataforma);
        escenario10_EstadisticasFinales(plataforma);

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         ✓ Todos los escenarios ejecutados con éxito          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void escenario1_RegistroUsuarios(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 1: Registro de Usuarios");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            // Registrar usuarios premium
            UsuarioPremium premium1 = plataforma.registrarUsuarioPremium(
                    "Ana García", "ana@email.com", "password123", TipoSuscripcion.PREMIUM);
            System.out.println("✓ Usuario premium registrado: " + premium1);

            UsuarioPremium premium2 = plataforma.registrarUsuarioPremium(
                    "Carlos López", "carlos@email.com", "securePass1", TipoSuscripcion.FAMILIAR);
            System.out.println("✓ Usuario familiar registrado: " + premium2);

            UsuarioPremium premium3 = plataforma.registrarUsuarioPremium(
                    "María Student", "maria@university.edu", "estudiant3!", TipoSuscripcion.ESTUDIANTE);
            System.out.println("✓ Usuario estudiante registrado: " + premium3);

            // Registrar usuarios gratuitos
            UsuarioGratuito gratuito1 = plataforma.registrarUsuarioGratuito(
                    "Pedro Martínez", "pedro@email.com", "password456");
            System.out.println("✓ Usuario gratuito registrado: " + gratuito1);

            UsuarioGratuito gratuito2 = plataforma.registrarUsuarioGratuito(
                    "Laura Sánchez", "laura@email.com", "mypassword");
            System.out.println("✓ Usuario gratuito registrado: " + gratuito2);

            System.out.println("\n📊 Total usuarios registrados: " + plataforma.getTotalUsuarios());
            System.out.println("   - Premium: " + plataforma.getUsuariosPremium().size());
            System.out.println("   - Gratuitos: " + plataforma.getUsuariosGratuitos().size());

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario2_GestionArtistas(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 2: Gestión de Artistas");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // Crear artistas verificados
        Artista artista1 = plataforma.registrarArtista("Bad Bunny", "Benito Martínez", "Puerto Rico", true);
        System.out.println("✓ Artista verificado creado: " + artista1);

        Artista artista2 = plataforma.registrarArtista("Taylor Swift", "Taylor Alison Swift", "USA", true);
        System.out.println("✓ Artista verificado creado: " + artista2);

        // Crear artista no verificado
        Artista artista3 = plataforma.registrarArtista("Nuevo Artista", "Juan Pérez", "España", false);
        System.out.println("✓ Artista no verificado creado: " + artista3);

        // Verificar al artista
        artista3.verificar();
        System.out.println("✓ Artista verificado: " + artista3);

        System.out.println("\n📊 Artistas verificados: " + plataforma.getArtistasVerificados().size());
        System.out.println("📊 Artistas no verificados: " + plataforma.getArtistasNoVerificados().size());

        System.out.println();
    }

    private static void escenario3_GestionAlbumesYCanciones(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 3: Gestión de Álbumes y Canciones");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            // Buscar artista
            Artista badBunny = plataforma.buscarArtista("Bad Bunny");

            // Crear álbum
            Album album = plataforma.crearAlbum(badBunny, "Un Verano Sin Ti", new Date());
            System.out.println("✓ Álbum creado: " + album);

            // Crear canciones en el álbum
            Cancion cancion1 = album.crearCancion("Moscow Mule", 243, GeneroMusical.REGGAETON);
            System.out.println("✓ Canción creada: " + cancion1);

            Cancion cancion2 = album.crearCancion("Tití Me Preguntó", 243, GeneroMusical.REGGAETON,
                    "Tití me preguntó si tengo muchas novias...", true);
            System.out.println("✓ Canción con letra creada: " + cancion2);

            Cancion cancion3 = album.crearCancion("Me Porto Bonito", 178, GeneroMusical.REGGAETON);
            System.out.println("✓ Canción creada: " + cancion3);

            // Crear canción independiente
            Artista taylor = plataforma.buscarArtista("Taylor Swift");
            Cancion cancionIndie = plataforma.crearCancion("Anti-Hero", 200, taylor, GeneroMusical.POP);
            System.out.println("✓ Canción independiente creada: " + cancionIndie);

            // Simular reproducciones
            cancion1.setReproducciones(150000);
            cancion2.setReproducciones(250000);
            cancion3.setReproducciones(100000);
            cancionIndie.setReproducciones(500000);

            System.out.println("\n📊 Álbum: " + album.getTitulo());
            System.out.println("   - Canciones: " + album.getNumCanciones());
            System.out.println("   - Duración total: " + album.getDuracionTotalFormateada());
            System.out.println("   - Total reproducciones: " + album.getTotalReproducciones());

            System.out.println("\n📊 Top canciones de " + badBunny.getNombreArtistico() + ":");
            for (Cancion c : badBunny.obtenerTopCanciones(3)) {
                System.out.println("   - " + c.getTitulo() + ": " + c.getReproducciones() + " reproducciones");
            }

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario4_GestionPodcasts(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 4: Gestión de Podcasts");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            // Crear creador
            Creador creador = plataforma.registrarCreador("Tech Talks", "Juan Developer",
                    "Podcast sobre tecnología y programación");
            System.out.println("✓ Creador registrado: " + creador);

            // Agregar redes sociales
            creador.agregarRedSocial("Twitter", "@techtalks");
            creador.agregarRedSocial("YouTube", "TechTalksChannel");

            // Crear podcasts
            Podcast podcast1 = plataforma.crearPodcast("Introducción a Java", 3600, creador,
                    1, 1, CategoriaPodcast.TECNOLOGIA);
            podcast1.setDescripcion("Aprende los fundamentos de Java desde cero");
            podcast1.agregarInvitado("María Experta");
            System.out.println("✓ Podcast creado: " + podcast1);

            Podcast podcast2 = plataforma.crearPodcast("Clean Code", 2700, creador,
                    2, 1, CategoriaPodcast.TECNOLOGIA);
            podcast2.setTranscripcion("Transcripción del episodio sobre código limpio...");
            System.out.println("✓ Podcast creado: " + podcast2);

            Podcast podcast3 = plataforma.crearPodcast("IA y Machine Learning", 4200, creador,
                    1, 2, CategoriaPodcast.CIENCIA);
            System.out.println("✓ Podcast creado: " + podcast3);

            // Simular métricas
            podcast1.setReproducciones(10000);
            podcast2.setReproducciones(15000);
            podcast3.setReproducciones(8000);
            creador.setSuscriptores(5000);

            // Generar estadísticas
            EstadisticasCreador stats = creador.obtenerEstadisticas();
            System.out.println("\n" + stats.generarReporte());

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario5_Playlists(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 5: Gestión de Playlists");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            Usuario usuario = plataforma.buscarUsuarioPorEmail("ana@email.com");

            // Crear playlist personal
            Playlist playlistPersonal = usuario.crearPlaylist("Mis Favoritas");
            System.out.println("✓ Playlist personal creada: " + playlistPersonal);

            // Agregar canciones a la playlist
            ArrayList<Cancion> canciones = plataforma.getCanciones();
            for (int i = 0; i < Math.min(3, canciones.size()); i++) {
                playlistPersonal.agregarContenido(canciones.get(i));
                System.out.println("   + Añadida: " + canciones.get(i).getTitulo());
            }

            // Crear playlist pública
            Playlist playlistPublica = plataforma.crearPlaylistPublica("Top Hits 2024", usuario);
            playlistPublica.setDescripcion("Las mejores canciones del momento");
            System.out.println("✓ Playlist pública creada: " + playlistPublica);

            // Agregar contenido
            for (Cancion cancion : canciones) {
                try {
                    playlistPublica.agregarContenido(cancion);
                } catch (ContenidoDuplicadoException e) {
                    // Ignorar duplicados
                }
            }

            // Ordenar por popularidad
            playlistPublica.ordenarPor(CriterioOrden.POPULARIDAD);
            System.out.println("✓ Playlist ordenada por popularidad");

            // Otro usuario sigue la playlist
            Usuario otroUsuario = plataforma.buscarUsuarioPorEmail("pedro@email.com");
            otroUsuario.seguirPlaylist(playlistPublica);
            System.out.println("✓ " + otroUsuario.getNombre() + " ahora sigue la playlist");

            System.out.println("\n📊 Playlist: " + playlistPublica.getNombre());
            System.out.println("   - Contenidos: " + playlistPublica.getNumContenidos());
            System.out.println("   - Duración: " + playlistPublica.getDuracionTotalFormateada());
            System.out.println("   - Seguidores: " + playlistPublica.getSeguidores());

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario6_ReproduccionContenido(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 6: Reproducción de Contenido");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            // Usuario premium reproduce sin restricciones
            UsuarioPremium premium = (UsuarioPremium) plataforma.buscarUsuarioPorEmail("ana@email.com");
            Cancion cancion = plataforma.getCanciones().get(0);

            System.out.println("--- Usuario Premium ---");
            premium.reproducir(cancion);
            premium.darLike(cancion);
            System.out.println("✓ " + premium.getNombre() + " dio like a " + cancion.getTitulo());

            // Usuario gratuito con restricciones
            UsuarioGratuito gratuito = (UsuarioGratuito) plataforma.buscarUsuarioPorEmail("pedro@email.com");

            System.out.println("\n--- Usuario Gratuito ---");
            System.out.println("Reproducciones restantes: " + gratuito.getReproduccionesRestantes());

            // Reproducir varias canciones
            ArrayList<Cancion> canciones = plataforma.getCanciones();
            for (int i = 0; i < Math.min(3, canciones.size()); i++) {
                try {
                    gratuito.reproducir(canciones.get(i));
                } catch (AnuncioRequeridoException e) {
                    System.out.println("📢 Se requiere anuncio - Mostrando anuncio...");
                    gratuito.verAnuncio(plataforma.obtenerAnuncioAleatorio());
                    gratuito.reproducir(canciones.get(i)); // Reintentar
                }
            }

            System.out.println("Reproducciones hoy: " + gratuito.getReproduccionesHoy());
            System.out.println("Anuncios escuchados: " + gratuito.getAnunciosEscuchados());

            // Controles de reproducción
            System.out.println("\n--- Controles de reproducción ---");
            cancion.play();
            cancion.pause();
            cancion.stop();

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario7_DescargasOffline(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 7: Descargas Offline");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            UsuarioPremium premium = (UsuarioPremium) plataforma.buscarUsuarioPorEmail("ana@email.com");
            ArrayList<Cancion> canciones = plataforma.getCanciones();

            System.out.println("Descargas disponibles: " + premium.getDescargasRestantes());

            // Descargar canciones
            for (int i = 0; i < Math.min(3, canciones.size()); i++) {
                premium.descargar(canciones.get(i));
            }

            System.out.println("\n📊 Contenido descargado: " + premium.getNumDescargados());
            System.out.println("   Descargas restantes: " + premium.getDescargasRestantes());

            // Mostrar descargas
            System.out.println("   Lista de descargas:");
            for (Contenido c : premium.getDescargados()) {
                System.out.println("   - " + c.getTitulo());
            }

            // Eliminar una descarga
            if (!canciones.isEmpty()) {
                premium.eliminarDescarga(canciones.get(0));
                System.out.println("\n✓ Descarga eliminada");
                System.out.println("   Descargas restantes: " + premium.getDescargasRestantes());
            }

            // Cambiar calidad de audio
            premium.cambiarCalidadAudio("muy alta");
            System.out.println("✓ Calidad de audio: " + premium.getCalidadAudio());

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario8_SistemaRecomendaciones(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 8: Sistema de Recomendaciones");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        try {
            RecomendadorIA recomendador = plataforma.getRecomendador();

            // Entrenar modelo
            recomendador.entrenarModelo(plataforma.getTodosLosUsuarios(), plataforma.getCatalogo());
            System.out.println("✓ Modelo de recomendación entrenado");
            System.out.println("   Algoritmo: " + recomendador.getAlgoritmo());

            // Obtener recomendaciones para un usuario
            Usuario usuario = plataforma.buscarUsuarioPorEmail("ana@email.com");

            // Asegurarse de que el usuario tiene historial
            if (usuario.getHistorial().isEmpty()) {
                System.out.println("⚠ El usuario no tiene historial, agregando contenido...");
                ArrayList<Cancion> canciones = plataforma.getCanciones();
                for (int i = 0; i < Math.min(3, canciones.size()); i++) {
                    usuario.agregarAlHistorial(canciones.get(i));
                }
                // Actualizar el modelo
                recomendador.actualizarPreferencias(usuario);
            }

            ArrayList<Contenido> recomendaciones = recomendador.recomendar(usuario);
            System.out.println("\n🎯 Recomendaciones para " + usuario.getNombre() + ":");
            for (Contenido c : recomendaciones) {
                System.out.println("   - " + c.getTitulo());
            }

            // Obtener contenido similar
            if (!plataforma.getCanciones().isEmpty()) {
                Cancion cancion = plataforma.getCanciones().get(0);
                ArrayList<Contenido> similares = recomendador.obtenerSimilares(cancion);
                System.out.println("\n🎵 Similar a '" + cancion.getTitulo() + "':");
                for (Contenido c : similares) {
                    System.out.println("   - " + c.getTitulo());
                }
            }

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario9_Excepciones(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 9: Manejo de Excepciones");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // Email inválido
        System.out.println("--- Prueba: Email inválido ---");
        try {
            plataforma.registrarUsuarioGratuito("Test", "email-invalido", "password123");
        } catch (EmailInvalidoException e) {
            System.out.println("✓ EmailInvalidoException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Excepción inesperada: " + e.getMessage());
        }

        // Password débil
        System.out.println("\n--- Prueba: Password débil ---");
        try {
            plataforma.registrarUsuarioGratuito("Test", "test@email.com", "123");
        } catch (PasswordDebilException e) {
            System.out.println("✓ PasswordDebilException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Excepción inesperada: " + e.getMessage());
        }

        // Usuario ya existe
        System.out.println("\n--- Prueba: Usuario ya existe ---");
        try {
            plataforma.registrarUsuarioGratuito("Ana Duplicada", "ana@email.com", "password123");
        } catch (UsuarioYaExisteException e) {
            System.out.println("✓ UsuarioYaExisteException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Excepción inesperada: " + e.getMessage());
        }

        // Duración inválida
        System.out.println("\n--- Prueba: Duración inválida ---");
        try {
            new Cancion("Test", -100, null, GeneroMusical.POP);
        } catch (DuracionInvalidaException e) {
            System.out.println("✓ DuracionInvalidaException capturada: " + e.getMessage());
        }

        // Contenido no disponible
        System.out.println("\n--- Prueba: Contenido no disponible ---");
        try {
            Cancion cancionTest = new Cancion("Test", 180, null, GeneroMusical.POP);
            cancionTest.marcarNoDisponible();
            cancionTest.reproducir();
        } catch (ContenidoNoDisponibleException e) {
            System.out.println("✓ ContenidoNoDisponibleException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Excepción inesperada: " + e.getMessage());
        }

        // Artista no encontrado
        System.out.println("\n--- Prueba: Artista no encontrado ---");
        try {
            plataforma.buscarArtista("Artista Inexistente");
        } catch (ArtistaNoEncontradoException e) {
            System.out.println("✓ ArtistaNoEncontradoException capturada: " + e.getMessage());
        }

        // Contenido ya descargado
        System.out.println("\n--- Prueba: Contenido ya descargado ---");
        try {
            Cancion cancion = plataforma.getCanciones().get(0);
            cancion.descargar();
            cancion.descargar(); // Segunda vez
        } catch (ContenidoYaDescargadoException e) {
            System.out.println("✓ ContenidoYaDescargadoException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Excepción inesperada: " + e.getMessage());
        }

        System.out.println();
    }

    private static void escenario10_EstadisticasFinales(Plataforma plataforma) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ESCENARIO 10: Estadísticas Finales");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        System.out.println(plataforma.obtenerEstadisticasGenerales());

        // Top contenidos
        System.out.println("🏆 Top 5 contenidos:");
        ArrayList<Contenido> top = plataforma.obtenerTopContenidos(5);
        int i = 1;
        for (Contenido c : top) {
            System.out.println("   " + i + ". " + c.getTitulo() + " - " + c.getReproducciones() + " reproducciones");
            i++;
        }

        // Búsquedas
        System.out.println("\n🔍 Búsquedas de prueba:");
        try {
            ArrayList<Cancion> reggaeton = plataforma.buscarPorGenero(GeneroMusical.REGGAETON);
            System.out.println("   Canciones de Reggaetón: " + reggaeton.size());
        } catch (ContenidoNoEncontradoException e) {
            System.out.println("   No se encontraron canciones de Reggaetón");
        }

        try {
            ArrayList<Podcast> techPodcasts = plataforma.buscarPorCategoria(CategoriaPodcast.TECNOLOGIA);
            System.out.println("   Podcasts de Tecnología: " + techPodcasts.size());
        } catch (ContenidoNoEncontradoException e) {
            System.out.println("   No se encontraron podcasts de Tecnología");
        }
    }
}
