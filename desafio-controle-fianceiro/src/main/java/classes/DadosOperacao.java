package classes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DadosOperacao {
    private LocalDateTime horaOperacao;
    private String tipo;
    private BigDecimal valor;

    public DadosOperacao(String tipo, BigDecimal valor) {
        this.horaOperacao = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDateTime getHoraOperacao() {
        return horaOperacao;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "DadosOperacao{" +
                "horaOperacao=" + horaOperacao +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                '}';
    }
}
