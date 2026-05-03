// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de conexión a la base de datos MySQL mediante JDBC.
 * Módulo: Programación (0485)
 */
public class Conexion {

    // ---- CAMBIA ESTOS DATOS POR LOS TUYOS ----
    private static final String URL      = "jdbc:mysql://localhost:3306/nexhub_db";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "";  // en XAMPP suele estar vacía

    private static Connection conexion = null;

    /**
     * Devuelve la conexión activa o crea una nueva si no existe.
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println(" Conexión a la base de datos establecida.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println(" Driver MySQL no encontrado. Añade mysql-connector-j.jar al proyecto.");
            System.err.println("   Descárgalo de: https://dev.mysql.com/downloads/connector/j/");
        } catch (SQLException e) {
            System.err.println(" Error al conectar con la base de datos: " + e.getMessage());
            System.err.println("   Comprueba que MySQL/XAMPP está activo y los datos de conexión son correctos.");
        }
        return conexion;
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println(" Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
