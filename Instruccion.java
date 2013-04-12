/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public abstract class Instruccion {

  abstract public int getCodigo();

  abstract public void setCodigo(int c);

  abstract public String getTipoIns();

  abstract public String getOperador();

  abstract public void setOperador(String op);

  abstract public String getBinario();

  abstract public void setNemonico( String nem);

  abstract public String getNemonico();

  abstract public void setBinario(int bin);

  abstract public String getRegistros();

  public String completaBinario(String bin, int max) {
    String temp = "";
    for (int i = 0; i < max - bin.length(); i++) {
      temp += '0';
    }
    temp += bin;
    return temp;
  }
}