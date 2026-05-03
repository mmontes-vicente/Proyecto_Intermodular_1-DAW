// Java 24  |  NexHub Coworking — Proyecto Intermodular 1.º DAW
package dao;

import db.Conexion;
import model.Espacio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla espacio.
 */
public class EspacioDAO {

    // --- READ ---

    public List<Espacio> findAll() {
        List<Espacio> lista = new ArrayList<>();
        String sql = "SELECT * FROM espacio ORDER BY tipo, nombre_espacio";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error al listar espacios: " + e.getMessage());
        }
        return lista;
    }

    public List<Espacio> findDisponibles() {
        List<Espacio> lista = new ArrayList<>();
        String sql = "SELECT * FROM espacio WHERE disponible = 1 ORDER BY tipo";

        try (Connection con = Conexion.getConexion();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error al listar espacios disponibles: " + e.getMessage());
        }
        return lista;
    }

    public Espacio findById(int id) {
        String sql = "SELECT * FROM espacio WHERE id_espacio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error al buscar espacio: " + e.getMessage());
        }
        return null;
    }

    // --- UPDATE ---

    public boolean actualizarDisponibilidad(int id, boolean disponible) {
        String sql = "UPDATE espacio SET disponible = ? WHERE id_espacio = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, disponible);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar disponibilidad: " + e.getMessage());
        }
        return false;
    }

    // --- MAPEO privado: ResultSet -> Espacio ---

    private Espacio mapear(ResultSet rs) throws SQLException {
        return new Espacio(
            rs.getInt("id_espacio"),
            rs.getString("nombre_espacio"),
            rs.getString("tipo"),
            rs.getInt("capacidad"),
            rs.getDouble("precio_hora"),
            rs.getBoolean("disponible")
        );
    }
}
