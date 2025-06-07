# Requisitos do Projeto - Decodificador MIPS

Este documento detalha os requisitos funcionais e o fluxo de execução do Decodificador MIPS.

## Objetivo Principal

O objetivo do programa é ler arquivos contendo códigos MIPS binários de instruções e gerar arquivos de saída com os mnemônicos correspondentes.

* **Arquivo de Entrada:** Deverá conter N instruções escritas em binário, sendo N > 0. Cada instrução estará em uma linha.
* **Arquivo de Saída:** Deverá conter as mesmas N instruções, na mesma ordem, mas escritas com seus respectivos mnemônicos. Cada instrução estará em uma linha.

## Testes e Nomenclatura de Arquivos

Para os testes a serem realizados, o programa deverá ler, em sequência, um total de 10 arquivos de entrada que estarão na raiz de um pendrive (ou no diretório especificado pelo usuário) e que serão nomeados seguindo o formato "TESTE-XX.txt", sendo XX o número do arquivo de teste que irá de 01 a 10.

Para cada arquivo de entrada, o programa deverá produzir um arquivo de saída contendo a resposta e salvar esses arquivos no mesmo diretório dos arquivos de entrada.  O arquivo de saída deverá ter o mesmo nome do arquivo de entrada acrescido de "-RESULTADO.txt".
* Exemplo: Se o arquivo de entrada tiver o nome "TESTE-01.txt", o arquivo de saída deverá conter o nome "TESTE-01-RESULTADO.txt".

## Fluxo de Execução do Projeto

O fluxo de execução do projeto começa na classe `Main` e delega a orquestração para o `Decoder`, seguindo a estrutura de camadas:

1.  **Início da Execução (Raiz de `src/` - `Main.java`)**
    * [ ] O programa inicia a execução no método `public static void main(String[] args)` da classe `Main`.
    * [ ] A classe `Main` verifica se um argumento de caminho para o diretório dos testes foi fornecido. Se não, exibe uma mensagem de uso e encerra.
    * [ ] Se um caminho for fornecido, ele é passado para o método `run(String basePath)` da classe `controller.Decoder`, delegando o controle para a camada de orquestração principal.

2.  **Orquestração e Leitura/Escrita de Arquivos (Camada `controller` - `Decoder.java`)**
    * [ ] O método `run` da classe `Decoder` recebe o caminho base dos arquivos.
    * [ ] Ele entra em um laço que itera de 1 a 10, correspondendo aos 10 arquivos de teste (TESTE-01.txt a TESTE-10.txt) que o programa deve processar.
    * [ ] Para cada iteração, ele constrói os nomes dos arquivos de entrada (`TESTE-XX.txt`) e de saída (`TESTE-XX-RESULTADO.txt`).
    * [ ] **Usando o Caminho Fornecido:** Utiliza a classe `java.nio.file.Paths` para combinar o `basePath` com os nomes dos arquivos, garantindo a construção correta dos caminhos completos.
    * [ ] Um `BufferedReader` é aberto para ler o arquivo de entrada linha por linha, e um `BufferedWriter` é aberto para escrever no arquivo de saída correspondente.
    * [ ] O programa exibe uma mensagem no console indicando qual arquivo está sendo processado.
    * [ ] Dentro do laço de leitura de arquivo, o `Decoder` lê cada linha do arquivo de entrada.
    * [ ] Ele verifica se a linha não está vazia (ignorando linhas em branco).
    * [ ] Se a linha contém uma instrução binária, ela é passada para a camada de `service` para decodificação.

