import desafioAguaLuz.entities.Contratante;
import desafioAguaLuz.entities.ContratoInstalacao;
import desafioAguaLuz.entities.Endereco;
import desafioAguaLuz.enums.TipoNotificacao;
import desafioAguaLuz.enums.TipoServico;
import desafioAguaLuz.enums.UnidadeFederativa;
import desafioAguaLuz.services.Cadastro;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

class CadastroTests {
    Path testPath01;
    String nomeArquivo;

    @BeforeEach
    void setUp() {
        nomeArquivo = "testeDbFile.txt";
        try {
            testPath01 = Path.of("src/test/resources");
        }
        catch (InvalidPathException e) {
            System.err.println("Erro na criação do arquivo temporário para teste em " + this.getClass().getSimpleName());
        }

        Endereco endereco1 = new Endereco("Rua Sebastião Firmino", 123, "AP 210 BL CENTAURO", "São Sebastião",
                "São Raimundo Nonato", UnidadeFederativa.valueOf("Sp".toUpperCase()),"07.210.715");
        Contratante contratante1 = new Contratante("007.324.223-21", "33765-5", "Raimundo Nonato Loureiro Castelo Branco", "(11)99768-1515", endereco1);
        ContratoInstalacao contratoInstalacao1 = new ContratoInstalacao(1984365, TipoServico.LUZ, TipoNotificacao.SMS, contratante1);

        Endereco endereco2 = new Endereco("Rua Paracatu", 245, "AP 104", "Parque Imperial",
                "São Paulo", UnidadeFederativa.valueOf("Sp".toUpperCase()),"04.302-021");
        Contratante contratante2 = new Contratante("123.423.666-55", "207.737-460", "Rubens de Paula e Souza", "(11)99888-2222", endereco2);
        ContratoInstalacao contratoInstalacao2 = new ContratoInstalacao(4738542, TipoServico.AGUA, TipoNotificacao.WHATS, contratante2);

        Endereco endereco3 = new Endereco("Avenida Almirante Maximiano Fonseca", 674, "CASA 02 BLOCO 11", "Zona Portuária",
                "Rio Grande", UnidadeFederativa.valueOf("RS".toUpperCase()),"96.204-040");
        Contratante contratante3 = new Contratante("197.444.123-44", "78.108.477", "Joane Maria da Silva Carvalho", "(55)88453-8977", endereco3);
        ContratoInstalacao contratoInstalacao3 = new ContratoInstalacao(98634329, TipoServico.LUZ, TipoNotificacao.SMS, contratante3);

        try {
            Cadastro.salvarCadastro(contratoInstalacao1, testPath01.toString(), nomeArquivo);
            Cadastro.salvarCadastro(contratoInstalacao2, testPath01.toString(), nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void deleteTempFile() throws IOException {
        try {
            Files.delete(Path.of(testPath01.toString(), nomeArquivo));
        }
        catch (NoSuchFileException e) {
            System.err.println("O arquivo a ser deletado não existe.");
        }

    }

    @DisplayName("Teste para salvar cadastro nulo")
    @Test
    void salvarCadastroNulo() throws IOException {
        String path = testPath01.toString();
        Path pathArquivo = Path.of(path, nomeArquivo);
        assertThrows(NullPointerException.class, () -> Cadastro.salvarCadastro(null, path, nomeArquivo));
        int numeroCadastros = Files.readAllLines(pathArquivo).size();
        assertEquals(2, numeroCadastros);
    }



}
