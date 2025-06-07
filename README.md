# Decodificador MIPS

Este projeto implementa um decodificador de instruções MIPS binárias para seus mnemônicos correspondentes, conforme
especificado no trabalho 1 [(ver anexo)](trabalho_1.pdf) da disciplina de Arquitetura de Computadores do Instituto
Federal de Minas Gerais (IFMG) -
Câmpus Sabará.

## Objetivo do Projeto

O programa deve ler arquivos contendo códigos MIPS binários e gerar arquivos de saída com os mnemônicos
correspondentes. Detalhes completos sobre como o objetivo e os requisitos forma implementados pode ser encontrados no
arquivo [REQUIREMENTS.md](REQUIREMENTS.md).

## Estrutura do Projeto

O projeto está organizado em pacotes para melhor modularidade e separação de responsabilidades, seguindo uma estrutura
semelhante a MVC com camada de serviços:

```
./
└── src/
    ├── controller/
    │   └── Decoder.java
    ├── service/
    │   └── Decoding.java
    ├── model/
    │   ├── MipsRegisters.java
    │   ├── MipsOpcodes.java
    │   └── MipsFunctCodes.java
    ├── utils/
    │   └── BinaryToInteger.java
    ├── Main.js
    ├── README.md
    ├── REQUIREMENTS.md
    └── trabalho_1.pdf
    
```

## Como Compilar e Executar

Para configurar e executar o projeto, siga os passos abaixo:

1. **Clonar o Repositório:**
   Primeiro, clone o repositório para sua máquina local usando o Git.
   ```bash
   git clone git@github.com:carneiromatheus/mips-decoder.git
   cd mips-decoder
   ```
   ou
   ```bash
   git clone https://github.com/carneiromatheus/mips-decoder.git
   cd mips-decoder
   ```

2. **Posicionar os Arquivos de Teste:**
   Crie um diretório para seus arquivos de teste (ex: `meus_testes`) e coloque os arquivos `TESTE-XX.txt` dentro dele.
   Este diretório *não precisa* estar na raiz do projeto, mas você precisará informar o
   caminho para ele na execução.

3. **Compilar:**
   Abra um terminal no diretório raiz do projeto (onde a pasta `src` está) e compile o código-fonte Java. O comando
   abaixo compilará todos os arquivos `.java` dentro do diretório `src` e seus subdiretórios.
   ```bash
   javac src/**/*.java
   ```

4. **Executar:**
   Após a compilação bem-sucedida, execute o programa a partir do diretório raiz do projeto. O ponto de entrada
   principal é a classe `Main`, localizada na raiz do pacote `src`.
   ```bash
   java -cp src Main <caminho_para_seus_arquivos_de_teste>
   ```
   **Exemplos:**
    * Se seus arquivos de teste estiverem em um diretório chamado `testes_mips` na mesma pasta do projeto:
        ```bash
        java -cp src Main testes_mips
        ```
    * Se seus arquivos de teste estiverem em um diretório como `/home/usuario/documentos/arquivos_binarios`:
        ```bash
        java -cp src Main /home/usuario/documentos/arquivos_binarios
        ```
    * Se seus arquivos de teste estiverem em um diretório como `C:\Users\SeuNome\Documentos\MIPS_Testes` (no Windows):
        ```bash
        java -cp src Main "C:\Users\SeuNome\Documentos\MIPS_Testes"
        ```
   (Note as aspas para caminhos no windows).

## Saída

Para cada arquivo de entrada `TESTE-XX.txt` encontrado no caminho fornecido, o programa irá produzir um arquivo de saída
contendo a resposta e salvar esses arquivos **no mesmo diretório** dos arquivos de entrada. Além disso, o arquivo de
saída terá o mesmo nome do arquivo de entrada acrescido de "`-RESULTADO.txt`". Cada linha do arquivo de saída conterá o
mnemônico MIPS correspondente à instrução binária da linha de entrada.

## Instruções Suportadas

Para uma lista detalhada das instruções MIPS suportadas e seus formatos, consulte o
arquivo [REQUIREMENTS.md](REQUIREMENTS.md).