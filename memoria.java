/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class memoria {
  private String nomRegs[] = {
      "r0", "at", "v0", "v1", "a0", "a1", "a2", "a3",
      "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7",
      "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7",
      "t8", "t9", "k0", "k1", "gp", "sp", "s8", "ra"};

  private int registros[] = new int[32];
  private Instruccion ramI[] = new Instruccion[256];
  private byte ramD[] = new byte[512];
  private int libreI = 0;
  private int cp = 0, HI = 0, LO = 0;

  private int initD = 1280;
  private int initI = 0;

  /* Setea el Contador de Programa */
  public void setCp(int cont) {
    cp = cont / 4;
  }

  /* Obtiene el contador de programa */
  public int getCp() {
    return cp;
  }

  /* Setea el registro HI */
  public void setHi(int h) {
    HI = h;
  }

  /* Obtiene el registro HI */
  public int getHi() {
    return HI;
  }
  /* Setea el registro LO */
  public void setLo(int l) {
    LO = l;
  }

  /* Obtiene el registro LO */
  public int getLo() {
    return LO;
  }


  public memoria() {
    borrar();
  }

  // Borra la memoria y los registros
  public void borrar() {
    for (int i = 0; i < registros.length; i++) {
      registros[i] = 0;
    }
   borrarRamI();
    for (int i = 0; i < ramD.length; i++) {
      ramD[i] = 0;
    }
    libreI = 0;
    cp = 0;
    HI = 0;
    LO = 0;
  }

  public void borrarRamI () {
    for (int i = 0; i < ramI.length; i++) {
    ramI[i] = null;
  }

  }
  /* Setea el valor de un registro */
  public void setRegistro(int numReg, int valor) {
    if ((numReg != 0) && (numReg != 1))
      registros[numReg] = valor;
  }

  /* Obtiene el valor de un registro */
  public int getRegistro(int numReg) {
    return registros[numReg];
  }

  /* Devuelve un String representando a los Registros */
  public String getRegistros() {
    String salida = "";
    int i;
    salida += "PC=  " + completa(Integer.toHexString(getCp() * 4), 8) +
        " HI= " + completa(Integer.toHexString(getHi()), 8) +
        " LO= " + completa(Integer.toHexString(getLo()), 8) + "\n";
    for (i = 1; i <= registros.length; i++) {
      salida += "R" + (i - 1) + ( (i > 10) ? "" : " ") + "(" + nomRegs[i - 1] +
          ")= " + completa(Integer.toHexString(getRegistro(i - 1)), 8) + " \t";
      if ( (i % 3) == 0) {
        salida += '\n';
      }
    }
    return salida;
  }

  /* Devuelve un String representando a la memoria de Datos */
  public String getMemoriaD() {
    String salida = "";
    int i = 0, j, k;
    salida = "(0x500)";

    while (i < ramD.length) {
      salida += " \t0x";
      for (j = (i + 3); j >= i; j--) {
        k = ramD[j] & 0xFF;
        salida += completa(Integer.toHexString(k), 2);
      }
      i += 4;
      if ( (i % 16) == 0) {
        salida += "\n(0x" + completa(Integer.toHexString(i+initD), 3) + ")";
      }
    }
    return salida;
  }

  /* Devuelve un String representando a la memoria de Instrucciones */
  public String getMemoriaI() {
    String salida = "";
    int i;
    salida = "(0x000)";
    for (i = 1; i <= ramI.length; i++) {
      if (ramI[i - 1] != null) {
        // Se castea un long para evitar un desborde del int
        int e = (int) Long.parseLong(ramI[i -
                                     1].getBinario().replaceAll(" ", ""), 2);
        salida += "\t0x" + completa(Integer.toHexString(e), 8);
      }
      else {
        salida += "\t0x00000000";
      }
      if (i != 256) {
        salida += '\n' + "(0x" + completa(Integer.toHexString((i * 4)+initI), 3) + ")";
      }
    }
    return salida;
  }

  /* Escribe una instrucción en la memoria de Instrucciones */
  public void setRamI(Instruccion instruccion) {
    ramI[libreI++] = instruccion;
  }

  /* Exclusivamente para BEQ, que necesita hacer el calculo relativo */
  public int getLibreI () {
    return libreI;
  }

  public void borrarLibreI () {
    libreI = 0;
  }

  /* Obtiene la instrucción a la que apunta el contador de programa */
  public Instruccion getInstruccion() {
    if (ramI[cp] != null) {
      return ramI[cp++];
    }
    return null;
  }

  /* Lee una palabra en la memoria en la dirección especificada */
  private int fetch(int addr) {
    int b1 = ramD[addr + 3] & 0x00FF;
    int b2 = ramD[addr + 2] & 0x00FF;
    int b3 = ramD[addr + 1] & 0x00FF;
    int b4 = ramD[addr] & 0x00FF;
    return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
  }

  /* Metodo público para utilizar fetch */
  public int leerPalabra(int address) {
    return fetch(address - initD);
  }

  /* Lee un byte de la memoria */
  public byte leerByte(int address) {
    return ramD[address - initD];
  }

  /* Escribe una palabra en la memoria */
  public void escribirPalabra(int address, int value) {
    address -= initD;
    ramD[address + 3] = (byte) (value >> 24);
    ramD[address + 2] = (byte) (value >> 16);
    ramD[address + 1] = (byte) (value >> 8);
    ramD[address] = (byte) (value);
  }

  /* Escribe un byte en la memoria */
  public void escribirByte(int address, byte value) {
    ramD[address - initD] = value;
  }

  /* Completa con ceros a la izquierda */
  public String completa(String bin, int max) {
    String temp = "";
    for (int i = 0; i < max - bin.length(); i++) {
      temp += '0';
    }
    temp += bin;
    return temp;
  }
}
