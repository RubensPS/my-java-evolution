package desafioAguaLuz.entities;

public class Contratante {
    private String cpf;
    private String rg;
    private String nome;
    private String celular;
    private Endereco endereco;

    public Contratante(String cpf, String rg, String nome, String celular, Endereco endereco) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.celular = celular;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
