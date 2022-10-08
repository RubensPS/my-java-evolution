package desafiodouble;

import desafiodouble.exceptions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContaDoubleApplication {
    public static void main(String[] args) {
        ContaCorrenteDouble conta01 = new ContaCorrenteDouble("Rubens Souza", LocalDate.of(1984, 4, 23) , 1000.00);
        ContaCorrenteDouble conta02 = new ContaCorrenteDouble("Grupo 06", LocalDate.of(2022, 10, 1) , 1000.00);

        try {
            conta01.sacar(TiposdeOperacao.SAQUE, 100.00);
            conta01.depositar(TiposdeOperacao.DEPOSITO, 85.00);
            conta02.sacar(TiposdeOperacao.SAQUE, 100.00);
            conta02.depositar(TiposdeOperacao.DEPOSITO, 85.00);
            conta01.transferirValor(130.00, conta02);
        } catch (ContaCanceladaException | SaldoInsuficienteException | DepositoInvalidoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(conta01.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.of(2022,10,10), LocalTime.MAX)));
            System.out.println(conta02.consultarExtrato(LocalDateTime.of(LocalDate.of(2022,10,7),
                    LocalTime.MIN), LocalDateTime.of(LocalDate.of(2022,10,10), LocalTime.MAX)));
        }


    }
}
