package controller;

import service.Decoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Decoder {

  private static File[] getFiles(File directory) {
    if (!directory.isDirectory()) return null;
    return directory.listFiles(
            file -> file.isFile() && file.getName().startsWith("TESTE") && file.getName().endsWith(".txt"));
  }

  private static String decodeFile(File file) throws IOException {
    StringBuilder decodedContent = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String lineContent;

      while ((lineContent = reader.readLine()) != null) {
        if (!lineContent.trim().isEmpty()) {
          String decodedInstruction = Decoding.decodeInstruction(lineContent.trim());
          decodedContent.append(decodedInstruction).append(System.lineSeparator());
          System.out.println("INPUT: " +lineContent + " --->  OUTPUT: " + decodedInstruction);
        }
      }
    }

    return decodedContent.toString();
  }

  public static void run(String directoryPath) throws IOException {
    File directory = new File(directoryPath);
    File[] inputFiles = getFiles(directory);

    if (inputFiles != null && inputFiles.length > 0) {
      System.out.printf("MSG: %s TESTE-XX.txt encontrados no diretório %s%n", inputFiles.length, directory);
    } else {
      System.err.printf("ERRO: Nenhum arquivo de teste encontrado no diretório %s%n", directory);
      return;
    }

    System.out.println("MSG: Iniciando processamento dos arquivos de teste...");

    for (File inputFile : inputFiles) {
      System.out.println("MSG: Decodificando o arquivo: " + inputFile.toPath());
      decodeFile(inputFile);
    }
  }
}
