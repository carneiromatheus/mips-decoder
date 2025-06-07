package model;

import java.util.HashMap;
import java.util.Map;

public class MipsFunctCodes {
  public static final Map<String, String> FUNCT_CODES = new HashMap<>();

  static {
    // Instruções Lógicas e Aritméticas R-Type
    FUNCT_CODES.put("100000", "add"); // Add
    FUNCT_CODES.put("100010", "sub"); // Subtract
    FUNCT_CODES.put("100100", "and"); // And
    FUNCT_CODES.put("100101", "or");  // Or
    FUNCT_CODES.put("100110", "xor"); // Exclusive Or
    FUNCT_CODES.put("000000", "sll"); // Shift Left Logical
    FUNCT_CODES.put("000010", "srl"); // Shift Right Logical
    FUNCT_CODES.put("001000", "jr");  // Jump Register
  }

  /**
   * Retorna o nome mnemônico de uma instrução R-type a partir de seu funct code.
   *
   * @param binaryFunctCode A string binária de 6 bits do funct code.
   * @return O nome mnemônico da instrução, ou null se não encontrado.
   */
  public static String get(String binaryFunctCode) {
    return FUNCT_CODES.get(binaryFunctCode);
  }
}