3.  **Decodificação da Instrução (Camada `service` - `Decoding.java`)**
    * [ ] A classe `Decoding` recebe a string binária de 32 bits da instrução através do método `decodeInstruction`.
    * [ ] **Validação Inicial:** Primeiramente, ela verifica se a string da instrução é nula ou se não possui exatamente 32 bits de comprimento. Se for inválida, retorna uma mensagem de erro.
    * [ ] **Extração do Opcode:** Os primeiros 6 bits da instrução são extraídos para obter o `opcode`.
    * [ ] **Consulta de Opcode:** O `opcode` é então usado para consultar o mapa `OPCODES` na camada `model` (`model.MipsOpcodes`).
        * [ ] Esta consulta retorna um array de `String` contendo o tipo da instrução ("R", "I", "J") e o mnemônico base ou um indicador.
        * [ ] Se o `opcode` for desconhecido (não encontrado no mapa), uma mensagem de erro é retornada.
    * [ ] **Delegando para o Tipo Específico:** Com base no `tipo` de instrução (R, I ou J) obtido da consulta, o `Decoding` delega a lógica para um método privado específico:
        * [ ] `decodeRType` se for uma instrução Tipo R.
        * [ ] `decodeIType` se for uma instrução Tipo I.
        * [ ] `decodeJType` se for uma instrução Tipo J.

4.  **Decodificação por Tipo de Instrução (Camada `service` - `Decoding.java`)**

    * [ ] **`decodeRType(String instructionBinary, String opcodeMnemonic)`:**
        * [ ] Extrai os campos `rs`, `rt`, `rd` (cada um com 5 bits), `shamt` (5 bits) e `funct` (6 bits) da string binária.
        * [ ] Converte os campos `rs`, `rt` e `rd` de binário para seus nomes mnemônicos (ex: `$t0`) usando `MipsRegisters.getRegisterName` (camada `model`).
        * [ ] Usa o campo `funct` para consultar o mapa `FUNCT_CODES` em `model.MipsFunctCodes` para obter o mnemônico exato da instrução (ex: "add", "sll").
        * [ ] Se o `funct code` for desconhecido, uma mensagem de erro é retornada.
        * [ ] Formata a string mnemônica final com os registradores e valores numéricos, seguindo o padrão da instrução (ex: `add $rd, $rs, $rt`).

    * [ ] **`decodeIType(String instructionBinary, String mnemonic)`:**
        * [ ] Extrai os campos `rs` (5 bits), `rt` (5 bits) e `immediate` (16 bits) da string binária.
        * [ ] Converte os campos `rs` e `rt` para seus nomes mnemônicos usando `MipsRegisters.getRegisterName`.
        * [ ] Converte a string binária de `immediate` para um inteiro com sinal usando `BinaryToInteger.convert` (camada `utils`).
        * [ ] Formata a string mnemônica final de acordo com o mnemônico e os operandos (ex: `lw $rt, offset($rs)`, `addi $rt, $rs, immediate`, `beq $rs, $rt, offset`).

    * [ ] **`decodeJType(String instructionBinary, String mnemonic)`:**
        * [ ] Extrai o campo `address` (26 bits) da string binária.
        * [ ] Converte a string binária de `address` para um inteiro.
        * [ ] Formata a string mnemônica final (ex: `j address_value`).

5.  **Geração e Escrita do Resultado (Camada `controller` - `Decoder.java`)**
    * [ ] O `Decoder` recebe a instrução mnemônica formatada da camada `service`.
    * [ ] Essa string mnemônica é escrita como uma nova linha no `BufferedWriter` associado ao arquivo de saída.
    * [ ] Após processar todas as linhas de um arquivo de entrada, os `BufferedReader` e `BufferedWriter` são fechados automaticamente (devido ao `try-with-resources`).
    * [ ] O programa exibe uma mensagem no console confirmando que o arquivo de saída foi gerado.

6.  **Conclusão da Execução (Camada `controller` - `Decoder.java`)**
    * [ ] O laço continua para o próximo arquivo de teste até que todos os 10 arquivos tenham sido processados.
    * [ ] Em caso de qualquer `IOException` (erro de leitura/escrita de arquivo), uma mensagem de erro é impressa no console.
    * [ ] Ao final do laço, uma mensagem de conclusão geral é exibida.

## Instruções MIPS Suportadas

- [ ] **Load e Store:** LB, LH, LW, SB, SH, SW
- [ ] **Lógicas e Aritméticas:** ADD, SUB, AND, OR, XOR, ADDI, ANDI, ORI, XORI, LUI
- [ ] **Deslocamento de Bits:** SLL, SRL
- [ ] **Desvio:** JR, J, BEQ, BNE, BLEZ, BGTZ 