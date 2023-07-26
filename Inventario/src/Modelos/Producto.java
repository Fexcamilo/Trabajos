
package Modelos;

import Vista.Mostrar_productos;
import inventario.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Producto {

    DefaultTableModel tablaproducto = new DefaultTableModel(
            null,
            new String[]
            {
                "codigo", "valor_compra", "valor_venta", "inventario", "descripcion"
            }
    ) {
        Class[] types = new Class[]
        {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean[]
        {
            false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    int codigo;
    String descripcion;
    int valor_compra;
    int valor_venta;
    int inventario;

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValor_compra() {
        return valor_compra;
    }

    public int getValor_venta() {
        return valor_venta;
    }

    public int getInventario() {
        return inventario;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor_compra(int valor_compra) {
        this.valor_compra = valor_compra;
    }

    public void setValor_venta(int valor_venta) {
        this.valor_venta = valor_venta;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public void AgregarProducto(Producto producto) {
        int codigo = producto.getCodigo();
        String descripcion = producto.getDescripcion();
        int valor_compra = producto.getValor_compra();
        int valor_venta = producto.getValor_venta();
        int inventario = producto.getInventario();
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;

        try
        {
            ps = con.prepareCall("INSERT INTO `producto`(`codigo`, `descripcion`, `valor_compra`, `valor_venta`, `inventario`) VALUES (?,?,?,?,?)");
            ps.setInt(1, codigo);
            ps.setString(2, descripcion);
            ps.setInt(3, valor_compra);
            ps.setInt(4, valor_venta);
            ps.setInt(5, inventario);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "producto registrado");

        } catch (SQLException ex)
        {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
    }

    public void mostrar_productos(Mostrar_productos ventana) {
        Conexion c = new Conexion();

        String Sql = "SELECT * FROM `producto`";
        try
        {
            java.sql.CallableStatement cs = c.conectar().prepareCall(Sql);
            cs.execute();
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                tablaproducto.addRow(new String[]
                {
                    rs.getString("codigo"),
                    rs.getString("valor_compra"),
                    rs.getString("valor_venta"),
                    rs.getString("inventario"),
                    rs.getString("descripcion")
                });
                ventana.getProductos().setModel(tablaproducto);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
    }

    public void ActualizarProducto(Producto producto) {
        int codigo = producto.getCodigo();
        String descripcion = producto.getDescripcion();
        int valor_compra = producto.getValor_compra();
        int valor_venta = producto.getValor_venta();
        int inventario = producto.getInventario();
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;

        try
        {
            ps = con.prepareCall("UPDATE `producto` SET `descripcion`=?,`valor_compra`=?,`valor_venta`=?,`inventario`=? WHERE codigo=" + codigo);
            ps.setString(1, descripcion);
            ps.setInt(2, valor_compra);
            ps.setInt(3, valor_venta);
            ps.setInt(4, inventario);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "producto actualizado");

        } catch (SQLException ex)
        {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
    }

    public void EliminarProducto(Producto producto) {
        int codigo = producto.getCodigo();
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;

        try
        {
            ps = con.prepareCall("DELETE * FROM `producti` WHERE codigo=(?)" + codigo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "producto eliminado");

        } catch (Exception ex)
        {
        }
        c.desconectar();
    }

   
}
