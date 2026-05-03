// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package dao;

import db.Conexion;
import model.Socio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para la tabla socio.
 * Concentra toda la logica de acceso a base de datos para socios.
 * El servicio SocioService usa este DAO y no toca JDBC directamente.
 */
public class SocioDAO {

    // --- CREATE ---

    public boolean insertar(Socio s) {
        String sql = "INSERT INTO socio (nombre, apellidos, email, telefono, fecha_alta, tipo_membresia) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getNombre());
            ps.setString(2, s.getApellidos());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getTelefono());
            ps.setDate(5, Date.valueOf(s.getFechaAlta()));
            ps.setString(6, s.getTipoMembresia());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: ya existe un socio con ese email.");
        } catch (SQLException e) {
            System.err.println("Error al insertar socio: " + e.getMessage());
        }
        return false;
    }

    // --- READ ---

    public List<Socio> findAll() {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socio ORDER BY apellidos, nombre";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error al listar socios: " + e.getMessage());
        }
        return lista;
    }

    public Socio findById(int id) {
        String sql = "SELECT * FROM socio WHERE id_socio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error al buscar socio: " + e.getMessage());
        }
        return null;
    }

    public List<Socio> findByMembresia(String tipo) {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socio WHERE tipo_membresia = ? ORDER BY apellidos";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error al buscar por membresia: " + e.getMessage());
        }
        return lista;
    }

    // --- UPDATE ---

    public boolean actualizarMembresia(int id, String nuevaMembresia) {
        String sql = "UPDATE socio SET tipo_membresia = ? WHERE id_socio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaMembresia);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar membresia: " + e.getMessage());
        }
        return false;
    }

    // --- DELETE ---

    public boolean eliminar(int id) {
        String sql = "DELETE FROM socio WHERE id_socio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("No se puede eliminar: el socio tiene reservas asociadas.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar socio: " + e.getMessage());
        }
        return false;
    }

    // --- ESTADISTICA ---

    public void contarPorMembresia() {
        String sql = "SELECT tipo_membresia, COUNT(*) AS total FROM socio GROUP BY tipo_membresia";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            System.out.println("\nEstadisticas de membresias:");
            System.out.println("----------------------------");
            while (rs.next()) {
                System.out.printf("  %-10s : %d socios%n",
                        rs.getString("tipo_membresia"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener estadisticas: " + e.getMessage());
        }
    }

    // --- MAPEO privado: ResultSet -> Socio ---

    private Socio mapear(ResultSet rs) throws SQLException {
        return new Socio(
            rs.getInt("id_socio"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("email"),
            rs.getString("telefono"),
            rs.getDate("fecha_alta").toLocalDate(),
            rs.getString("tipo_membresia")
        );
    }
}
