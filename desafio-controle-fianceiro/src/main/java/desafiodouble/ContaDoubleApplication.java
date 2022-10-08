package desafiodouble;

import desafiodouble.exceptions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContaDoubleApplication {
    public static void main(String[] args) {
        ContaCorrenteDouble conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        ContaCorrenteDouble conta02 = new ContaCorrenteDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 1000.00);

        //cancelamento
//        try {
//            conta01.cancelarConta("");
//            conta01.cancelarConta("     ");
//            conta01.cancelarConta(null);
//            conta01.cancelarConta("Fechamento a pedido do cliente.");
//            conta01.cancelarConta("Não vai ser alterada.");
//            conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
//            conta01.depositar(TiposdeOperacao.DEPOSITO, 60.00);
//            conta01.transferirValor(30.00, conta02);
//            conta02.transferirValor(70.00, conta01);
//        } catch (JustificativaInvalidaException | ContaPreviamenteCanceladaException | ContaCanceladaException | SaldoInsuficienteException | DepositoInvalidoException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.printf("conta01: %.2f - conta02: %.2f%n", conta01.getSaldo(), conta02.getSaldo());
//            System.out.println(conta01.getContaCancelada() + "\n" + conta01.getJustificativaCancelamento());
//        }
//
        //reabertura
//        try {
//            conta01.reabrirConta("Reabertura para empréstimo imobiliário.");
//            conta01.reabrirConta(null);
//            conta01.reabrirConta("");
//            conta01.reabrirConta("   ");
//            conta01.reabrirConta("Não vai reabrir, conta aberta.");
//            conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
//            conta01.depositar(TiposdeOperacao.DEPOSITO, 60.00);
//            conta01.transferirValor(30.00, conta02);
//            conta02.transferirValor(70.00, conta01);
//        } catch (JustificativaInvalidaException | ContaAtivaException | ContaCanceladaException | SaldoInsuficienteException | DepositoInvalidoException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.printf("conta01: %.2f - conta02: %.2f%n", conta01.getSaldo(), conta02.getSaldo());
//            System.out.println(conta01.getContaCancelada() + "\n" + conta01.getJustificativaCancelamento());
//        }

        //extrato
        try {
            conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
            conta01.depositar(TiposdeOperacao.DEPOSITO, 85.00);
            conta02.sacar(TiposdeOperacao.SAQUE, 100.00);
            conta02.depositar(TiposdeOperacao.DEPOSITO, 85.00);
            conta01.transferirValor(130.00, conta02);
        } catch (ContaCanceladaException | SaldoInsuficienteException | DepositoInvalidoException e) {
            System.out.println(e.getMessage());
        } finally {
            conta01.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MIN), LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MAX));
            conta02.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MIN), LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MAX));
        }


    }
}
