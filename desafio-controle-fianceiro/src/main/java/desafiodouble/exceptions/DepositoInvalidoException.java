package desafiodouble.exceptions;

public class DepositoInvalidoException extends Exception{

    public DepositoInvalidoException(Double valor) {
        super(String.format("Valor de depósito inválido. Valor: %.2f", valor));
    }

}
