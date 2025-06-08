package service;

import model.MipsFunctCodes;
import model.MipsOpcodes;
import model.MipsRegisters;
import utils.BinaryToDecimal;

public class Decoding {

  public static String decodeInstruction(String instructionBinary) {
    if (instructionBinary == null || instructionBinary.length() != 32) {
      return "INVALID_INSTRUCTION_LENGTH: " + instructionBinary;
    }

    String opcode = instructionBinary.substring(0, 6);
    String[] opcodeInfo = MipsOpcodes.get(opcode);

    String type = opcodeInfo[0];
    String mnemonic = opcodeInfo[1];

    if (type.equals("UNKNOWN")) {
      return "UNKNOWN_OPCODE: " + opcode + " " + instructionBinary;
    }

    switch (type) {
      case "R":
        return decodeRType(instructionBinary, mnemonic);
      case "I":
        return decodeIType(instructionBinary, mnemonic);
      case "J":
        return decodeJType(instructionBinary, mnemonic);
      default:
        return "UNEXPECTED_INSTRUCTION_TYPE: " + type + " " + instructionBinary;
    }
  }

  private static String decodeRType(String instructionBinary, String opcodeMnemonic) {
    String rsBinary = instructionBinary.substring(6, 11);
    String rtBinary = instructionBinary.substring(11, 16);
    String rdBinary = instructionBinary.substring(16, 21);
    String shamtBinary = instructionBinary.substring(21, 26);
    String functBinary = instructionBinary.substring(26, 32);

    String rs = MipsRegisters.get(rsBinary);
    String rt = MipsRegisters.get(rtBinary);
    String rd = MipsRegisters.get(rdBinary);

    String instructionMnemonic = MipsFunctCodes.get(functBinary);

    if (instructionMnemonic.equals("UNKNOWN_FUNCT_MNEMONIC")) {
      return "UNKNOWN_R_TYPE_FUNCT: " + functBinary + " " + instructionBinary;
    }

    switch (instructionMnemonic) {
      case "add":
      case "sub":
      case "and":
      case "or":
      case "xor":
        return String.format("%s %s, %s, %s", instructionMnemonic, rd, rs, rt);
      case "sll":
      case "srl":
        int shamt_val = Integer.parseInt(shamtBinary, 2);
        return String.format("%s %s, %s, %d", instructionMnemonic, rd, rt, shamt_val);
      case "jr":
        return String.format("%s %s", instructionMnemonic, rs);
      default:
        return "UNHANDLED_R_TYPE_FUNCT_FORMAT: " + instructionMnemonic + " " + instructionBinary;
    }
  }

  private static String decodeIType(String instructionBinary, String mnemonic) {
    String rsBinary = instructionBinary.substring(6, 11);
    String rtBinary = instructionBinary.substring(11, 16);
    String immediateStr = instructionBinary.substring(16, 32);
    int immediate = BinaryToDecimal.convert(immediateStr);

    String rs = MipsRegisters.get(rsBinary);
    String rt = MipsRegisters.get(rtBinary);

    return switch (mnemonic) {
      case "lw", "sw", "lb", "lh", "sb", "sh" -> String.format("%s %s, %d(%s)", mnemonic, rt, immediate, rs);
      case "addi", "andi", "ori", "xori" -> String.format("%s %s, %s, %d", mnemonic, rt, rs, immediate);
      case "lui" -> String.format("%s %s, %d", mnemonic, rt, immediate);
      case "beq", "bne" -> String.format("%s %s, %s, %d", mnemonic, rs, rt, immediate);
      case "blez", "bgtz" -> String.format("%s %s, %d", mnemonic, rs, immediate);
      default -> "UNHANDLED_I_TYPE_FORMAT: " + mnemonic + " " + instructionBinary;
    };
  }

  private static String decodeJType(String instructionBinary, String mnemonic) {
    String addressBinary = instructionBinary.substring(6, 32);
    int addressValue = Integer.parseInt(addressBinary, 2);

    return String.format("%s %d", mnemonic, addressValue);
  }
}
