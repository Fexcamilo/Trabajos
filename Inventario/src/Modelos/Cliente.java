 
package Modelos;

import inventario.Conexion;
import Vista.RegistroCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente {
    private String nombre;
    private String identificacion;
    private String telefono;
    private String direccion;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void RegistroClientes(Cliente cliente) {
        String nombre = cliente.getNombre();
        String identificacion = cliente.getIdentificacion();
        String telefono = cliente.getTelefono();
        String direccion = cliente.getDireccion();
        
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;

        try
        {
            ps = con.prepareCall("INSERT INTO `cliente`(nombre, Identificacion, telefono, direccion) VALUES (?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, identificacion);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "registro exitoso");

        } catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        c.desconectar();
    }
    
    
    public void EliminarCliente(Cliente cliente){
        String identificacion = cliente.getIdentificacion();
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall("DELETE FROM cliente WHERE Identificacion="+identificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "usuario eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
    }
    
    public void ActualizarCliente(Cliente cliente){
        String identificacion = cliente.getIdentificacion();
        String nombre =  cliente.getNombre();
        String telefono = cliente.getTelefono();
        String direccion = cliente.getDireccion();
        Conexion c = new Conexion();
        Connection con;
        con = c.conectar();
        PreparedStatement ps;
        
        try {
            ps = con.prepareCall("UPDATE `cliente` SET `nombre`=(?),`telefono`=(?),`direccion`=(?) WHERE Identificacion="+identificacion);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, direccion);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente Actualizado");
   
            
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        c.desconectar();
        
        
    }

    

}
