package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Decoder {
  public static void run(String directoryPath) {
    File directory = new File(directoryPath);
    File[] inputFiles = directory.listFiles(file -> file.isFile() && file.getName().startsWith("TESTE") && file.getName().endsWith(".txt"));

    if (inputFiles != null && inputFiles.length > 0) {
      System.out.printf("MSG: %s TESTE-XX.txt encontrados no diretório %s", inputFiles.length, directory);
    } else {
      System.err.printf("ERRO: Nenhum arquivo de teste encontrado no diretório %s", directory);
      return;
    }

    System.out.println("\nMSG: Iniciando processamento dos arquivos de teste...");
    for (File inputFile : inputFiles) {
      System.out.println("Processando arquivo: " + inputFile.toPath());

      try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
        String line;

        while ((line = reader.readLine()) != null) {
          if (!line.trim().isEmpty()) {
            System.out.println(line);
          }
        }

      } catch (IOException e) {
        System.err.println("Erro ao processar o arquivo: " + e.getMessage());
      }
    }
  }
}


