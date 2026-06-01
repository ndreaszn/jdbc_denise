package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    // conexión test
    String url = "jdbc:mysql://localhost:3306/hipermercado";
    String user = "root";
    String password = "root";

    String select = "SELECT p.id_producto, p.nombre, p.descripcion, p.id_seccion, s.nombre AS nombre_seccion, p.id_marca, m.nombre AS nombre_marca, p.precio, p.stock, p.unidad_medida, p.codigo_barras, p.activo FROM productos p JOIN secciones s ON p.id_seccion = s.id_seccion JOIN marcas m ON p.id_marca = m.id_marca ";

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();

        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setIdSeccion(rs.getInt("id_seccion"));
        p.setNombreSeccion(rs.getString("nombre_seccion"));
        p.setIdMarca(rs.getInt("id_marca"));
        p.setNombreMarca(rs.getString("nombre_marca"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setUnidadMedida(rs.getString("unidad_medida"));
        p.setCodigoBarras(rs.getString("codigo_barras"));
        p.setActivo(rs.getBoolean("activo"));

        return p;
    }

    public Producto buscarPorId(int id) {

        String sql = select + "WHERE p.id_producto = ?";

        Connection con = null;
        PreparedStatement ps  = null;
        ResultSet rs = null;

        try {
            // abrir la conexión con la bbdd
            con = DriverManager.getConnection(url, user, password);

            ps = con.prepareStatement(sql);

            // sustituir el '?' por el ID real
            ps.setInt(1, id);

            // ResultSet para ejecutar consulta
            rs = ps.executeQuery();

            // next() devuelve true si hay fila/producto
            if (rs.next()) {
                return mapearProducto(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, ps, con);
        }

        return null; // No encontrado
    }

    public boolean crearProducto(Producto p) {

        String sql = "INSERT INTO productos (nombre, descripcion, id_seccion, id_marca, precio, stock, unidad_medida, codigo_barras, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps  = null;
        ResultSet rsKeys = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            // coge el valor del auto increment
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // para asignar cada '?' con la info del producto
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getIdSeccion());
            ps.setInt(4, p.getIdMarca());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getStock());
            ps.setString(7, p.getUnidadMedida());
            ps.setString(8, p.getCodigoBarras());
            ps.setInt(9, p.isActivo() ? 1 : 0);

            // ejecuta el insert
            int filasInsertadas = ps.executeUpdate();

            // id generado por auto_increment
            if (filasInsertadas > 0) {
                rsKeys = ps.getGeneratedKeys();
                if (rsKeys.next()) {
                    p.setIdProducto(rsKeys.getInt(1)); // guardamos el nuevo id en el objeto
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
        } finally {
            cerrarRecursos(rsKeys, ps, con);
        }

        return false;
    }

    public List<Producto> filtrarPorCategoria(String categoria) {

        String sql = select + "WHERE s.nombre LIKE ? AND p.activo = 1 ORDER BY p.nombre";

        List<Producto> lista = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps  = null;
        ResultSet rs  = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            // PreparedStatement: '?' tomará el valor "%categoria%"
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + categoria + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al filtrar por categoría: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, ps, con);
        }

        return lista;
    }

    public boolean eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE id_producto = ?";

        Connection con = null;
        PreparedStatement ps  = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate(); // devuelve tmb el num de filas eliminadas

            return filasEliminadas > 0; // true = se eliminó, false = no

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        } finally {
            cerrarRecursos(null, ps, con);
        }

        return false;
    }

    public List<Producto> buscarPorMarca(String marca) {

        String sql = select + "WHERE m.nombre LIKE ? AND p.activo = 1 ORDER BY p.precio";

        List<Producto> lista = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps  = null;
        ResultSet rs  = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            // filtrar por nombre de marca
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + marca + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar por marca: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, ps, con);
        }

        return lista;
    }

    private void cerrarRecursos(ResultSet rs, PreparedStatement ps, Connection con) {
        try { if (rs  != null) rs.close();  } catch (SQLException e) { }
        try { if (ps  != null) ps.close();  } catch (SQLException e) { }
        try { if (con != null) con.close(); } catch (SQLException e) { }
    }
}