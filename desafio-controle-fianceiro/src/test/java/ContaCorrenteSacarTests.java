import desafiodouble.ContaCorrenteDouble;
import desafiodouble.TiposdeOperacao;
import desafiodouble.exceptions.ContaCanceladaException;
import desafiodouble.exceptions.ContaPreviamenteCanceladaException;
import desafiodouble.exceptions.JustificativaInvalidaException;
import desafiodouble.exceptions.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContaCorrenteSacarTests {

    ContaCorrenteDouble conta01;
    ContaCorrenteDouble conta02;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        conta02 = new ContaCorrenteDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 1000.00);
    }

    @DisplayName("Teste de sacar() com conta cancelada")
    @Test
    void saqueContaCancelada() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException, SaldoInsuficienteException, ContaCanceladaException {
        conta01.cancelarConta("Teste de saque com conta cancelada.");
        ContaCanceladaException exception = assertThrows(ContaCanceladaException.class, () -> conta01.sacar(TiposdeOperacao.SAQUE, 100.00));
        assertEquals(String.format("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.", conta01.getNumeroConta(), conta01.getNomeCliente()), exception.getMessage());
    }

    @DisplayName("Teste de sacar() com valor nulo")
    @Test
    void saqueNuloTest() {
       NullPointerException exception = assertThrows(NullPointerException.class, () -> conta01.sacar(TiposdeOperacao.SAQUE, null));
        assertEquals("O valor do saque não pode ser nulo.", exception.getMessage());
    }

    @DisplayName("Teste de sacar() com valor zero")
    @Test
    void saqueZero() {
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.sacar(TiposdeOperacao.SAQUE, 0.0));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
    }

    @DisplayName("Teste de sacar() com valor negativo")
    @Test
    void saqueNegativo() {
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.sacar(TiposdeOperacao.SAQUE, -100.0));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
    }

    @DisplayName("Teste de sacar() com valor maior que o saldo")
    @Test
    void saqueValorMaiorQueSaldo() {
        Double valor = 1200.00;
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.sacar(TiposdeOperacao.SAQUE, valor));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
        assertTrue(valor > conta01.getSaldo());
    }

    @DisplayName("Teste de sacar() com valor positivo")
    @Test
    void saquePositivo() throws SaldoInsuficienteException, ContaCanceladaException {
        Double valor = 100.00;
        conta01.sacar(TiposdeOperacao.SAQUE, valor);
        assertTrue(conta01.getSaldo() > valor);
        assertEquals(900.00, conta01.getSaldo());
        assertEquals(1, conta01.getHistoricoTransacoes().size());
        assertEquals(100, conta01.getHistoricoTransacoes().get(0).getValor());
    }




}
