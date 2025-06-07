package model;

import java.util.HashMap;
import java.util.Map;

public class MipsRegisters {
  public static final Map<String, String> REGISTERS = new HashMap<>();

  static {
    REGISTERS.put("00000", "$zero");
    REGISTERS.put("00001", "$at");
    REGISTERS.put("00010", "$v0");
    REGISTERS.put("00011", "$v1");
    REGISTERS.put("00100", "$a0");
    REGISTERS.put("00101", "$a1");
    REGISTERS.put("00110", "$a2");
    REGISTERS.put("00111", "$a3");
    REGISTERS.put("01000", "$t0");
    REGISTERS.put("01001", "$t1");
    REGISTERS.put("01010", "$t2");
    REGISTERS.put("01011", "$t3");
    REGISTERS.put("01100", "$t4");
    REGISTERS.put("01101", "$t5");
    REGISTERS.put("01110", "$t6");
    REGISTERS.put("01111", "$t7");
    REGISTERS.put("10000", "$s0");
    REGISTERS.put("10001", "$s1");
    REGISTERS.put("10010", "$s2");
    REGISTERS.put("10011", "$s3");
    REGISTERS.put("10100", "$s4");
    REGISTERS.put("10101", "$s5");
    REGISTERS.put("10110", "$s6");
    REGISTERS.put("10111", "$s7");
    REGISTERS.put("11000", "$t8");
    REGISTERS.put("11001", "$t9");
    REGISTERS.put("11010", "$k0");
    REGISTERS.put("11011", "$k1");
    REGISTERS.put("11100", "$gp");
    REGISTERS.put("11101", "$sp");
    REGISTERS.put("11110", "$fp");
    REGISTERS.put("11111", "$ra");
  }

  /**
   * Retorna o nome mnemônico de um registrador MIPS a partir de sua representação binária.
   *
   * @param binaryReg A string binária de 5 bits do registrador.
   * @return O nome do registrador (ex: "$t0"), ou a própria string binária se não for encontrado.
   */
  public static String get(String binaryReg) {
    return REGISTERS.get(binaryReg);
  }
}