package desafioAguaLuz.services;

import desafioAguaLuz.entities.ContratoInstalacao;
import desafioAguaLuz.enums.TipoServico;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Cadastro {

    public static String buscarCadastro(String path, String protocolo) throws IOException {

        return Files.lines(Path.of(path)).filter(c -> c.contains(protocolo)).toList().get(0);

    }

    public static void gerarRelatorio(String path, String protocoloBusca) throws IOException, ParseException {
        String cadastro = buscarCadastro(path, protocoloBusca);

        String cpf = formatarCpf(cadastro.substring(0,11).trim());
        String rg = cadastro.substring(11,21).trim();
        String nome = cadastro.substring(21,51).trim();
        String celular = formatarCelular(cadastro.substring(51,62).trim());
        String logradouro = cadastro.substring(62,82).trim();
        Integer numero = Integer.parseInt(cadastro.substring(82,88).trim());
        String complemento = cadastro.substring(88,98).trim();
        String bairro = cadastro.substring(98,113).trim();
        String cidade = cadastro.substring(113,143).trim();
        String uf = cadastro.substring(143,145).trim();
        String cep = formatarCep(cadastro.substring(145,153).trim());
        String pais = cadastro.substring(153,155).trim();
        String protocolo = cadastro.substring(155,165).trim();
        String dataHora = formatarDataHora(cadastro.substring(165,177).trim());
        TipoServico servico = TipoServico.getServico(cadastro.substring(177,178).trim());
        String valorServico = formatarValor(cadastro.substring(178,186).trim());
        String tipoNotificacao = cadastro.substring(186).trim();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Senhor(a) %s, cpf de número %s.%n",nome,cpf));
        sb.append(String.format("Informamos que conforme contrato com protocolo de número %s ",protocolo));
        sb.append(String.format("está agendado para a data/hora %sh a%n",dataHora));
        sb.append(String.format("instalação do serviço de %s com taxa de %s ",servico,valorServico));
        sb.append("em sua residência, localizada no endereço abaixo:");
        sb.append(String.format("%n%n   \u2022 Logradouro: %s, %s%n",logradouro,numero));
        sb.append(String.format("   \u2022 Complemento: %s%n",complemento));
        sb.append(String.format("   \u2022 Bairro: %s%n",bairro));
        sb.append(String.format("   \u2022 Cidade: %s / %s%n",cidade,uf));
        sb.append(String.format("   \u2022 Cep: %s%n",cep));
        exibirMensagem(sb, celular);
    }

    public static void salvarCadastro(ContratoInstalacao contrato) throws IOException {
        List<String> list = new ArrayList<>();
        list.add(String.format("%011d", Long.parseLong(contrato.getContratante().getCpf().replaceAll("[\\.\\-]", ""))));
        list.add(String.format("%-10.10s", contrato.getContratante().getRg().replaceAll("[\\-\\.]", "")));
        list.add(String.format("%-30.30s", contrato.getContratante().getNome().toUpperCase()));
        list.add(String.format("%-11.11s", contrato.getContratante().getCelular().trim().replaceAll("[\\(\\)\\-]", "")));
        list.add(String.format("%-20.20s", contrato.getContratante().getEndereco().getLogradouro()));
        list.add(String.format("%06d", contrato.getContratante().getEndereco().getNumero()));
        list.add(String.format("%-10.10s", contrato.getContratante().getEndereco().getComplemento()));
        list.add(String.format("%-15.15s", contrato.getContratante().getEndereco().getBairro().toUpperCase()));
        list.add(String.format("%-30.30s", contrato.getContratante().getEndereco().getCidade().toUpperCase()));
        list.add(contrato.getContratante().getEndereco().getUf().name());
        list.add(String.format("%-8.8s", contrato.getContratante().getEndereco().getCep().replaceAll("[\\.\\-]", "")));
        list.add(contrato.getContratante().getEndereco().getPais().getValor());
        list.add(String.format("%010d", contrato.getProtocolo()));
        list.add(contrato.getAgendamento().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        list.add(contrato.getAgendamento().format(DateTimeFormatter.ofPattern("HHmm")));
        list.add(contrato.getTipoServico().getTipo());
        list.add(String.format("%08d", Math.round(contrato.getValorServico() * 100)));
        list.add(contrato.getTipoNotificacao().getValor());
        String cadastro = String.join("", list);

        String nomeArquivo = String.format("%s", "agua-luz-output.txt");
        File diretorio = new File("C:\\Users\\ruben\\IdeaProjects\\MJV");
        if (!diretorio.exists())
            diretorio.mkdirs();

        File arquivo = new File(diretorio, nomeArquivo);
        Path path = arquivo.toPath();
        Files.writeString(path, cadastro + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        //Alternativa para ajuntar os elementos da lista:
        // list.stream().map(Object::toString).collect(Collectors.joining(""));
        // return String.join("", list);
    }

    public static String formatarCpf(String cpf) {
        return Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})").matcher(cpf).replaceAll("$1.$2.$3-$4");
    }

    public static String formatarCep(String cep) {
        return Pattern.compile("(\\d{3})(\\d{3})(\\d{2})").matcher(cep).replaceAll("$1.$2-$3");
    }

    public static String formatarDataHora(String dataRelatorio) {
        DateTimeFormatter formatarStringParaData = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dataRelatorio,formatarStringParaData).format(formatarData);
    }

    public static String formatarValor(String valor) {
        Double d = Double.parseDouble(valor)/100;
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(d);
    }

    public static String formatarCelular(String celular) throws ParseException {
        MaskFormatter mf = new MaskFormatter("(##)#####-####");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(celular);
    }

    public static void exibirMensagem(StringBuilder sb, String celular) {
        ImageIcon icon = new ImageIcon("src/main/resources/mjv.png");
        JOptionPane.showInternalMessageDialog(null, sb,
                "MENSAGEM ENVIADA PARA: " + celular,
                JOptionPane.INFORMATION_MESSAGE, icon);
    }

}
