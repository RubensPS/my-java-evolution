import desafiodouble.ContaCorrenteDouble;
import static org.junit.jupiter.api.Assertions.*;

import desafiodouble.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ContaCorrenteTranferirValorTests {
    ContaCorrenteDouble conta01;
    ContaCorrenteDouble conta02;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        conta02 = new ContaCorrenteDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 1000.00);
    }

    @DisplayName("Teste de transferir() com conta origem cancelada.")
    @Test
    void transferirContaOrigemCancelada() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        String justificativa = "Teste de transferência com conta origem cancelada.";
        conta01.cancelarConta(justificativa);
        ContaCanceladaException exception = assertThrows(ContaCanceladaException.class, () -> conta01.transferirValor( 100.00, conta02));
        assertEquals(String.format("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.", conta01.getNumeroConta(), conta01.getNomeCliente()), exception.getMessage());
        assertEquals(justificativa, conta01.getJustificativaCancelamento());
        assertFalse(conta02.getContaCancelada());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com conta destino cancelada.")
    @Test
    void transferirContaDestinoCancelada() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        String justificativa = "Teste de transferência com conta destino cancelada.";
        conta02.cancelarConta(justificativa);
        ContaCanceladaException exception = assertThrows(ContaCanceladaException.class, () -> conta01.transferirValor( 100.00, conta02));
        assertEquals(String.format("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.", conta02.getNumeroConta(), conta02.getNomeCliente()), exception.getMessage());
        assertEquals(justificativa, conta02.getJustificativaCancelamento());
        assertFalse(conta01.getContaCancelada());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com valor nulo.")
    @Test
    void transferirValorNulo() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> conta01.transferirValor( null, conta02));
        assertEquals("O valor do saque não pode ser nulo.", exception.getMessage());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com valor negativo.")
    @Test
    void transferirValorNegativo() {
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.transferirValor( -100.00, conta02));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com valor zero.")
    @Test
    void transferirValorZero() {
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.transferirValor( 0.0, conta02));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com valor maior que saldo da conta origem.")
    @Test
    void transferirValorMaiorQueSaldo() {
        Double valor = 1200.00;
        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> conta01.transferirValor(valor, conta02));
        assertEquals(String.format("O saldo da conta nº %d é insuficiente para o saque!", conta01.getNumeroConta()), exception.getMessage());
        assertTrue(valor > conta01.getSaldo());
        assertEquals(0, conta01.getHistoricoTransacoes().size());
        assertEquals(0, conta02.getHistoricoTransacoes().size());
        assertEquals(1000, conta01.getSaldo());
        assertEquals(1000, conta02.getSaldo());
    }

    @DisplayName("Teste de transferir() com valor positivo menor que o saldo da conta de origem.")
    @Test
    void transferirValorPositivoMaiorQueSaldo() throws SaldoInsuficienteException, ContaCanceladaException, DepositoInvalidoException {
        Double valor = 100.00;
        conta01.transferirValor(valor, conta02);
        assertTrue(conta01.getSaldo() > valor);
        assertEquals(900.00, conta01.getSaldo());
        assertEquals(1, conta01.getHistoricoTransacoes().size());
        assertEquals(valor, conta01.getHistoricoTransacoes().get(0).getValor());
        assertEquals(1100.00, conta02.getSaldo());
        assertEquals(1, conta02.getHistoricoTransacoes().size());
        assertEquals(100.00, conta02.getHistoricoTransacoes().get(0).getValor());
    }
}
