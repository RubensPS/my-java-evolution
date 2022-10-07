package desafiodouble;

import desafiodouble.exceptions.ContaCanceladaException;
import desafiodouble.exceptions.DepositoInvalidoException;
import desafiodouble.exceptions.SaldoInsuficienteException;

import java.time.LocalDate;

public class ContaDoubleApplication {
    public static void main(String[] args) {
        ContaCorrenteComDouble conta01 = new ContaCorrenteComDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        ContaCorrenteComDouble conta02 = new ContaCorrenteComDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 500.00);

        //sacar
        try {
            conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
            conta01.sacar(TiposdeOperacao.SAQUE, 1000.00);
            conta01.sacar(TiposdeOperacao.SAQUE, -1000.00);
            System.out.println(conta01.getSaldo());
        } catch (ContaCanceladaException | SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        //depósito
        try {
            conta01.depositar(TiposdeOperacao.DEPOSITO, 85.00);
            conta01.depositar(TiposdeOperacao.DEPOSITO, -85.00);
            conta01.depositar(TiposdeOperacao.DEPOSITO, null);
            System.out.println(conta01.getSaldo());

        } catch (ContaCanceladaException | DepositoInvalidoException e) {
            System.out.println(e.getMessage());
        }

        //transferir
        try {

        }catch (ContaCanceladaException | SaldoInsuficienteException | DepositoInvalidoException e) {
            System.out.println(e.getMessage());
        }


    }
}
