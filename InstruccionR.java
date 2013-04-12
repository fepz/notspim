/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class InstruccionR
    extends Instruccion {
  private String tipoIns = "Instrucción de Tipo R";
  private String operador;
  private String binario;
  private int rd, rs, rt;
  private int inmediato;
  private int codigo;
  private int shamt, funct;
  private String nemonico;

  public void setNemonico(String nem) {
    nemonico = nem;
  }

  public String getNemonico() {
    return nemonico;
  }

  public InstruccionR() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int c) {
    codigo = c;
  }

  public void setInmediato(String i) {
    inmediato = Integer.parseInt(i);
  }

  public int getInmediato() {
    return inmediato;
  }

  public void setShamt(int s) {
    shamt = s;
  }

  public int getShamt() {
    return shamt;
  }

  public void setFunct(int f) {
    funct = f;
  }

  public String getFunct() {
    return completaBinario(Integer.toBinaryString(funct), 6);
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
    return binario + " " + completaBinario(Integer.toBinaryString(rs), 5)
        + " " + completaBinario(Integer.toBinaryString(rt), 5)
        + " " + completaBinario(Integer.toBinaryString(rd), 5)
        + " " + completaBinario(Integer.toBinaryString(shamt), 5)
        + " " + getFunct();
  }

  public void setBinario(int bin) {
    binario = completaBinario(Integer.toBinaryString(bin), 6) + " ";
  }

  public void setRs(String val) {
    rs = Integer.parseInt(val);

  }

  public void setRt(String val) {
    rt = Integer.parseInt(val);

  }

  public void setRd(String val) {
    rd = Integer.parseInt(val);

  }

  public int getRd() {
    return rd;
  }

  public int getRs() {
    return rs;
  }

  public int getRt() {
    return rt;
  }

  public String getRegistros() {
    return "op = " + getOperador() + " | rs = $" + rs +
        "  | rt = $" + rt + "  | rd = $" + rd + " | shamt = " + shamt +
        " | funct  = " + funct + "|";
  }
}