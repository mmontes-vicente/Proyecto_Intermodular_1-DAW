// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package dao;

import db.Conexion;

import java.sql.*;

/**
 * DAO para la tabla reserva.
 * La logica de calculo de precio y horas se mantiene en ReservaService;
 * este DAO solo habla con la base de datos.
 */
public class ReservaDAO {

    // --- CREATE ---

    public boolean insertar(int idSocio, int idEspacio,
                             String fechaInicio, String fechaFin,
                             double precioTotal) {
        String sql = "INSERT INTO reserva (id_socio, id_espacio, fecha_inicio, fecha_fin, estado, precio_total) " +
                     "VALUES (?, ?, ?, ?, 'pendiente', ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSocio);
            ps.setInt(2, idEspacio);
            ps.setString(3, fechaInicio);
            ps.setString(4, fechaFin);
            ps.setDouble(5, precioTotal);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear reserva: " + e.getMessage());
        }
        return false;
    }

    // --- READ ---

    public void listarConJoin() {
        String sql = "SELECT r.id_reserva, CONCAT(s.nombre,' ',s.apellidos) AS socio, " +
                     "e.nombre_espacio, r.fecha_inicio, r.fecha_fin, r.estado, r.precio_total " +
                     "FROM reserva r " +
                     "JOIN socio s   ON r.id_socio   = s.id_socio " +
                     "JOIN espacio e ON r.id_espacio = e.id_espacio " +
                     "ORDER BY r.fecha_inicio DESC";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            System.out.println("\nRESERVAS:");
            System.out.println("=".repeat(90));
            System.out.printf("%-5s %-22s %-22s %-20s %-14s %s%n",
                    "ID", "Socio", "Espacio", "Inicio", "Estado", "Total");
            System.out.println("-".repeat(90));

            while (rs.next()) {
                System.out.printf("%-5d %-22s %-22s %-20s %-14s %.2f%n",
                        rs.getInt("id_reserva"),
                        rs.getString("socio"),
                        rs.getString("nombre_espacio"),
                        rs.getString("fecha_inicio"),
                        rs.getString("estado"),
                        rs.getDouble("precio_total"));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar reservas: " + e.getMessage());
        }
    }

    public double getPrecioHoraEspacio(int idEspacio) {
        String sql = "SELECT precio_hora FROM espacio WHERE id_espacio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("precio_hora");

        } catch (SQLException e) {
            System.err.println("Error al obtener precio hora: " + e.getMessage());
        }
        return 0;
    }

    // --- UPDATE ---

    public boolean actualizarEstado(int idReserva, String nuevoEstado) {
        String sql = "UPDATE reserva SET estado = ? WHERE id_reserva = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idReserva);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al cambiar estado: " + e.getMessage());
        }
        return false;
    }

    // --- ESTADISTICA ---

    public void mostrarIngresos() {
        String sql = "SELECT SUM(precio_total) AS total, COUNT(*) AS num " +
                     "FROM reserva WHERE estado = 'completada'";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            if (rs.next()) {
                System.out.printf("%nIngresos totales (reservas completadas): %.2f (%d reservas)%n",
                        rs.getDouble("total"), rs.getInt("num"));
            }

        } catch (SQLException e) {
            System.err.println("Error al calcular ingresos: " + e.getMessage());
        }
    }
}
