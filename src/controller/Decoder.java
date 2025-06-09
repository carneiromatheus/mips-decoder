package controller;

import service.Decoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Decoder {

  private static File[] getFiles(File directory) {
    if (!directory.isDirectory()) return null;
    return directory.listFiles(
            file -> file.isFile() && file.getName().startsWith("TESTE") && file.getName().endsWith(".txt"));
  }

  private static String decodeInputFile(File file) throws IOException {
    StringBuilder decodedContent = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String lineContent;

      while ((lineContent = reader.readLine()) != null) {
        if (!lineContent.trim().isEmpty()) {
          String decodedInstruction = Decoding.decodeInstruction(lineContent.trim());
          decodedContent.append(decodedInstruction).append(System.lineSeparator());
          System.out.println("BINÁRIOS: " + lineContent + " --->  MNEMÔNICO: " + decodedInstruction);
        }
      }
    }

    return decodedContent.toString();
  }

  private static void writeOutputFile(String content, File outputFile) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      writer.write(content);
    }
  }

  public static void run(String directoryPath) throws IOException {
    File directory = new File(directoryPath);
    File[] inputFiles = getFiles(directory);

    if (inputFiles == null || inputFiles.length == 0) {
      System.err.printf("ERRO: Nenhum arquivo de teste encontrado no diretório %s%n", directory);
      return;
    }

    System.out.printf("MSG: %s TESTE-XX.txt encontrados no diretório %s%n", inputFiles.length, directory);
    System.out.println("MSG: Iniciando processamento dos arquivos de teste...");

    File outputDirectory = new File(directory, "Augusto e Matheus Carneiro");
    if (!outputDirectory.exists()) {
      boolean created = outputDirectory.mkdirs();
      if (!created) {
        System.err.println("ERRO: Falha ao criar diretório de saída.");
        return;
      }
    }

    for (File inputFile : inputFiles) {
      System.out.println("MSG: Decodificando o arquivo: " + inputFile.toPath());

      String outputFileName = inputFile.getName().replace(".txt", "-RESULTADO.txt");
      File outputFile = new File(outputDirectory, outputFileName);

      writeOutputFile(decodeInputFile(inputFile), outputFile);

      System.out.println("MSG: Arquivo decodificado gravado em: " + outputFile.getPath());
    }
  }

}
