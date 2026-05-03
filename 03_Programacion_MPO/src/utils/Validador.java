// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package utils;

/**
 * Clase utilitaria para validar datos de entrada.
 *
 * MPO — Mejora estructural: Sistema de validación de datos.
 *
 * Centralizar las validaciones aquí evita duplicar código en los servicios
 * y hace que sea fácil saber dónde están todas las reglas de validación.
 *
 * Todos los métodos son estáticos porque no necesitan estado propio:
 * son funciones puras que reciben un dato y devuelven true/false.
 */
public class Validador {

    // Constantes de validación (MPO — uso correcto de constantes)
    public static final int    LONGITUD_MIN_NOMBRE = 2;
    public static final int    LONGITUD_MAX_EMAIL  = 150;
    public static final double PRECIO_MIN          = 0.01;
    public static final int    CAPACIDAD_MIN       = 1;

    // Constructor privado: no queremos que se instancie esta clase
    private Validador() {}

    // ----------------------------------------------------------------
    // Validaciones de texto
    // ----------------------------------------------------------------

    /**
     * Comprueba que un texto no sea nulo ni esté vacío.
     * @param texto el texto a validar
     * @param nombreCampo nombre del campo (para el mensaje de error)
     * @return true si es válido
     */
    public static boolean esTextoValido(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            System.err.println("   El campo '" + nombreCampo + "' no puede estar vacío.");
            return false;
        }
        if (texto.trim().length() < LONGITUD_MIN_NOMBRE) {
            System.err.println("   El campo '" + nombreCampo + "' debe tener al menos "
                    + LONGITUD_MIN_NOMBRE + " caracteres.");
            return false;
        }
        return true;
    }

    // ----------------------------------------------------------------
    // Validación de email
    // ----------------------------------------------------------------

    /**
     * Comprueba que el email tenga formato válido (contiene @ y punto).
     */
    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.err.println("   El email no puede estar vacío.");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            System.err.println("   El email no tiene un formato válido: " + email);
            return false;
        }
        if (email.length() > LONGITUD_MAX_EMAIL) {
            System.err.println("   El email es demasiado largo (máximo " + LONGITUD_MAX_EMAIL + " caracteres).");
            return false;
        }
        return true;
    }

    // ----------------------------------------------------------------
    // Validación de membresía
    // ----------------------------------------------------------------

    /**
     * Comprueba que el tipo de membresía sea uno de los valores permitidos.
     */
    public static boolean esMembresiaValida(String membresia) {
        if (membresia == null) return false;
        boolean valida = membresia.equals("basica")
                      || membresia.equals("premium")
                      || membresia.equals("empresa");
        if (!valida) {
            System.err.println("   Membresía no válida: '" + membresia
                    + "'. Opciones: basica, premium, empresa.");
        }
        return valida;
    }

    // ----------------------------------------------------------------
    // Validación de estado de reserva
    // ----------------------------------------------------------------

    /**
     * Comprueba que el estado de la reserva sea uno de los valores permitidos.
     */
    /** Alias para compatibilidad con ReservaService */
    public static boolean esEstadoValido(String estado) {
        return esEstadoReservaValido(estado);
    }

    public static boolean esEstadoReservaValido(String estado) {
        if (estado == null) return false;
        boolean valido = estado.equals("pendiente")
                      || estado.equals("confirmada")
                      || estado.equals("completada")
                      || estado.equals("cancelada");
        if (!valido) {
            System.err.println("   Estado no válido: '" + estado
                    + "'. Opciones: pendiente, confirmada, completada, cancelada.");
        }
        return valido;
    }

    // ----------------------------------------------------------------
    // Validación de números
    // ----------------------------------------------------------------

    /**
     * Comprueba que un precio sea positivo y mayor que cero.
     */
    public static boolean esPrecioValido(double precio) {
        if (precio < PRECIO_MIN) {
            System.err.println("   El precio debe ser mayor que 0.");
            return false;
        }
        return true;
    }

    /**
     * Comprueba que un ID sea un número positivo.
     */
    public static boolean esIdValido(int id) {
        if (id <= 0) {
            System.err.println("   El ID debe ser un número positivo.");
            return false;
        }
        return true;
    }

    /**
     * Comprueba que una capacidad sea al menos 1.
     */
    public static boolean esCapacidadValida(int capacidad) {
        if (capacidad < CAPACIDAD_MIN) {
            System.err.println("   La capacidad debe ser al menos " + CAPACIDAD_MIN + ".");
            return false;
        }
        return true;
    }

    // ----------------------------------------------------------------
    // Validación completa de un Socio antes de guardarlo
    // ----------------------------------------------------------------

    /**
     * Valida todos los campos de un socio de una vez.
     * Devuelve true solo si TODOS los campos son correctos.
     * MPO — método que agrupa validaciones sin duplicar código.
     */
    public static boolean esSocioValido(String nombre, String apellidos,
                                         String email, String membresia) {
        boolean nombreOk    = esTextoValido(nombre,    "nombre");
        boolean apellidosOk = esTextoValido(apellidos, "apellidos");
        boolean emailOk     = esEmailValido(email);
        boolean membresiaOk = esMembresiaValida(membresia);
        return nombreOk && apellidosOk && emailOk && membresiaOk;
    }
}
