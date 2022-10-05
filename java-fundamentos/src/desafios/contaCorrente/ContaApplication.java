package desafios.contaCorrente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaApplication {
    public static void main(String[] args) {
        //criando as contas para teste
        ContaCorrente conta01 = new ContaCorrente("Rubens Souza", LocalDate.of(1984, 4, 23) , BigDecimal.valueOf(1000.00));
        ContaCorrente conta02 = new ContaCorrente("Grupo 06", LocalDate.of(2022, 10, 1) , BigDecimal.valueOf(500.00));

        //teste do saque
        System.out.println("TESTE sacar():\n" + "Saldo: " + conta01.getSaldo());
        conta01.sacar(BigDecimal.valueOf(100.00));
        System.out.println("Sacou R$100.00:\n" + "Saldo: " + conta01.getSaldo());
        System.out.println("Saque maior que saldo:");
        conta01.sacar(BigDecimal.valueOf(1000.00));
        System.out.println("Saldo: " + conta01.getSaldo());
        System.out.println("Saque negativo:");
        conta01.sacar(BigDecimal.valueOf(-1000.00));
        System.out.println("--------------------------------------------------------------------------------------");

        //teste de transferência
        System.out.println("Saldos:\n" + "conta01: " + conta01.getSaldo() + "\nconta02: " + conta02.getSaldo());
        conta01.transferirValor(BigDecimal.valueOf(130.00), conta02);
        System.out.println("Transferiu R$130.00\nconta01: " + conta01.getSaldo() + "\n" + "conta02: " + conta02.getSaldo());
        System.out.println("--------------------------------------------------------------------------------------");

        //teste de deposito
        System.out.println("TESTE depositar():\n" + "Saldo: " + conta01.getSaldo());
        conta01.depositar(BigDecimal.valueOf(85.00));
        System.out.println("Depositou R$85.00:\n" + "Saldo: " + conta01.getSaldo());
        System.out.println("Depósito negativo:");
        conta01.depositar(BigDecimal.valueOf(-85.00));
        //System.out.println("Depósito nulo:");
        //conta01.depositar(null);
        System.out.println("--------------------------------------------------------------------------------------");

        //teste do extrato
        System.out.println("Pegando extrato das duas contas:");
        System.out.print("conta01: ");
        conta01.pegarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.of(2022, 10,5, 23, 59));
        System.out.print("conta02: ");
        conta02.pegarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.of(2022, 10,5, 23, 59));
        System.out.println("Extrato com entrada de data nula:");
        conta02.pegarExtrato(null, LocalDateTime.now());
        System.out.println("Extrato com entrada de data final > data inicial:");
        conta02.pegarExtrato(LocalDateTime.now(), LocalDateTime.of(2022, 10,4, 0, 0));
        System.out.println("--------------------------------------------------------------------------------------");

        //teste de cancelamento
        System.out.println(conta01.getStatus());
        conta01.cancelarConta("Fechamento a pedido do cliente.");
        conta01.transferirValor(BigDecimal.valueOf(100.00), conta02);
        System.out.println("Cancelando conta já cancelada:");
        conta01.cancelarConta("Fechamento a pedido do cliente.");
        System.out.println("Sacando de conta cancelada:");
        conta01.sacar(BigDecimal.valueOf(100.00));
        System.out.println("Transferindo de conta cancelada:");
        conta01.transferirValor(BigDecimal.valueOf(130.00), conta02);
        System.out.println("Transferindo para conta cancelada:");
        conta02.transferirValor(BigDecimal.valueOf(130.00), conta01);
        System.out.println("Depositando de conta cancelada:");
        conta01.depositar(BigDecimal.valueOf(85.00));
        System.out.println("--------------------------------------------------------------------------------------");

        //verificando se foram realizadas operações com conta cancelada
        System.out.println("Pegando extrato das duas contas:");
        System.out.print("conta01: ");
        conta01.pegarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.of(2022, 10,5, 23, 59));
        System.out.print("conta02: ");
        conta02.pegarExtrato(LocalDateTime.of(2022, 10,4, 0, 0), LocalDateTime.of(2022, 10,5, 23, 59));
    }
}
