import desafiodouble.ContaCorrenteDouble;
import static org.junit.jupiter.api.Assertions.*;

import desafiodouble.exceptions.ContaPreviamenteCanceladaException;
import desafiodouble.exceptions.JustificativaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ContaCorrenteCancelarTests {
    ContaCorrenteDouble conta01;

    @BeforeEach
    void setUp() {
        conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
    }

    @DisplayName("Teste de cancelarConta() com justificativa nula.")
    @Test
    void cancelarContaJustificativaNula() {
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.cancelarConta(null));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertFalse(conta01.getContaCancelada());
        assertEquals("Conta Ativa", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de cancelarConta() com justificativa vazia.")
    @Test
    void cancelarContaJustificativaVazia() {
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.cancelarConta(""));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertFalse(conta01.getContaCancelada());
        assertEquals("Conta Ativa", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de cancelarConta() com justificativa em branco.")
    @Test
    void cancelarContaJustificativaEmBranco() {
        JustificativaInvalidaException exception = assertThrows(JustificativaInvalidaException.class, () -> conta01.cancelarConta("    "));
        assertEquals("Uma justificativa válida deve ser informada.", exception.getMessage());
        assertFalse(conta01.getContaCancelada());
        assertEquals("Conta Ativa", conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de cancelarConta() com conta já cancelada.")
    @Test
    void cancelarContaJaCancelada() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        String justificativa = "Testando a conta já cancelada.";
        conta01.cancelarConta(justificativa);
        assertTrue(conta01.getContaCancelada());
        ContaPreviamenteCanceladaException exception = assertThrows(ContaPreviamenteCanceladaException.class, () -> conta01.cancelarConta("Essa justificativa não deve ser salva."));
        assertEquals(String.format("A conta nº %d já se encontra cancelada!", conta01.getNumeroConta()), exception.getMessage());
        assertEquals(justificativa, conta01.getJustificativaCancelamento());
    }

    @DisplayName("Teste de cancelarConta() com sucesso.")
    @Test
    void cancelarContaSucesso() throws JustificativaInvalidaException, ContaPreviamenteCanceladaException {
        String justificativa = "Essa msg deve aparecer após cancelamento.";
        conta01.cancelarConta(justificativa);
        assertEquals(justificativa, conta01.getJustificativaCancelamento());
        assertTrue(conta01.getContaCancelada());

    }
}
