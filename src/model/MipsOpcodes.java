package model;

import java.util.HashMap;
import java.util.Map;

public class MipsOpcodes {
  public static final Map<String, String[]> OPCODES = new HashMap<>();

  static {
    // Instruções Load e Store
    OPCODES.put("100011", new String[]{"I", "lw"});   // Load Word
    OPCODES.put("101011", new String[]{"I", "sw"});   // Store Word
    OPCODES.put("100000", new String[]{"I", "lb"});   // Load Byte
    OPCODES.put("100001", new String[]{"I", "lh"});   // Load Halfword
    OPCODES.put("101000", new String[]{"I", "sb"});   // Store Byte
    OPCODES.put("101001", new String[]{"I", "sh"});   // Store Halfword

    // Instruções Lógicas e Aritméticas I-Type
    OPCODES.put("001000", new String[]{"I", "addi"}); // Add Immediate
    OPCODES.put("001100", new String[]{"I", "andi"}); // And Immediate
    OPCODES.put("001101", new String[]{"I", "ori"});  // Or Immediate
    OPCODES.put("001110", new String[]{"I", "xori"}); // Exclusive Or Immediate
    OPCODES.put("001111", new String[]{"I", "lui"});  // Load Upper Immediate

    // Instruções de Desvio I-Type
    OPCODES.put("000100", new String[]{"I", "beq"});  // Branch on Equal
    OPCODES.put("000101", new String[]{"I", "bne"});  // Branch on Not Equal
    OPCODES.put("000110", new String[]{"I", "blez"}); // Branch on Less Than or Equal to Zero
    OPCODES.put("000111", new String[]{"I", "bgtz"}); // Branch on Greater Than Zero

    // Instruções Jump J-Type
    OPCODES.put("000010", new String[]{"J", "j"});    // Jump

    // Instruções R-Type (opcode 000000)
    OPCODES.put("000000", new String[]{"R", "R_type_instruction"}); // R-Type
  }

  /**
   * Retorna as informações de um opcode (tipo de instrução e mnemônico/indicador).
   *
   * @param binaryOpcode A string binária de 6 bits do opcode.
   * @return Um array de String contendo o tipo da instrução e o mnemônico/indicador, ou null se não encontrado.
   */
  public static String[] get(String binaryOpcode) {
    return OPCODES.get(binaryOpcode);
  }
}