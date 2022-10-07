import desafiodouble.ContaCorrenteDouble;
import desafiodouble.TiposdeOperacao;
import desafiodouble.exceptions.ContaCanceladaException;
import desafiodouble.exceptions.ContaPreviamenteCanceladaException;
import desafiodouble.exceptions.DepositoInvalidoException;
import desafiodouble.exceptions.JustificativaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContaCorrenteDepositarTests {

    ContaCorrenteDouble conta01;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
    }

    @DisplayName("Teste de depositar() com conta cancelada.")
    @Test
    void depositarContaCancelada() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        conta01.cancelarConta("Teste de deposito com conta cancelada.");
        ContaCanceladaException exception = assertThrows(ContaCanceladaException.class, () -> conta01.depositar(TiposdeOperacao.DEPOSITO, 100.00));
        assertEquals(String.format("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.", conta01.getNumeroConta(), conta01.getNomeCliente()), exception.getMessage());
        assertEquals("Teste de deposito com conta cancelada.", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de depositar() com valor igual a zero")
    @Test
    void depositarValorZero() {
        Double valor = 0.00;
        DepositoInvalidoException exception = assertThrows(DepositoInvalidoException.class, () -> conta01.depositar(TiposdeOperacao.DEPOSITO, valor));
        assertEquals(String.format("Valor de depósito inválido. Valor: %.2f", valor), exception.getMessage());
    }

    @DisplayName("Teste de depositar() com valor negativo")
    @Test
    void depositarValorNegativo() {
        Double valor = -100.00;
        DepositoInvalidoException exception = assertThrows(DepositoInvalidoException.class, () -> conta01.depositar(TiposdeOperacao.DEPOSITO, valor));
        assertEquals(String.format("Valor de depósito inválido. Valor: %.2f", valor), exception.getMessage());
    }

    @DisplayName("Teste de depositar() com valor nulo")
    @Test
    void depositarValorNulo() {
        Double valor = null;
        NullPointerException exception = assertThrows(NullPointerException.class, () -> conta01.depositar(TiposdeOperacao.DEPOSITO, valor));
        assertEquals(String.format("O valor do depósito não pode ser nulo."), exception.getMessage());
    }

    @DisplayName("Teste de depositar() com valor positivo")
    @Test
    void depositarValorPositivo() throws ContaCanceladaException, DepositoInvalidoException {
        conta01.depositar(TiposdeOperacao.DEPOSITO, 100.00);
        assertEquals(1100.00, conta01.getSaldo());
        assertEquals(1, conta01.getHistoricoTransacoes().size());
        assertEquals(100.00, conta01.getHistoricoTransacoes().get(0).getValor());
    }

}
