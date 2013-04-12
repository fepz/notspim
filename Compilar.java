/* 5/12/03 llevamos 459 lineas.*/
/* 9/12/03 llevamos 574 lineas.*/
/* 12/12/03 llevamos 994 lineas.*/
/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

import java.util.*;

public class Compilar {

  private static String Inst[] = {
      "add", "addi", "sub", "lw", "sw", "j", "beq", "lui", "jal", "jr", "lb",
      "sb", "lh", "sh", "and", "or", "addu", "subu", "addiu", "andi", "ori",
      "sll", "srl", "mult", "div", "multu", "divu", "mfhi", "mflo", "slt",
      "slti", "sltu", "sltiu"};
  private static int BinOp[] = {
      0, 8, 0, 35, 43, 2, 4, 15, 3, 0, 32, 40, 33, 41, 0, 0, 0, 0, 9,
      12, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 11};
  private static int funct[] = {
      32, 0, 34, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 36, 37, 33, 35, 0, 0, 0,
      0, 2, 24, 26, 25, 27, 16, 18, 42, 0, 43, 0};
  private static String nemonico[] = {
      "$rd <= $rs + $rt", // add
      "$rt <= $rs + Inm", //addi
      "$rd <= $rs - rt", //sub
      "$rs <= Memoria[ $rt + Inm ] (word)", //lw
      "Memoria[ $rt + Inm ] <= $s (word)", //sw
      "Ir a Inm", //j
      "si ($rt == $rs) Ir a PC + 4 + Inm", //beq
      "$rs <= Inm * (2 ** 16)", //lui
      "($31 <= PC + 4) e Ir a Inm", //jal
      "Ir a $31", //jr
      "$rs <= Memoria[ $rt + Inm ] (byte)", //lb
      "Memoria[ $rt + Inm ] <= $rs (byte)", //sb
      "$rs <= Memoria[ $rt + Inm ] (halfword)", //lh
      "Memoria[ $rt + Inm ] <= $rs (halfword)", //sh
      "$rd <= $rt AND $rs", //and
      "$rd <= $rt OR $rs", //or
      "$rd <= $rt + $rs (suma absoluta)", //addu
      "$rd <= $rt + $rs (resta absoluta)", //subu
      "$rt <= $rs + Inm (Inm absoluto)", //addiu
      "$rt <= $rs AND Inm", //andi
      "$rt <= $rs Or  Inm", //ori
      "$rd <= $rt << Inm", //sll
      "$rd <= $rt >> Inm", //srl
      "HI, LO <= $rs * $rt", //mult
      "HI <= $rs / $rt, LO <= $rs MOD $rt", //div
      "HI, LO <= $rs * $rt (absoluto)", //mult
      "HI <= $rs / $rt, LO <= $rs MOD $rt (absoluto)", //div
      "$rd <= HI", //mfhi
      "$rd <= LO", //mflo
      "Si ($rs < $rt) $rd <= 1, sino $rd <= 0", //slt
      "Si ($rs < Inm) $rt <= 1, sino $rt <= 0", //slti
      "Si ($rs < $rt) $rd <= 1, sino $rd <= 0 (absoluto)", //sltu
      "Si ($rs < Inm) $rt <= 1, sino $rt <= 0 (absoluto)" //sltui
  };

  private static String labelStr[] = new String[256];
  private static int labelInt[] = new int[labelStr.length];

  private static memoria Mem;

  public Compilar() {
  }

  public static void setMemoria(memoria Memoria) {
    Mem = Memoria;
  }

  public static void borraEtiquetas() {
    for (int i = 0; i < labelStr.length; i++) {
      labelStr[i] = "";
    }
  }

  private static void buscaLabel(StringTokenizer tokens) {
    int cont = 0, pri;
    String linea, label;
    while (tokens.hasMoreTokens()) {
      linea = tokens.nextToken().toString().trim();
      if ( (pri = linea.indexOf(":")) != -1) {
        label = linea.substring(0, pri).trim();
        labelInt[cont] = cont * 4;
        labelStr[cont] = label;
      }
      else if ((linea.length() > 0) && (linea.charAt(0) != '#')) {
        cont++;
      }
    }
  }

  private static int retornaLabel(String label) {
    int cont = 0;
    while ( (labelStr[cont].compareToIgnoreCase(label)) != 0) {
      cont++;
    }
    return labelInt[cont];
  }

