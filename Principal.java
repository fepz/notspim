/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

import java.awt.*;
import javax.swing.UIManager;

public class Principal {
  //Crear la aplicación
  public Principal() {
    GUI frame = new GUI();
    frame.pack();
    frame.setExtendedState(frame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }

  // Método Main
  static public void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new Principal();
  }
}
