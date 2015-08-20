/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Image;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Blutharsch
 */
public class Padding {
    
      public boolean activo(Object object, JDesktopPane jDesktopPane1) {

        JInternalFrame[] activos = jDesktopPane1.getAllFrames();

        boolean mostrar = true;

        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (object.getClass().isInstance(jDesktopPane1.getComponent(a))) {

                mostrar = false;
            }
        }
        return mostrar;
    }
      
       public static void Centrar(JInternalFrame ia, JDesktopPane Escritorio) {
        int x = (Escritorio.getWidth() / 2) - ia.getWidth() / 2;
        int y = (Escritorio.getHeight() / 2) - ia.getHeight() / 2;
        Escritorio.add(ia);
        ia.setLocation(x, y);
        ia.setVisible(true);
    }
}
