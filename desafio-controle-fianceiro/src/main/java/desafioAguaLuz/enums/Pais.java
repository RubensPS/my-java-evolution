package desafioAguaLuz.enums;

public enum Pais {
    BRASIL("BR"),
    ESTADO_UNIDOS("US"),
    FRANÇA("FR");

    String valor;

    Pais(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
