/**
 * <p>Título: not(SPIM)</p>
 * <p>Descripción: Sencillo simulador de instrucciones MIPS</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Universidad Nacional San Juan Bosco</p>
 * @author Fernando Bonti && Francisco Paez
 * @version 3.11
 */

public class Ejecutar {
  static memoria mem;
  static private int mascara = 0x000000FF;

  public static void setMemoria(memoria m) {
    mem = m;
  }

  /* Ejecuta Instrucciones de Formato R */
  public static void ejecutar(InstruccionR instruccion) {
    int codigoop;
    int temp;
    codigoop = instruccion.getCodigo();
    int f; // funct
    int s; // shamt
    long multi;
    switch (codigoop) {
      case 0:

        /* Convertimos el binario a decimal */
        f = Integer.parseInt(instruccion.getFunct(), 2);
        switch (f) {
          case 0: /* sll */
            temp = mem.getRegistro(instruccion.getRt());
            temp <<= instruccion.getShamt();
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 2: /* srl */
            temp = mem.getRegistro(instruccion.getRt());
            temp >>= instruccion.getShamt();
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 8: /* jr */
            mem.setCp(mem.getRegistro(instruccion.getRs()));
            break;
          case 16: /* mfhi */
            mem.setRegistro(instruccion.getRd(), mem.getHi());
            break;
          case 18: /* mflo */
            mem.setRegistro(instruccion.getRd(), mem.getLo());
            break;
          case 24: /* mult */
            multi = (long) mem.getRegistro(instruccion.getRs()) *
                (long) mem.getRegistro(instruccion.getRt());
            mem.setLo( (int) multi);
            mem.setHi( (int) (multi >> 32));
            break;
          case 25: /* multu */
            multi = Math.abs( (long) mem.getRegistro(instruccion.getRs())) *
                Math.abs( (long) mem.getRegistro(instruccion.getRt()));
            mem.setLo( (int) multi);
            mem.setHi( (int) (multi >> 32));
            break;
          case 26: /* div */
            mem.setHi(mem.getRegistro(instruccion.getRs()) /
                      mem.getRegistro(instruccion.getRt()));
            mem.setLo(mem.getRegistro(instruccion.getRs()) %
                      mem.getRegistro(instruccion.getRt()));
            break;
          case 27: /* div */
            mem.setHi(Math.abs(mem.getRegistro(instruccion.getRs()) /
                               mem.getRegistro(instruccion.getRt())));
            mem.setLo(Math.abs(mem.getRegistro(instruccion.getRs()) %
                               mem.getRegistro(instruccion.getRt())));
            break;
          case 32: /* Add */
            temp = mem.getRegistro(instruccion.getRs()) +
                mem.getRegistro(instruccion.getRt());
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 33: /* Addu */
            temp = Math.abs(mem.getRegistro(instruccion.getRs())) +
                Math.abs(mem.getRegistro(instruccion.getRt()));
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 34: /* Sub */
            temp = mem.getRegistro(instruccion.getRs()) -
                mem.getRegistro(instruccion.getRt());
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 35: /* Subu */
            temp = Math.abs(mem.getRegistro(instruccion.getRs())) -
                Math.abs(mem.getRegistro(instruccion.getRt()));
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 36: /* And */
            temp = mem.getRegistro(instruccion.getRs()) &
                mem.getRegistro(instruccion.getRt());
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 37: /* or */
            temp = mem.getRegistro(instruccion.getRs()) |
                mem.getRegistro(instruccion.getRt());
            mem.setRegistro(instruccion.getRd(), temp);
            break;
          case 42: /*slt */
            if (mem.getRegistro(instruccion.getRs()) < mem.getRegistro(instruccion.getRt()))
              mem.setRegistro(instruccion.getRd(), 1);
            else
              mem.setRegistro(instruccion.getRd(), 0);
            break;
          case 43: /*sltu*/
            if (Math.abs(mem.getRegistro(instruccion.getRs())) < Math.abs(mem.getRegistro(instruccion.getRt())))
              mem.setRegistro(instruccion.getRd(), 1);
            else
              mem.setRegistro(instruccion.getRd(), 0);
            break;
        }
        break;
    }
  }

