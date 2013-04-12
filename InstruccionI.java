/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class InstruccionI
    extends Instruccion {
  private String tipoIns = "Instrucción de Tipo I";
  private String operador;
  private String binario;
  private int rs, rt;
  private short inmediato;
  private int codigo;
  private String nemonico;

  public void setNemonico(String nem) {
    nemonico = nem;
  }

  public String getNemonico() {
    return nemonico;
  }

  public InstruccionI() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int c) {
    codigo = c;
  }

  public void setInmediato(String i) {
    // ver a donde poner el try... en Compilar?
    inmediato = Short.parseShort(i);
    //int temp = inmediato;
    // temp &= 0xFFFF;

  }

  public int getInmediato() {
    return inmediato;
  }

  public String getTipoIns() {
    return tipoIns;
  }

  public String getOperador() {
    return operador;
  }

  public void setOperador(String op) {
    operador = op;
  }

  public String getBinario() {
    int temp;
    temp = inmediato;
    temp &= 0x0000FFFF;
    return binario + " " +
                       completaBinario(Integer.toBinaryString(rs), 5)
                       + " " + completaBinario(Integer.toBinaryString(rt), 5)
                       + " " +
                       completaBinario(Integer.toBinaryString(temp), 16).substring(0,16);

  }

  public void setBinario(int bin) {
    binario = completaBinario(Integer.toBinaryString(bin), 6);
  }

  public void setRs(String val) {
    rs = Integer.parseInt(val);

  }

  public void setRt(String val) {
    rt = Integer.parseInt(val);
  }

  public int getRs() {
    return rs;
  }

  public int getRt() {
    return rt;
  }

  public String getRegistros() {
    return "op = " + getOperador() + " | rs = $" + rs +
        "  | rt = $" + rt + "  | Dir/Inm = " + inmediato + "|";
  }
}
