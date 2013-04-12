/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class InfoInstrucciones
    extends JPanel {

  private final Color colorNem = new Color(41, 1, 171);
  private final Color colorFu = new Color(137, 100, 254);
  private final Color colorSh = new Color(121, 80, 254);
  private final Color colorInm = new Color(82, 29, 253);
  private final Color colorRd = new Color(55, 2, 226);
  private final Color colorRt = new Color(41, 1, 171);
  private final Color colorRs = new Color(28, 1, 113);
  private final Color colorOp = new Color(17,1,69);

  private final Color colorR[] = {
      colorOp, colorRs, colorRt, colorRd, colorSh, colorFu};
 /* private final Color colorI[] = {
      colorOp, colorRs, colorRt, colorInm};
  private final Color colorJ[] = {
      colorOp, colorInm};
*/
  private final int espaciado[] = {
      40, 110, 170, 230, 290, 350};
  private final int espaciadoR[] = {
      20, 100, 170, 230, 300, 370};
  private final int espaciadoI[] = {
      20, 100, 170, 370};
  private final int espaciadoJ[] = {
      20, 370};

  private StringTokenizer binario = null;
  private StringTokenizer descripcion = null;
  private String nemonico = "";
  private Font fuente3 = new Font("Dialog", Font.BOLD, 16);
  private Font fuente1 = new Font("Dialog", Font.BOLD, 10);
  private Font fuente2 = new Font("Dialog", Font.BOLD, 12);

  public InfoInstrucciones() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void setBinario(String bin) {
    binario = new StringTokenizer(bin, " ");
    repaint();
  }

  public void setDescripcion(String des) {
    descripcion = new StringTokenizer(des, "|");
    repaint();
  }

  public void setNemonico(String nem) {
    nemonico = nem;
    repaint();
  }

  public void paint(Graphics g) {

    super.paint(g);

    g.setFont(fuente1);
    g.drawString("Codigo Binario de la Instruccion: ", 10, 10);

    if (binario != null && descripcion != null) {
      if (binario.countTokens() == 2) {
        for (int i = 0; binario.hasMoreTokens(); i++) {
          g.setColor(colorR[i]);
          g.setFont(fuente3);
          g.drawString(binario.nextToken(), espaciado[i], 40);
          g.setFont(fuente2);
          g.drawString(descripcion.nextToken(), espaciadoJ[i], 80);
        }
        g.drawLine(espaciado[1] + 200, 45, espaciadoJ[1] + 25, 65);
        g.setColor(colorOp);
        g.drawLine(espaciado[0] + 25, 45, espaciadoJ[0] + 25, 65);
        g.setColor(colorNem);
        g.drawString(nemonico, 10, 100);

      }
      if (binario.countTokens() == 4) {
        for (int i = 0; binario.hasMoreTokens(); i++) {
          g.setColor(colorR[i]);
          g.setFont(fuente3);
          g.drawString(binario.nextToken(), espaciado[i], 40);
          g.setFont(fuente2);
          g.drawString(descripcion.nextToken(), espaciadoI[i], 80);
          flecha(espaciado[i] + ( (i == 3) ? 100 : 25), 45, espaciadoI[i] + 25,
                 65, g);
               }
               g.setColor(colorNem);
        g.drawString(nemonico, 10, 100);

      }
      if (binario.countTokens() == 6) {
        for (int i = 0; binario.hasMoreTokens(); i++) {
          g.setColor(colorR[i]);
          g.setFont(fuente3);
          g.drawString(binario.nextToken(), espaciado[i], 40);
          g.setFont(fuente2);
          g.drawString(descripcion.nextToken(), espaciadoR[i], 80);
          flecha(espaciado[i] + 25, 45, espaciadoR[i] + 25, 65, g);
        }
        g.setColor(colorNem);
        g.drawString(nemonico, 10, 100);

      }
    }
  }

  private void flecha(int x1, int y1, int x2, int y2, Graphics g) {
    g.drawLine(x1, y1, x2, y2);
  }

  private void jbInit() throws Exception {
    this.setBackground(new Color(187, 218, 240));
  }
}