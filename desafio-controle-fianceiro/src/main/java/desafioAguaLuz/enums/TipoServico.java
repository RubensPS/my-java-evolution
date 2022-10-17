package desafioAguaLuz.enums;

public enum TipoServico {
    LUZ("L", 132.15),
    AGUA("A", 137.21);

    String tipo;
    Double valor;

    TipoServico(String tipo, Double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Double getValor() {
        return this.valor;
    }

    public static String getServico(String tipoServico) {
        String servico = "";
        if (tipoServico.equals("A"))
            servico = "Água";
        if (tipoServico.equals("L"))
            servico = "Luz";
        return servico;
    }

}
