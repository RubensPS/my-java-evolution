package desafioAguaLuz.application;

import desafioAguaLuz.entities.Contratante;
import desafioAguaLuz.entities.ContratoInstalacao;
import desafioAguaLuz.entities.Endereco;
import desafioAguaLuz.enums.TipoNotificacao;
import desafioAguaLuz.enums.TipoServico;
import desafioAguaLuz.enums.UnidadeFederativa;
import desafioAguaLuz.services.Cadastro;

import java.io.IOException;

public class AguaLuzApplication {

    static {
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

        Endereco endereco4 = new Endereco("Rua Domingos Olímpio", 6788, "AP 12", "Centro",
                "Sobral", UnidadeFederativa.valueOf("ce".toUpperCase()),"62.011-140");
        Contratante contratante4 = new Contratante("31.555.231-77", "16.643.381-0", "Moema Iris Soares da Silva", "(88)99734-2525", endereco4);
        ContratoInstalacao contratoInstalacao4 = new ContratoInstalacao(2783569, TipoServico.AGUA, TipoNotificacao.WHATS, contratante4);

        Endereco endereco5 = new Endereco("Rua dos Carijós", 2465, "LOJA 7", "Santo Antônio",
                "Belo Horizonte", UnidadeFederativa.valueOf("mg".toUpperCase()),"30.120-060");
        Contratante contratante5 = new Contratante("228.553.123.97", "32.491.339-4", "Ramon Douglas Neves de Andrade", "(31)99552-1566", endereco5);
        ContratoInstalacao contratoInstalacao5 = new ContratoInstalacao(1735893, TipoServico.LUZ, TipoNotificacao.SMS, contratante5);
        try {
            Cadastro.salvarCadastro2(contratoInstalacao1);
            Cadastro.salvarCadastro2(contratoInstalacao2);
            Cadastro.salvarCadastro2(contratoInstalacao3);
            Cadastro.salvarCadastro2(contratoInstalacao4);
            Cadastro.salvarCadastro2(contratoInstalacao5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Endereco endereco = new Endereco("Rua dos Carijós", 2465, "LOJA 7", "Urca",
//                "Belo Horizonte", UnidadeFederativa.valueOf("mg".toUpperCase()),"30.120-060");
//        Contratante contratante = new Contratante("228.553.123.97", "32.491.339-4", "Ramon Douglas Neves de Andrade", "(31)99552-1566", endereco);
//        ContratoInstalacao contratoInstalacao = new ContratoInstalacao(1984365, TipoServico.LUZ, TipoNotificacao.SMS, contratante);
//
//        try {
//            Cadastro.salvarCadastro2(contratoInstalacao);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
