package desafiodouble;

import java.time.LocalDateTime;

public class OperacaoComDouble {
    private final LocalDateTime HORAOPERACAO;
    private final TiposdeOperacao TIPO;
    private final Double VALOR;

    public OperacaoComDouble(TiposdeOperacao tipo, Double valor) {
        this.HORAOPERACAO = LocalDateTime.now();
        this.TIPO = tipo;
        this.VALOR = valor;
    }

    public Double getValor() {
        return VALOR;
    }

    public LocalDateTime getHoraOperacao() {
        return HORAOPERACAO;
    }

    public TiposdeOperacao getTipo() {
        return TIPO;
    }
}
