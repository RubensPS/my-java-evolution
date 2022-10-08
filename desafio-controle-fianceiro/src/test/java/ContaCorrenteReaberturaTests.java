import desafiodouble.ContaCorrenteDouble;
import static org.junit.jupiter.api.Assertions.*;

import desafiodouble.exceptions.ContaAtivaException;
import desafiodouble.exceptions.ContaPreviamenteCanceladaException;
import desafiodouble.exceptions.JustificativaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ContaCorrenteReaberturaTests {
    ContaCorrenteDouble conta01;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
    }

    @DisplayName("Teste de reabrirConta() com justificativa nula.")
    @Test
    void reabrirContaJustificativaNula() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        conta01.cancelarConta("Cancelamento para teste de reabertura.");
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.reabrirConta(null));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertTrue(conta01.getContaCancelada());
        assertEquals("Cancelamento para teste de reabertura.", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de reabrirConta() com justificativa vazia.")
    @Test
    void reabrirContaJustificativaVazia() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        conta01.cancelarConta("Cancelamento para teste de reabertura.");
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.reabrirConta(""));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertTrue(conta01.getContaCancelada());
        assertEquals("Cancelamento para teste de reabertura.", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de reabrirConta() com justificativa em branco.")
    @Test
    void reabrirContaJustificativaEmBranco() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        conta01.cancelarConta("Cancelamento para teste de reabertura.");
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.reabrirConta("      "));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertTrue(conta01.getContaCancelada());
        assertEquals("Cancelamento para teste de reabertura.", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de reabrirConta() com conta já ativa.")
    @Test
    void reabrirContaJaAtiva() {
        ContaAtivaException exception = assertThrows(ContaAtivaException.class, () -> conta01.reabrirConta("Teste conta já ativa."));
        assertEquals(String.format("A conta nº %d já se encontra ativa!", conta01.getNumeroConta()), exception.getMessage());
        assertFalse(conta01.getContaCancelada());
        assertEquals("Conta Ativa", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de reabrirConta() com sucesso.")
    @Test
    void reabrirContaSucesso() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException, ContaAtivaException {
        String justificativa = "Conta reaberta com sucesso.";
        conta01.cancelarConta("Cancelando para reabertura.");
        assertTrue(conta01.getContaCancelada());
        conta01.reabrirConta(justificativa);
        assertFalse(conta01.getContaCancelada());
        assertEquals(justificativa, conta01.getJustificativaCancelamento());
    }

}