  public static void fuente(String tokens) {
    StringTokenizer codigob = new StringTokenizer(tokens, "\n");
    StringTokenizer codigo = new StringTokenizer(tokens, "\n");
    String cadena;
    int comienzo;
    int fin;

    buscaLabel(codigob);

    while (codigo.hasMoreTokens()) {
      cadena = codigo.nextToken().trim();
      // Indica el comienzo de la instruccion
      if ( (comienzo = cadena.indexOf(":")) == -1) {
        comienzo = 0;
      }
      else {
        comienzo++;
        // Indica el final de la instruccion (comienzo del comentario)
      }
      if ( (fin = cadena.indexOf("#")) == -1) {
        fin = cadena.length();
      }
      cadena = cadena.substring(comienzo, fin).trim();
      if (cadena.length() > 0) {
        compilarInstruccion(cadena);
      }
    }
  }

  private static void compilarInstruccion(String instruccion) {
    String op = "";
    String temp;
    int numRegs = 0;
    int indice = 0;
    int p = 0;
    int cont, cont2;
    String regs[] = new String[3];
    InstruccionR InstR;
    InstruccionI InstI;
    InstruccionJ InstJ;

    /* Obtenemos el operador */
    int esp = instruccion.indexOf(" ");
    if (esp == -1) {
      esp = 100;
    }
    int tab = instruccion.indexOf("\t");
    if (tab == -1) {
      tab = 100;
    }
    op = instruccion.substring(0, (esp < tab) ? esp : tab);
    /* Contamos cuantos registros usa la instruccion */
    numRegs = numVeces(instruccion, '$');

    /* Obtenemos el indice del operador en el arreglo Inst */while (indice <
        Inst.length) {
      if ( (Inst[indice].compareToIgnoreCase(op)) == 0) {
        p = indice;
      }
      indice++;
    }

    /* Sacamos los espacios */
    instruccion = instruccion.replaceAll(" ", "");
    instruccion = instruccion.replaceAll("\t", "");
    instruccion += " ";

    switch (numRegs) {
      /* Instrucciones con 3 registros */
      case 3:
        InstR = new InstruccionR();
        cargaBasicos(InstR, op, BinOp[p], nemonico[p]);
        cont = instruccion.indexOf("$") + 1;
        for (int i = 0; i < 2; i++) {
          regs[i] = instruccion.substring(cont, instruccion.indexOf(",", cont));
          cont = instruccion.indexOf("$", cont) + 1;
        }
        regs[2] = instruccion.substring(cont, instruccion.indexOf(" ", cont));
        /* Guardamos los registros */
        InstR.setRd(regs[0]);
        InstR.setRs(regs[1]);
        InstR.setRt(regs[2]);
        /* Seteamos shamt y funct */
        InstR.setShamt(0);
        InstR.setFunct(funct[p]);
        Mem.setRamI(InstR);
        break;

      /* Instruccion con 2 registros */
      case 2:

        /* Segun la instruccion hace cosas diferentes */
        switch (p) {
          case 3: /* lw */
          case 4: /* sw */
          case 10: /* lb */
          case 11: /* sb */
          case 12: /* sh */
          case 13: /* sb */
            InstI = new InstruccionI();
            cargaBasicos(InstI, op, BinOp[p], nemonico[p]);

            cont = instruccion.indexOf("$") + 1;
            regs[0] = instruccion.substring(cont, instruccion.indexOf(",", cont));
            cont = instruccion.indexOf(",", cont) + 1;
            InstI.setInmediato(instruccion.substring(cont,
                instruccion.indexOf("(")));
            cont = instruccion.indexOf("(", cont) + 2;
            regs[1] = instruccion.substring(cont, instruccion.indexOf(")"));
            /* Setear los rs y rd */
            InstI.setRs(regs[1]);
            InstI.setRt(regs[0]);
            Mem.setRamI(InstI);
            break;
          case 1: /* addi */
          case 18: /* addiu */
          case 19: /* andi */
          case 20: /* ori */
          case 30: /* slti */
          case 32: /* sltiu */
            InstI = new InstruccionI();
            cargaBasicos(InstI, op, BinOp[p], nemonico[p]);
            cont = 0;
            for (int i = 0; i < 2; i++) {
              cont = instruccion.indexOf("$", cont) + 1;
              regs[i] = instruccion.substring(cont,
                                              instruccion.indexOf(",", cont));

            }
            cont = instruccion.indexOf(",", cont) + 1;
            InstI.setInmediato(instruccion.substring(cont,
                instruccion.indexOf(" ")));
            /* Setear los rs y rd */
            InstI.setRs(regs[1]);
            InstI.setRt(regs[0]);
            Mem.setRamI(InstI);
            break;
          case 6: /* Beq */
            InstI = new InstruccionI();
            cargaBasicos(InstI, op, BinOp[p], nemonico[p]);
            cont = 0;
            for (int i = 0; i < 2; i++) {
              cont = instruccion.indexOf("$", cont) + 1;
              regs[i] = instruccion.substring(cont,
                                              instruccion.indexOf(",", cont));
            }
            cont = instruccion.indexOf(",", cont) + 1;
            /* Obtenemos el nombre de la etiqueta */
            temp = instruccion.substring(cont, instruccion.indexOf(" "));
            cont = retornaLabel(temp);
            cont2 = (Mem.getLibreI() + 1) * 4;
            /* Calculamos la distancia relativa al PC */
            cont -= cont2;
            InstI.setInmediato(String.valueOf(cont));
            InstI.setRs(regs[0]);
            InstI.setRt(regs[1]);
            Mem.setRamI(InstI);
            break;
          case 21: /* sll */
          case 22: /* srl */
            InstR = new InstruccionR();
            cargaBasicos(InstR, op, BinOp[p], nemonico[p]);
            cont = 0;
            for (int i = 0; i < 2; i++) {
              cont = instruccion.indexOf("$", cont) + 1;
              regs[i] = instruccion.substring(cont,
                                              instruccion.indexOf(",", cont));

            }
            cont = instruccion.indexOf(",", cont) + 1;
            InstR.setShamt(Integer.parseInt(instruccion.substring(cont,
                instruccion.indexOf(" "))));
            /* Setear los rt y rd */
            InstR.setRt(regs[1]);
            InstR.setRd(regs[0]);
            InstR.setFunct(funct[p]);
            Mem.setRamI(InstR);
            break;
          case 23: //mult
          case 24: //div
          case 25: //multu
          case 26: //divu
            InstR = new InstruccionR();
            cargaBasicos(InstR, op, BinOp[p], nemonico[p]);
            cont = 0;
            cont = instruccion.indexOf("$", cont) + 1;
            regs[0] = instruccion.substring(cont, instruccion.indexOf(",", cont));
            cont = instruccion.indexOf("$", cont) + 1;
            regs[1] = instruccion.substring(cont, instruccion.indexOf(" ", cont));
            InstR.setRs(regs[0]);
            InstR.setRt(regs[1]);
            InstR.setFunct(funct[p]);
            Mem.setRamI(InstR);
            break;
        }
        break;

      /* Instrucciones con 1 registro */
      case 1:
        switch (p) {
          case 9: /* jr */
            InstR = new InstruccionR();
            cargaBasicos(InstR, op, BinOp[p], nemonico[p]);
            cont = instruccion.indexOf("$") + 1;
            InstR.setRs(instruccion.substring(cont, instruccion.indexOf(" ")));
            InstR.setShamt(0);
            InstR.setFunct(funct[p]);
            Mem.setRamI(InstR);
            break;
          case 7: /* lui */
            InstI = new InstruccionI();
            cargaBasicos(InstI, op, BinOp[p], nemonico[p]);
            cont = instruccion.indexOf("$") + 1;
            InstI.setRt(instruccion.substring(cont, instruccion.indexOf(",")));
            cont = instruccion.indexOf(",", cont) + 1;
            InstI.setInmediato(instruccion.substring(cont,
                instruccion.indexOf(" ")));
            Mem.setRamI(InstI);
            break;
          case 27: /* mfhi */
          case 28: /* mflo */

            InstR = new InstruccionR();
            cargaBasicos(InstR, op, BinOp[p], nemonico[p]);
            cont = instruccion.indexOf("$") + 1;
            InstR.setRd(instruccion.substring(cont, instruccion.indexOf(" ")));
            InstR.setShamt(0);
            InstR.setFunct(funct[p]);
            Mem.setRamI(InstR);
            break;

        }
        break;

      /* Instrucciones sin registros */
      case 0:
        switch (p) {
          case 5: /* j */
          case 8: /* jal */
            InstJ = new InstruccionJ();
            cargaBasicos(InstJ, op, BinOp[p], nemonico[p]);
            temp = instruccion.substring( (p == 5) ? 1 : 3,
                                         instruccion.length() - 1);
                /* Agrega el try-cacth para avisar de que no se encontro el label */
            InstJ.setInmediato(retornaLabel(temp));
            Mem.setRamI(InstJ);
            break;
        }
        break;
    }
  }

  private static int numVeces(String cadena, char c) {
    int aciertos = 0;
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == c) {
        aciertos++;
      }
    }
    return aciertos;
  }

  private static void cargaBasicos(Instruccion inst, String op, int Binop,
                                   String nem) {
    inst.setOperador(op);
    inst.setCodigo(Binop);
    inst.setBinario(Binop);
    inst.setNemonico(nem);
  }
}
