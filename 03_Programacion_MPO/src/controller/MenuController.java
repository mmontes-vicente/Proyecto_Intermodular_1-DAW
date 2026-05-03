// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package controller;

import model.Socio;
import model.Espacio;
import service.SocioService;
import service.EspacioService;
import service.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador principal de la aplicación.
 * Gestiona la navegación entre menús.
 * Módulo: Programación (0485) — MPO (separación de responsabilidades)
 */
public class MenuController {

    private final Scanner       sc      = new Scanner(System.in);
    private final SocioService   socioSrv   = new SocioService();
    private final EspacioService espacioSrv = new EspacioService();
    private final ReservaService reservaSrv = new ReservaService();

    // ================================================================
    // MENÚ PRINCIPAL
    // ================================================================
    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n" + "═".repeat(45));
            System.out.println("   ⬡  NEXHUB COWORKING — Sistema de Gestión");
            System.out.println("═".repeat(45));
            System.out.println("  1.  Gestión de Socios");
            System.out.println("  2.  Gestión de Espacios");
            System.out.println("  3.  Gestión de Reservas");
            System.out.println("  4.  Estadísticas e Informes");
            System.out.println("  0.  Salir");
            System.out.println("─".repeat(45));
            System.out.print("  Elige una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuSocios();
                case 2 -> menuEspacios();
                case 3 -> menuReservas();
                case 4 -> menuEstadisticas();
                case 0 -> System.out.println("\n   ¡Hasta pronto! Cerrando NexHub...\n");
                default -> System.out.println("  ️ Opción no válida. Elige entre 0 y 4.");
            }

        } while (opcion != 0);
    }

    // ================================================================
    // MENÚ SOCIOS
    // ================================================================
    private void menuSocios() {
        int opcion;
        do {
            System.out.println("\n──  GESTIÓN DE SOCIOS ──────────────────────");
            System.out.println("  1. Ver todos los socios");
            System.out.println("  2. Buscar socio por ID");
            System.out.println("  3. Buscar socios por membresía");
            System.out.println("  4. Dar de alta un socio nuevo");
            System.out.println("  5. Actualizar membresía de un socio");
            System.out.println("  6. Dar de baja a un socio");
            System.out.println("  0. Volver al menú principal");
            System.out.print("  Elige: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> mostrarListaSocios(socioSrv.listarSocios());
                case 2 -> {
                    System.out.print("  ID del socio: ");
                    int id = leerEntero();
                    Socio s = socioSrv.buscarPorId(id);
                    if (s != null) System.out.println("  " + s);
                }
                case 3 -> {
                    System.out.print("  Membresía (basica/premium/empresa): ");
                    String tipo = sc.nextLine().trim();
                    mostrarListaSocios(socioSrv.buscarPorMembresia(tipo));
                }
                case 4 -> altaNuevoSocio();
                case 5 -> {
                    System.out.print("  ID del socio: ");
                    int id = leerEntero();
                    System.out.print("  Nueva membresía (basica/premium/empresa): ");
                    String mem = sc.nextLine().trim();
                    socioSrv.actualizarMembresia(id, mem);
                }
                case 6 -> {
                    System.out.print("  ID del socio a dar de baja: ");
                    int id = leerEntero();
                    System.out.print("  ¿Estás seguro? (s/n): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("s")) {
                        socioSrv.bajaSocio(id);
                    }
                }
                case 0 -> {}
                default -> System.out.println("  ️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ================================================================
    // MENÚ ESPACIOS
    // ================================================================
    private void menuEspacios() {
        int opcion;
        do {
            System.out.println("\n──  GESTIÓN DE ESPACIOS ────────────────────");
            System.out.println("  1. Ver todos los espacios");
            System.out.println("  2. Ver solo los disponibles");
            System.out.println("  3. Cambiar disponibilidad de un espacio");
            System.out.println("  0. Volver al menú principal");
            System.out.print("  Elige: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> mostrarListaEspacios(espacioSrv.listarEspacios());
                case 2 -> mostrarListaEspacios(espacioSrv.listarDisponibles());
                case 3 -> {
                    System.out.print("  ID del espacio: ");
                    int id = leerEntero();
                    System.out.print("  ¿Disponible? (s/n): ");
                    boolean disp = sc.nextLine().trim().equalsIgnoreCase("s");
                    espacioSrv.cambiarDisponibilidad(id, disp);
                }
                case 0 -> {}
                default -> System.out.println("  ️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ================================================================
    // MENÚ RESERVAS
    // ================================================================
    private void menuReservas() {
        int opcion;
        do {
            System.out.println("\n──  GESTIÓN DE RESERVAS ────────────────────");
            System.out.println("  1. Ver todas las reservas");
            System.out.println("  2. Crear una reserva nueva");
            System.out.println("  3. Cambiar estado de una reserva");
            System.out.println("  4. Cancelar una reserva");
            System.out.println("  0. Volver al menú principal");
            System.out.print("  Elige: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> reservaSrv.listarReservas();
                case 2 -> crearNuevaReserva();
                case 3 -> {
                    System.out.print("  ID de la reserva: ");
                    int id = leerEntero();
                    System.out.print("  Nuevo estado (pendiente/confirmada/completada/cancelada): ");
                    String estado = sc.nextLine().trim();
                    reservaSrv.cambiarEstado(id, estado);
                }
                case 4 -> {
                    System.out.print("  ID de la reserva a cancelar: ");
                    int id = leerEntero();
                    reservaSrv.cancelarReserva(id);
                }
                case 0 -> {}
                default -> System.out.println("  ️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ================================================================
    // MENÚ ESTADÍSTICAS
    // ================================================================
    private void menuEstadisticas() {
        System.out.println("\n──  ESTADÍSTICAS E INFORMES ────────────────");
        socioSrv.mostrarEstadisticas();
        reservaSrv.mostrarIngresos();

        System.out.println("\n  Espacios disponibles ahora:");
        mostrarListaEspacios(espacioSrv.listarDisponibles());
    }

    // ================================================================
    // Métodos auxiliares privados
    // ================================================================

    /** Pide los datos por consola y da de alta un socio nuevo */
    private void altaNuevoSocio() {
        System.out.println("\n   Alta de nuevo socio:");
        System.out.print("  Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("  Apellidos: ");
        String apellidos = sc.nextLine().trim();
        System.out.print("  Email: ");
        String email = sc.nextLine().trim();
        System.out.print("  Teléfono: ");
        String tel = sc.nextLine().trim();
        System.out.print("  Membresía (basica/premium/empresa): ");
        String mem = sc.nextLine().trim();

        Socio nuevo = new Socio(nombre, apellidos, email, tel, LocalDate.now(), mem);
        socioSrv.altaSocio(nuevo);
    }

    /** Pide los datos para crear una reserva */
    private void crearNuevaReserva() {
        System.out.println("\n   Nueva reserva:");
        mostrarListaEspacios(espacioSrv.listarDisponibles());
        System.out.print("  ID del socio: ");
        int idSocio = leerEntero();
        System.out.print("  ID del espacio: ");
        int idEspacio = leerEntero();
        System.out.print("  Fecha inicio (yyyy-MM-dd HH:mm:ss): ");
        String inicio = sc.nextLine().trim();
        System.out.print("  Fecha fin   (yyyy-MM-dd HH:mm:ss): ");
        String fin = sc.nextLine().trim();
        reservaSrv.crearReserva(idSocio, idEspacio, inicio, fin);
    }

    /** Muestra una lista de socios formateada */
    private void mostrarListaSocios(List<Socio> lista) {
        if (lista.isEmpty()) {
            System.out.println("  ️ No hay socios que mostrar.");
            return;
        }
        System.out.println("\n" + "─".repeat(80));
        System.out.printf("  %-4s %-15s %-20s %-30s %-10s%n",
                "ID", "Nombre", "Apellidos", "Email", "Membresía");
        System.out.println("─".repeat(80));
        for (Socio s : lista) {
            System.out.println("  " + s);
        }
        System.out.println("─".repeat(80));
        System.out.println("  Total: " + lista.size() + " socios");
    }

    /** Muestra una lista de espacios formateada */
    private void mostrarListaEspacios(List<Espacio> lista) {
        if (lista.isEmpty()) {
            System.out.println("  ️ No hay espacios que mostrar.");
            return;
        }
        System.out.println("\n" + "─".repeat(80));
        for (Espacio e : lista) {
            System.out.println("  " + e);
        }
        System.out.println("─".repeat(80));
        System.out.println("  Total: " + lista.size() + " espacios");
    }

    /**
     * Lee un entero desde consola con manejo de errores.
     * Consume también el salto de línea que queda en el buffer.
     */
    private int leerEntero() {
        int valor = -1;
        try {
            String linea = sc.nextLine().trim();
            valor = Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            System.out.println("  ️ Debes introducir un número.");
        }
        return valor;
    }
}