  /* Ejecuta Instrucciones de Formato I */
  public static void ejecutar(InstruccionI instruccion) {
    int codigoop;
    int temp, temp2;
    byte tempb;
    codigoop = instruccion.getCodigo();
    switch (codigoop) {
      case 4: /* Beq */
        if (mem.getRegistro(instruccion.getRs())
            - mem.getRegistro(instruccion.getRt()) == 0) {
          mem.setCp( (mem.getCp() * 4) + instruccion.getInmediato());
        }
        break;
      case 8: /* Addi */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        mem.setRegistro(instruccion.getRt(), temp);
        break;
      case 9: /* Addiu */
        temp = Math.abs(mem.getRegistro(instruccion.getRs()))
            + Math.abs(instruccion.getInmediato());
        mem.setRegistro(instruccion.getRt(), temp);
        break;
      case 10: /*slti */
        if (mem.getRegistro(instruccion.getRs()) < instruccion.getInmediato())
             mem.setRegistro(instruccion.getRt(), 1);
           else
             mem.setRegistro(instruccion.getRt(), 0);
           break;
         case 11: /*sltiu*/
      if (Math.abs(mem.getRegistro(instruccion.getRs())) < Math.abs(instruccion.getInmediato()))
           mem.setRegistro(instruccion.getRt(), 1);
         else
           mem.setRegistro(instruccion.getRt(), 0);
         break;
      case 12: /* andi */
        temp = mem.getRegistro(instruccion.getRs()) & instruccion.getInmediato();
        mem.setRegistro(instruccion.getRt(), temp);
        break;
      case 13: /* ori */
        temp = mem.getRegistro(instruccion.getRs()) | instruccion.getInmediato();
        mem.setRegistro(instruccion.getRt(), temp);
        break;
      case 15: /* Lui  */
        temp = instruccion.getInmediato();
        temp = temp << 16;
        mem.setRegistro(instruccion.getRt(), temp);
        break;
      case 32: /* Lb */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        // Enmascaramiento. Evita que se extienda el signo
        temp2 = mem.leerByte(temp) & 0xFF;
        mem.setRegistro(instruccion.getRt(), temp2);
        break;
      case 33: /* Lh */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        int b1 = mem.leerByte(temp + 1) & 0x00FF;
        int b0 = mem.leerByte(temp) & 0x00FF;
        temp2 = (b1 << 8) | b0;
        mem.setRegistro(instruccion.getRt(), temp2);
        break;
      case 35: /* Lw */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        temp2 = mem.leerPalabra(temp);
        mem.setRegistro(instruccion.getRt(), temp2);
        break;
      case 40: /* Sb */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        tempb = (byte) (mem.getRegistro(instruccion.getRt()) & mascara);
        mem.escribirByte(temp, tempb);
        break;
      case 41: /* Sh */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        temp2 = mem.getRegistro(instruccion.getRt());
        mem.escribirByte(temp + 1, (byte) (temp2 >> 8));
        mem.escribirByte(temp, (byte) (temp2));
        break;
      case 43: /* Sw */
        temp = mem.getRegistro(instruccion.getRs()) + instruccion.getInmediato();
        temp2 = mem.getRegistro(instruccion.getRt());
        mem.escribirPalabra(temp, temp2);
        break;
    }
  }

  /* Ejecuta Instrucciones de Formato J */
  public static void ejecutar(InstruccionJ instruccion) {
    int codigoop;
    int temp;
    codigoop = instruccion.getCodigo();
    switch (codigoop) {
      case 2: /* J */
        mem.setCp(instruccion.getInmediato());
        break;
      case 3: /* Jal  */
        mem.setRegistro(31, mem.getCp() * 4);
        mem.setCp(instruccion.getInmediato());
        break;
    }
  }
}
