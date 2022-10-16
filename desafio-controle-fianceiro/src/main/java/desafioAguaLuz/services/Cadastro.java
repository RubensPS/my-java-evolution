package desafioAguaLuz.services;

import desafioAguaLuz.entities.ContratoInstalacao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    public static String buscarCadastro(String path, Integer protocolo) {
        return "";
    }

    public static void salvarCadastro(ContratoInstalacao contrato) {
        StringBuilder sb = new StringBuilder();

        String cpf = contrato.getContratante().getCpf().replaceAll("[\\.\\-]", "");
        String rg = String.format("%10.10s", contrato.getContratante().getRg().replaceAll("[\\-]", ""));
        String nome = String.format("%30.30s", contrato.getContratante().getNome().toUpperCase());
        String celular = contrato.getContratante().getCelular().replaceAll("[\\(\\)\\-]", "");
        String logradouro = String.format("%20.20s", contrato.getContratante().getEndereco().getLogradouro());
        String numero = String.format("%06d", contrato.getContratante().getEndereco().getNumero());
        String complemento = String.format("%-30.10s", contrato.getContratante().getEndereco().getComplemento());
        String bairro = contrato.getContratante().getEndereco().getBairro().toUpperCase();
        String cidade = contrato.getContratante().getEndereco().getCidade().toUpperCase();
        String uf = contrato.getContratante().getEndereco().getUf().name();
        String cep = contrato.getContratante().getCpf().replaceAll("[\\.]", "");
        String pais = contrato.getContratante().getEndereco().getPais().name();
        String protocolo = String.format("%-10.10d", contrato.getProtocolo());
        String data = contrato.getAgendamento().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hora = contrato.getAgendamento().format(DateTimeFormatter.ofPattern("HHmm"));
        String tipoServico = contrato.getTipoServico().getTipo();
        String valorServico = String.valueOf(contrato.getValorServico()).replace(".", "");
        String tipoNotificacao = contrato.getTipoNotificacao().getValor();

        sb.append(cpf.concat(rg).concat(nome).concat(celular).concat(logradouro).concat(numero).concat(complemento)
                .concat(bairro).concat(cidade).concat(cidade).concat(uf).concat(cep).concat(pais).concat(protocolo)
                .concat(data).concat(hora).concat(tipoServico).concat(valorServico).concat(tipoNotificacao));

    }

    public static void salvarCadastro2(ContratoInstalacao contrato) throws IOException {
        List<String> list = new ArrayList<>();

        list.add(String.format("%-11.11s", contrato.getContratante().getCpf().replaceAll("[\\.\\-]", "")));
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
        list.add(contrato.getContratante().getEndereco().getPais().name());
        list.add(String.format("%010d", contrato.getProtocolo()));
        list.add(contrato.getAgendamento().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        list.add(contrato.getAgendamento().format(DateTimeFormatter.ofPattern("HHmm")));
        list.add(contrato.getTipoServico().getTipo());
        list.add(String.format("%08d", Math.round(contrato.getValorServico() * 100)));
        list.add(contrato.getTipoNotificacao().getValor());

        // list.stream().map(Object::toString).collect(Collectors.joining(""));
        // return String.join("", list);

        String cadastro = String.join("", list);
        String nomeArquivo = String.format("%s", "agua-luz-output.txt");
        File diretorio = new File("C:\\Users\\ruben\\IdeaProjects\\MJV");
        if (!diretorio.exists())
            diretorio.mkdirs();

        File arquivo = new File(diretorio, nomeArquivo);
        Path path = arquivo.toPath();
        Files.writeString(path, cadastro + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

}
