package desafio_arquivo_bancos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransformaArquivoEmLista {

    public static List<DadosBanco> transformarArquivoEmLista(String nomeArquivo) throws IOException {
        List<String> listaLinhas;
        listaLinhas = Files.readAllLines(Path.of(nomeArquivo), StandardCharsets.UTF_8);


        //Dica Gleyson - fazer com o for-each. Bem mais enxuto e fácil de ler
        List<DadosBanco> listaInstituicoes = new ArrayList<>();
        for(String linha:listaLinhas) {
            DadosBanco banco = new DadosBanco();
            banco.setCodigoBancario(linha.substring(0, 3));
            banco.setNomeInstituicao(linha.substring(3, linha.length() - 8).trim());
            banco.setIsbp(linha.substring(linha.length() - 8));
            listaInstituicoes.add(banco);
        }

        //for inicial. Subtituído.
//        for (int i = 0; i < listaLinhas.size(); i++) {
//            DadosBanco banco = new DadosBanco();
//            banco.setCodigoBancario(listaLinhas.get(i).substring(0, 3));
//            banco.setNomeInstituicao(listaLinhas.get(i).substring(3, listaLinhas.get(i).length() - 8).trim());
//            banco.setIsbp(listaLinhas.get(i).substring(listaLinhas.get(i).length() - 8, listaLinhas.get(i).length()));
//            listaInstituicoes.add(banco);
//        }

        return listaInstituicoes;
    }

}
