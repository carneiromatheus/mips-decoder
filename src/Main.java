import controller.Decoder;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("MSG: Iniciando o Decodificador de MIPS Binário...");
    System.out.print("INPUT: Informe o caminho dos arquivos teste: ");

    Scanner sc = new Scanner(System.in);
    String directoryPath = sc.nextLine().trim();
    sc.close();

    Decoder.run(directoryPath);
  }
}