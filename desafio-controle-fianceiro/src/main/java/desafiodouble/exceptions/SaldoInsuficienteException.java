package desafiodouble.exceptions;

public class SaldoInsuficienteException extends Exception{

    public SaldoInsuficienteException(int numeroConta) {
        super(String.format("O saldo da conta nº %d é insuficiente para o saque!", numeroConta));
    }

}
