package desafioAguaLuz.enums;

public enum TipoNotificacao {
    SMS("S"), WHATS("W");

    String valor;

    TipoNotificacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
