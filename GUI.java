import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.io.*;

/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class GUI
    extends JFrame {

  File archivoFuente;
  BufferedReader input;
  BufferedWriter output;
  InfoInstrucciones Info = new InfoInstrucciones();
  memoria Memoria = new memoria();

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jButton3 = new JButton();
  JPanel jPanel2 = new JPanel();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenu jMenu3 = new JMenu();
  JMenuItem jMenuItem6 = new JMenuItem();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JLabel jLabel5 = new JLabel();
  JScrollPane jScrollPane3 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JTextArea jTextArea2 = new JTextArea();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  Border border1;
  Border border2;
  Border border3;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton jButton4 = new JButton();
  JLabel jLabel6 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea3 = new JTextArea();
  JScrollPane jScrollPane4 = new JScrollPane();
  JTextArea jTextArea4 = new JTextArea();
  String a[] = {
      "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9",
      "R10", "R11", "R12", "R13", "R14", "R15", "R16", "R17", "R18", "R19",
      "R20", "R21", "R22", "R23", "R24", "R25", "R26", "R27", "R28", "R29",
      "R30", "R31", "PC"};
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JComboBox jComboBox1 = new JComboBox(a);
  String b[] = new String[512];
  JComboBox jComboBox2 = new JComboBox();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  TitledBorder titledBorder4;
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem jMenuItem9 = new JMenuItem();
  JMenuItem jMenuItem10 = new JMenuItem();

  public GUI() throws HeadlessException {
    try {
      Compilar.setMemoria(Memoria);
      Ejecutar.setMemoria(Memoria);
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    for (int i = 0; i < 512; i++) {
      b[i] = Integer.toHexString(i+1280);
      jComboBox2.addItem(b[i]);
    }

    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    border1 = BorderFactory.createEmptyBorder(0, 20, 0, 0);
    border2 = BorderFactory.createCompoundBorder(BorderFactory.
                                                 createEtchedBorder(Color.white,
        new Color(156, 156, 158)), BorderFactory.createEmptyBorder(0, 10, 0, 0));
    border3 = BorderFactory.createCompoundBorder(BorderFactory.
                                                 createMatteBorder(6, 6, 6, 6,
        new Color(187, 218, 240)), titledBorder1);
    titledBorder4 = new TitledBorder("");
    this.getContentPane().setLayout(borderLayout1);
    jButton1.setBackground(new Color(122, 150, 223));
    jButton1.setEnabled(false);
    jButton1.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton1.setText(">>");
    jButton1.addActionListener(new Frame2_jButton1_actionAdapter(this));
    jButton2.setBackground(new Color(122, 150, 223));
    jButton2.setEnabled(false);
    jButton2.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton2.setFocusPainted(true);
    jButton2.setText(" X ");
    jButton2.addActionListener(new Frame2_jButton2_actionAdapter(this));
    jPanel1.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jButton3.setBackground(new Color(122, 150, 223));
    jButton3.setEnabled(false);
    jButton3.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton3.setText(" > ");
    jButton3.addActionListener(new Frame2_jButton3_actionAdapter(this));
    jPanel2.setBackground(new Color(187, 218, 240));
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setLayout(gridBagLayout1);
    jMenu1.setText("Archivo");
    jMenuItem1.setText("Abrir");
    jMenuItem1.addActionListener(new GUI_jMenuItem1_actionAdapter(this));
    jMenuItem2.setText("Guardar");
    jMenuItem2.addActionListener(new GUI_jMenuItem2_actionAdapter(this));
    jMenu2.setText("Ejecutar");
    jMenuItem3.setText("Instrucción");
    jMenuItem3.addActionListener(new GUI_jMenuItem3_actionAdapter(this));
    jMenuItem4.setText("Todo");
    jMenuItem4.addActionListener(new GUI_jMenuItem4_actionAdapter(this));
    jMenuItem5.setText("Cancelar");
    jMenuItem5.addActionListener(new GUI_jMenuItem5_actionAdapter(this));
    jMenu3.setText("Ayuda");
    this.getContentPane().setBackground(new Color(187, 218, 240));
    this.setJMenuBar(jMenuBar1);
    jMenuItem6.setText("Acerca de not(SPIM)");
    jMenuItem6.addActionListener(new GUI_jMenuItem6_actionAdapter(this));
    jLabel1.setBackground(Color.lightGray);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel1.setForeground(new Color(0, 97, 152));
    jLabel1.setMaximumSize(new Dimension(171, 21));
    jLabel1.setMinimumSize(new Dimension(171, 15));
    jLabel1.setOpaque(false);
    jLabel1.setPreferredSize(new Dimension(171, 15));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("TIPO DE INSTRUCCION");
    jLabel1.setVerticalAlignment(SwingConstants.CENTER);
    borderLayout1.setHgap(5);
    borderLayout1.setVgap(0);
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel4.setMaximumSize(new Dimension(40, 15));
    jLabel4.setMinimumSize(new Dimension(40, 15));
    jLabel4.setPreferredSize(new Dimension(40, 15));
    jLabel4.setRequestFocusEnabled(true);
    jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel4.setHorizontalTextPosition(SwingConstants.LEFT);
    jLabel4.setText("REGISTROS:");
    jLabel4.setVerticalAlignment(SwingConstants.CENTER);
    jLabel4.setVerticalTextPosition(SwingConstants.CENTER);
    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.
                                            VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane2.setRowHeader(null);
    jScrollPane2.setViewportBorder(null);
    jScrollPane2.setEnabled(true);
    jScrollPane2.setBorder(BorderFactory.createEtchedBorder());
    jScrollPane2.setDoubleBuffered(false);
    jScrollPane2.setMinimumSize(new Dimension(25, 15));
    jScrollPane2.setOpaque(true);
    jScrollPane2.setRequestFocusEnabled(true);
    jScrollPane2.setToolTipText("");
    jScrollPane2.setVerifyInputWhenFocusTarget(true);
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel5.setMaximumSize(new Dimension(40, 15));
    jLabel5.setMinimumSize(new Dimension(40, 15));
    jLabel5.setPreferredSize(new Dimension(40, 15));
    jLabel5.setRequestFocusEnabled(true);
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel5.setText("MEMORIA DE DATOS:");
    jLabel5.setVerticalAlignment(SwingConstants.CENTER);
    jLabel5.setVerticalTextPosition(SwingConstants.CENTER);
    jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12));
    jTextArea1.setAlignmentX( (float) 0.5);
    jTextArea1.setBorder(null);
    jTextArea1.setEditable(false);
    jTextArea1.setTabSize(4);
    jTextArea1.setText("");
    jTextArea2.setEditable(false);
    jTextArea2.setTabSize(4);
    jTextArea2.setText("");
    jScrollPane3.setBorder(BorderFactory.createEtchedBorder());
    jButton4.setBackground(new Color(122, 150, 223));
    jButton4.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton4.setHorizontalAlignment(SwingConstants.TRAILING);
    jButton4.setText("Listo");
    jButton4.addActionListener(new Frame2_jButton4_actionAdapter(this));
    jPanel1.setBackground(new Color(187, 218, 240));
    jLabel6.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel6.setText("MEMORIA DE INSTRUCCIONES:");
    jTextArea4.setBorder(null);
    jTextArea4.setTabSize(4);
    jTextArea4.setText("");
    jScrollPane4.setBorder(BorderFactory.createEtchedBorder());
    jTextArea3.setText("");
    jTextField1.setMinimumSize(new Dimension(6, 21));
    jTextField1.setToolTipText("");
    jTextField1.setText("");
    jTextField1.addActionListener(new Frame2_jTextField1_actionAdapter(this));
    jTextField2.setSelectionStart(0);
    jTextField2.setText("");
    jTextField2.addActionListener(new Frame2_jTextField2_actionAdapter(this));
    jComboBox1.setToolTipText("Seleccione un registro");
    jComboBox1.setPopupVisible(false);
    jComboBox1.setSelectedIndex(0);
    jComboBox1.setSelectedItem(null);
    jComboBox2.setSelectedIndex(0);
    jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setBackground(new Color(187, 218, 240));
    jPanel3.setMaximumSize(new Dimension(171, 21));
    jPanel3.setMinimumSize(new Dimension(171, 1));
    jPanel3.setPreferredSize(new Dimension(10, 0));
    jPanel3.setLayout(borderLayout2);
    jMenuItem7.setText("Nuevo");
    jMenuItem7.addActionListener(new GUI_jMenuItem7_actionAdapter(this));
    jMenuItem8.setText("Salir");
    jMenuItem8.addActionListener(new GUI_jMenuItem8_actionAdapter(this));
    jMenuItem9.setText("Compilar");
    jMenuItem9.addActionListener(new GUI_jMenuItem9_actionAdapter(this));
    jMenuItem10.setText("Temas de Ayuda");
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    this.setTitle("Bienvenido a not(SPIM)");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(new Dimension(780, 570));
    jPanel1.add(jButton4, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jLabel1, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 244, 25));
    jPanel2.add(jLabel4, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 230, 15));
    jPanel2.add(jScrollPane2, new GridBagConstraints(1, 4, 3, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 5), 411, 41));
    jScrollPane2.getViewport().add(jTextArea1, null);
    jPanel2.add(jScrollPane3, new GridBagConstraints(1, 6, 3, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 5), 0, 0));
    jPanel2.add(jLabel5, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 230, 15));
    jPanel2.add(jLabel6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 3, 0, 3), 0, 0));
    jScrollPane3.getViewport().add(jTextArea2, null);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenuBar1.add(jMenu3);
    jMenu1.add(jMenuItem7);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu1.addSeparator();
    jMenu1.add(jMenuItem8);
    jMenu2.add(jMenuItem9);
    jMenu2.addSeparator();
    jMenu2.add(jMenuItem3);
    jMenu2.add(jMenuItem4);
    jMenu2.add(jMenuItem5);
    jMenu3.add(jMenuItem10);
    jMenu3.add(jMenuItem6);
    jPanel2.add(jScrollPane1, new GridBagConstraints(0, 0, 1, 5, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
        new Insets(5, 3, 0, 3), 280, 240));
    jScrollPane1.getViewport().add(jTextArea3, null);
    jPanel2.add(jScrollPane4, new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
        new Insets(0, 3, 0, 3), 280, 41));
    jPanel2.add(jTextField1, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
        , GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(0, 5, 0, 5), 75, 0));
    jPanel2.add(jComboBox1, new GridBagConstraints(2, 3, 1, 1, 1.0, 0.0
        , GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 25, 0));
    jPanel2.add(jTextField2, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0
        , GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(0, 5, 0, 5), 75, 0));
    jPanel2.add(jComboBox2, new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0
        , GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 25, 0));
    jPanel2.add(jPanel3, new GridBagConstraints(1, 1, 3, 2, 1.0, 1.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.BOTH,
                                                new Insets(0, 0, 0, 0), 250, 0));
    jScrollPane4.getViewport().add(jTextArea4, null);

    jTextArea1.setText(Memoria.getRegistros());
    jTextArea2.setText(Memoria.getMemoriaD());
    jTextArea4.setText(Memoria.getMemoriaI());
    jPanel3.add(Info, BorderLayout.CENTER);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    jButton4.setEnabled(true);
    jButton1.setEnabled(false);
    jButton2.setEnabled(false);
    jButton3.setEnabled(false);

    Instruccion instruccion;

    while ( (instruccion = Memoria.getInstruccion()) != null) {

      if (instruccion instanceof InstruccionR) {
        Ejecutar.ejecutar( (InstruccionR) instruccion);
      }
      if (instruccion instanceof InstruccionI) {
        Ejecutar.ejecutar( (InstruccionI) instruccion);
      }
      if (instruccion instanceof InstruccionJ) {
        Ejecutar.ejecutar( (InstruccionJ) instruccion);
      }

    }
    jTextArea1.setText(Memoria.getRegistros());
    jTextArea1.setCaretPosition(0);
    jTextArea2.setText(Memoria.getMemoriaD());
    jTextArea2.setCaretPosition(0);
    Memoria.borrar();
    jTextArea3.setEditable(true);

  }

  void jButton4_actionPerformed(ActionEvent e) {

    jTextArea3.setEditable(false);
    Memoria.borrarLibreI();
    Memoria.borrarRamI();
    Memoria.setCp(0);
    Compilar.borraEtiquetas();
    Compilar.fuente(jTextArea3.getText());
    jTextArea4.setText(Memoria.getMemoriaI());
    jTextArea4.setCaretPosition(0);
    jTextArea2.setText(Memoria.getMemoriaD());
    jTextArea2.setCaretPosition(0);
    jTextArea1.setText(Memoria.getRegistros());
    jTextArea1.setCaretPosition(0);
    jLabel1.setText("TIPO DE INSTRUCCION");
    Info.setBinario(" ");
    Info.setDescripcion("|");
    Info.setNemonico("");
    jButton4.setEnabled(false);
    jButton1.setEnabled(true);
    jButton2.setEnabled(true);
    jButton3.setEnabled(true);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    Instruccion instruccion = Memoria.getInstruccion();
    if (instruccion != null) {
      jLabel1.setText(instruccion.getTipoIns());
      if (instruccion instanceof InstruccionR) {
        Ejecutar.ejecutar( (InstruccionR) instruccion);
      }
      if (instruccion instanceof InstruccionI) {
        Ejecutar.ejecutar( (InstruccionI) instruccion);
      }
      if (instruccion instanceof InstruccionJ) {
        Ejecutar.ejecutar( (InstruccionJ) instruccion);
      }
      jTextArea1.setText(Memoria.getRegistros());
      jTextArea1.setCaretPosition(0);
      jTextArea2.setText(Memoria.getMemoriaD());
      jTextArea2.setCaretPosition(0);
      Info.setBinario(instruccion.getBinario());
      Info.setDescripcion(instruccion.getRegistros());
      Info.setNemonico(instruccion.getNemonico());

    }
    else {
      Memoria.borrar();
      jButton3.setEnabled(false);
      jButton1.setEnabled(false);
      jButton2.setEnabled(false);
      jButton4.setEnabled(true);
      jTextArea3.setEditable(true);
    }
  }

  /* Modifica un registro seleccionado por el usuario con un valor
   hexadecimal ingresado en el jComboBox1 */
  void jTextField1_actionPerformed(ActionEvent e) {
    try {
      Memoria.setRegistro(jComboBox1.getSelectedIndex(),
                          Integer.parseInt(e.getActionCommand().trim(), 16));
      jTextArea1.setText(Memoria.getRegistros());
      jTextArea1.setCaretPosition(jComboBox1.getSelectedIndex());
    }
    catch (NumberFormatException excepcionNumero) {
      JOptionPane.showMessageDialog(null,
          "El número no es hexadecimal o excede el tamaño máximo",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
    catch (ArrayIndexOutOfBoundsException indiceException) {
      JOptionPane.showMessageDialog(null,
                                    "Falta indicar el registro a modificar",
                                    "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /* Ingresa en la dirección seleccionada por el usuario el valor
   hexadecimal ingresado en el jComboBox2 */
  void jTextField2_actionPerformed(ActionEvent e) {
    try {
      int temp = Integer.parseInt(e.getActionCommand().trim(), 16);
      /* Acomodo un byte */
      byte tempb;
      if (temp < 0x100) {
        tempb = (byte) temp;
        Memoria.escribirByte(jComboBox2.getSelectedIndex()+1280, tempb);
      }
      /* Acomodo un halfword */
      if (temp < 0x10000) {
        tempb = (byte) (temp & 0xFF);
        Memoria.escribirByte(jComboBox2.getSelectedIndex()+1280, tempb);
        temp >>= 8;
        tempb = (byte) (temp & 0xFF);
        Memoria.escribirByte(jComboBox2.getSelectedIndex() + 1281, tempb);
      }
      /* Acomdo una palabra */
      if ( temp >= 0x10000 ) {
        Memoria.escribirPalabra(jComboBox2.getSelectedIndex() + 1280, temp);
      }
      jTextArea2.setText(Memoria.getMemoriaD());
      jTextArea2.setCaretPosition(0);
    }
    /* Si el número es demasiado grande o no esta en hexa */
    catch (NumberFormatException excepcionNumero) {
      JOptionPane.showMessageDialog(null,
          "El número no es hexadecimal o excede el tamaño máximo",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    jTextArea3.setEditable(true);
    jButton2.setEnabled(false);
    jButton1.setEnabled(false);
    jButton3.setEnabled(false);
    jButton4.setEnabled(true);
    Memoria.borrar();
    jTextArea2.setText(Memoria.getMemoriaD());
    jTextArea2.setCaretPosition(0);
    jTextArea1.setText(Memoria.getRegistros());
    jTextArea1.setCaretPosition(0);
    jLabel1.setText("TIPO DE INSTRUCCION");
  }

  void jMenuItem8_actionPerformed(ActionEvent e) {

    try {
      if (input != null) {
        input.close();
      }
      if (output != null) {
        output.close();
      }

      System.exit(0);
    }

    // process exceptions from closing file
    catch (IOException ioException) {
      JOptionPane.showMessageDialog(this, "Error closing file",
                                    "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    StringBuffer salida = abrirArchivoFuente(archivoFuente);
    if (salida != null) {
      jTextArea3.setText(salida.toString());
    }
  }

  private StringBuffer abrirArchivoFuente(File name) {

    StringBuffer buffer = new StringBuffer();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int result = fileChooser.showOpenDialog(this);

    // if user clicked Cancel button on dialog, return
    if (result == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    name = fileChooser.getSelectedFile();

    if (name == null || name.getName().equals("")) {
      JOptionPane.showMessageDialog(this, "Invalid File Name",
                                    "Invalid File Name",
                                    JOptionPane.ERROR_MESSAGE);
    }
    else {
      // append contents of file to outputArea
      try {
        input = new BufferedReader(
            new FileReader(name));

        String text;
        while ( (text = input.readLine()) != null) {
          buffer.append(text + "\n");
        }
      }
      // process file processing problems
      catch (IOException ioException) {
        JOptionPane.showMessageDialog(this, "FILE ERROR",
                                      "FILE ERROR", JOptionPane.ERROR_MESSAGE);
      }
    }
    return buffer;
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    guardarArchivoFuente(archivoFuente);

  }

  private int guardarArchivoFuente(File name) {

    StringBuffer buffer = new StringBuffer();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int result = fileChooser.showSaveDialog(this);

    // if user clicked Cancel button on dialog, return
    if (result == JFileChooser.CANCEL_OPTION) {
      return 1;
    }

    name = fileChooser.getSelectedFile();

    if (name == null || name.getName().equals("")) {
      JOptionPane.showMessageDialog(this, "Invalid File Name",
                                    "Invalid File Name",
                                    JOptionPane.ERROR_MESSAGE);
    }
    else {
      // append contents of file to outputArea
      try {
        output = new BufferedWriter(new FileWriter(name));
        output.write(jTextArea3.getText());
        output.flush();
      }
      // process file processing problems
      catch (IOException ioException) {
        JOptionPane.showMessageDialog(this, "FILE ERROR",
                                      "FILE ERROR", JOptionPane.ERROR_MESSAGE);
      }
    }
    return 0;
  }

  /*Compila llamando al actionPerformed del boton 4 "Listo" */
  void jMenuItem9_actionPerformed(ActionEvent e) {
    jButton4_actionPerformed(e);
  }

  /*Ejecuta una instruccion llamando al actionPerformed del boton 3 ">" */
  void jMenuItem3_actionPerformed(ActionEvent e) {
    jButton3_actionPerformed(e);
  }

  /*Ejecuta todo llamando al actionPerformed del boton 1 ">>" */
  void jMenuItem4_actionPerformed(ActionEvent e) {
    jButton1_actionPerformed(e);
  }

  /*Cancela la ejecucion llamando al actionPerformed del boton 2 "X" */
  void jMenuItem5_actionPerformed(ActionEvent e) {
    jButton2_actionPerformed(e);
  }

  void jMenuItem6_actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(this,
        "\nnot(SPIM) v3.11\nSencillo simulador de instrucciones MIPS\n" +
        "Copyright (c) 2003 Bonti&&Paez.\nTodos los derechos reservados\n\n" +
        "Autores:\n - Fernando Bonti (fernandobonti@yahoo.com.ar)\n" +
        " - Francisco Paez (fp@ubbi.com)\n\n" +
        "Hecho para la:\nUniversidad Nacional de la Patagonia San Juan Bosco\n\n",
        "Acerca de not(SPIM)",
        JOptionPane.INFORMATION_MESSAGE);
  }

  void jMenuItem7_actionPerformed(ActionEvent e) {
    try {
      if (input != null) {
        input.close();
      }
      jTextArea3.setText("");
    }
    catch (IOException ioException) {
      JOptionPane.showMessageDialog(this, "Error closing file",
                                    "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }
}

class Frame2_jButton4_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jButton4_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class Frame2_jButton3_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jButton3_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Frame2_jTextField1_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jTextField1_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class Frame2_jTextField2_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jTextField2_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField2_actionPerformed(e);
  }
}

class Frame2_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jButton1_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Frame2_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  Frame2_jButton2_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class GUI_jMenuItem8_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem8_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem8_actionPerformed(e);
  }

}

class GUI_jMenuItem1_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem1_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class GUI_jMenuItem2_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem2_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem2_actionPerformed(e);
  }
}

class GUI_jMenuItem9_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem9_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem9_actionPerformed(e);
  }
}

class GUI_jMenuItem3_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem3_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem3_actionPerformed(e);
  }
}

class GUI_jMenuItem4_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem4_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem4_actionPerformed(e);
  }
}

class GUI_jMenuItem5_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem5_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem5_actionPerformed(e);
  }
}

class GUI_jMenuItem6_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem6_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem6_actionPerformed(e);
  }
}

class GUI_jMenuItem7_actionAdapter
    implements java.awt.event.ActionListener {
  GUI adaptee;

  GUI_jMenuItem7_actionAdapter(GUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem7_actionPerformed(e);
  }
}
