package desafiobigdecimal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaApplication {
    public static void main(String[] args) {

        ContaCorrente conta01 = new ContaCorrente("Rubens Souza", LocalDate.of(1984, 4, 23) , BigDecimal.valueOf(1000.00));
        ContaCorrente conta02 = new ContaCorrente("Grupo 06", LocalDate.of(2022, 10, 1) , BigDecimal.valueOf(500.00));

        conta01.sacar(BigDecimal.valueOf(100.00));
        conta01.sacar(BigDecimal.valueOf(1000.00));
        conta01.sacar(BigDecimal.valueOf(-1000.00));
        System.out.println(conta01.getSaldo());

        conta01.depositar(BigDecimal.valueOf(85.00));
        conta01.depositar(BigDecimal.valueOf(-85.00));
        conta01.depositar(null);
        System.out.println(conta01.getSaldo());

        conta01.transferirValor(BigDecimal.valueOf(130.00), conta02);
        conta01.consultarExtrato(LocalDateTime.of(2022, 10,4, 0, 0),LocalDateTime.now().plusHours(1));
        conta02.consultarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.now().plusHours(1));
        conta02.consultarExtrato(null, LocalDateTime.now());
        conta02.consultarExtrato(LocalDateTime.now().plusDays(1), LocalDateTime.of(2022, 10,4, 0, 0));

        conta01.cancelarConta("Fechamento a pedido do cliente.");
        conta01.cancelarConta("Fechamento a pedido do cliente.");
        conta01.sacar(BigDecimal.valueOf(100.00));
        conta01.transferirValor(BigDecimal.valueOf(130.00), conta02);
        conta02.transferirValor(BigDecimal.valueOf(130.00), conta01);
        conta01.depositar(BigDecimal.valueOf(85.00));
        System.out.println(conta01.getStatus() + "\n" + conta01.getJustificativaCancelamento());

        conta01.reabrirConta("Cliente quitou o débito.");
        conta01.reabrirConta(null);
        conta01.reabrirConta("      ");
        conta01.reabrirConta("");
        conta01.reabrirConta("Reabertura sem mootivo, erro.");
        System.out.println(conta01.getStatus() + "\n" + conta01.getJustificativaCancelamento());

        conta01.consultarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.now().plusHours(1));
        conta02.consultarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.now().plusHours(1));
    }
}
