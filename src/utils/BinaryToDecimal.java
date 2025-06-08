package utils;

public class BinaryToDecimal {

  public static int convert(String binaryStr) {
    int decimal = Integer.parseInt(binaryStr, 2);
    if (binaryStr.charAt(0) == '1') {
      return decimal - (1 << 16);
    } else {
      return decimal;
    }
  }
}
