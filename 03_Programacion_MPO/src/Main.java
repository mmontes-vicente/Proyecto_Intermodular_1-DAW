// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
import controller.MenuController;
import db.Conexion;

/**
 * ===================================================
 * NEXHUB COWORKING — Aplicación de Gestión
 * ===================================================
 * Proyecto Intermodular 1º DAW
 * Módulo: Programación (0485)
 *
 * Cómo ejecutar:
 *   1. Asegúrate de tener MySQL activo (XAMPP)
 *   2. Importa la base de datos con los scripts SQL
 *   3. Ajusta usuario/contraseña en db/Conexion.java
 *   4. Añade mysql-connector-j.jar a las librerías del proyecto
 *   5. Ejecuta esta clase
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("═".repeat(50));
        System.out.println("   ⬡  NexHub Coworking — Sistema de Gestión");
        System.out.println("   Proyecto Intermodular 1º DAW");
        System.out.println("═".repeat(50));

        // Verificar conexión a la base de datos antes de arrancar
        if (Conexion.getConexion() == null) {
            System.err.println("\n No se pudo conectar a la base de datos.");
            System.err.println("   Revisa que MySQL/XAMPP esté activo y los datos en db/Conexion.java");
            System.exit(1);
        }

        // Lanzar el menú principal
        MenuController menu = new MenuController();
        menu.menuPrincipal();

        // Cerrar conexión al salir
        Conexion.cerrarConexion();
    }
}
