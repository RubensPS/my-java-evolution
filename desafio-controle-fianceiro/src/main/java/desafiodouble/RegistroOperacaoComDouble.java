package desafiodouble;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroOperacaoComDouble {
    private final LocalDate DIAOPERACAO;
    private final LocalTime HORAOPERACAO;
    private final TiposdeOperacao TIPO;
    private final Double VALOR;

    public RegistroOperacaoComDouble(TiposdeOperacao tipo, Double valor) {
        this.DIAOPERACAO = LocalDate.now();
        this.HORAOPERACAO = LocalTime.now();
        this.TIPO = tipo;
        this.VALOR = valor;
    }

    public LocalDate getDIAOPERACAO() {
        return DIAOPERACAO;
    }

    public LocalTime getHORAOPERACAO() {
        return HORAOPERACAO;
    }

    public TiposdeOperacao getTIPO() {
        return TIPO;
    }

    public Double getVALOR() {
        return VALOR;
    }

    @Override
    public String toString() {
        return "RegistroOperacaoComDouble{" +
                "DIAOPERACAO=" + DIAOPERACAO +
                ", HORAOPERACAO=" + HORAOPERACAO +
                ", TIPO=" + TIPO +
                ", VALOR=" + VALOR +
                '}';
    }
}
