import desafiodouble.ContaCorrenteDouble;
import static org.junit.jupiter.api.Assertions.*;

import desafiodouble.TiposdeOperacao;
import desafiodouble.exceptions.ContaCanceladaException;
import desafiodouble.exceptions.DepositoInvalidoException;
import desafiodouble.exceptions.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContaCorrenteConsultarExtratoTests {
    ContaCorrenteDouble conta01;
    ContaCorrenteDouble conta02;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        conta02 = new ContaCorrenteDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 1000.00);

        //
    }

    @DisplayName("Teste de consultarExtrato() com data inicial nula.")
    @Test
    void consultarExtratoDataInicioNula() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> conta01.consultarExtrato(null, LocalDateTime.now().plusHours(1)));
        assertEquals("As datas de início e fim não podem ser nulas.", exception.getMessage());
    }

    @DisplayName("Teste de consultarExtrato() com data final nula.")
    @Test
    void consultarExtratoDataFinalNula() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> conta01.consultarExtrato(LocalDateTime.now().plusHours(1), null));
        assertEquals("As datas de início e fim não podem ser nulas.", exception.getMessage());
    }

    @DisplayName("Teste de consultarExtrato() com data inicial posterior a final.")
    @Test
    void consultarExtratoDataInicioPosteriorFim() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> conta01.consultarExtrato(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusHours(1)));
        assertEquals("A data inicial precisa ser inferior à data final.", exception.getMessage());
    }

    @DisplayName("Teste de consultarExtrato() com sucesso.")
    @Test
    void consultarExtratoSucesso() throws SaldoInsuficienteException, ContaCanceladaException, DepositoInvalidoException {
        conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
        conta01.depositar(TiposdeOperacao.DEPOSITO, 200.00);
        conta02.sacar(TiposdeOperacao.SAQUE, 300.00);
        conta01.transferirValor(60.00, conta02);
        assertEquals(3, conta01.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MIN),
                LocalDateTime.of(LocalDate.of(2022,10,12), LocalTime.MAX)).size());
        assertEquals(2, conta02.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MIN),
                LocalDateTime.of(LocalDate.of(2022,10,12), LocalTime.MAX)).size());
    }
}
