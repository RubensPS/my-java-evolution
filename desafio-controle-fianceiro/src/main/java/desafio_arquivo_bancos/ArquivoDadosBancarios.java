package desafio_arquivo_bancos;


import java.io.IOException;

public class ArquivoDadosBancarios {
    public static void main(String[] args) {
        String path = "C:\\Users\\ruben\\OneDrive\\Área de Trabalho\\dadosInstituicoes.txt";
        try {
            TransformaArquivoEmLista.transformarArquivoEmLista(path).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
