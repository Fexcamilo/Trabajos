
package inventario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;


public class Conexion {
    
    String db="inventario";
    String url="jdbc:mysql://localhost:3306/";
    String user="root";
    String password="";
    String driver="com.mysql.cj.jdbc.Driver";
    Connection con;
    
    public Conexion(){
        
        
    }
    
    public Connection conectar(){
        
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url+db, user, password);
            System.out.println("<----------Conexion Exitosa------------>");
        } catch (ClassNotFoundException | SQLException ex) {
             System.out.print("<----------Problemas de conexion------->");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    
      return con;
    }
    
    public void desconectar(){
        
        try {
            con.close();
            System.out.println("<------------Terminando Conexion------------->");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void llenarComboBoxProductos(JComboBox<String> comboBoxProductos) {
        try {
            String consulta = "SELECT descripcion FROM producto";
            PreparedStatement consultaPreparada = (PreparedStatement) con.prepareStatement(consulta);
            ResultSet resultado = (ResultSet) consultaPreparada.executeQuery();

            while (resultado.next()) {
                String descripcion = resultado.getString("descripcion");
                comboBoxProductos.addItem(descripcion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
