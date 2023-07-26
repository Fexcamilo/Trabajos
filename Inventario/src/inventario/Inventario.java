
package inventario;

import Vista.Menu;

public class Inventario {

    public static void main(String[] args) {
               java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu menu = new Menu();
                menu.show();
            }
        });
            }
    
}
