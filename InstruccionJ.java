/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class InstruccionJ extends Instruccion
{
  private String tipoIns = "Instrucción de Tipo J";
  private String operador;
  private String binario;
  private int inmediato;
  private int codigo;
  private String nemonico;

  public void setNemonico ( String nem ){
    nemonico = nem;
  }

  public String getNemonico(){
    return nemonico;
  }


  public InstruccionJ() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo( int c ) {
    codigo = c;
  }

  public void setInmediato ( int i ) {
    inmediato = i;

  }

  public int getInmediato ( ) {
    return inmediato;
  }

  public String getTipoIns(){
    return tipoIns;
  }

  public String getOperador(){
    return operador;
  }
  public void setOperador(String op){
    operador = op;
  }
  public String getBinario(){
    return binario + " " + completaBinario( Integer.toBinaryString(inmediato), 26 );
  }
  public void setBinario(int bin) {
    binario = completaBinario(Integer.toBinaryString(bin), 6) + " ";
  }

  public String getRegistros() {
    return "op = " + getOperador()  + "  | Dir/Inm = " + inmediato + "|";
  }

}
