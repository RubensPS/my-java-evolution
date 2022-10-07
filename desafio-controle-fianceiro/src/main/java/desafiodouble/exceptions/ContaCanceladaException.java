package desafiodouble.exceptions;

public class ContaCanceladaException extends Exception{

    public ContaCanceladaException(int numeroConta, String nomeCliente) {
        super(String.format("A conta nº %d, em nome de %s foi cancelada. Reabra a conta para utilizar.%n", numeroConta, nomeCliente));
    }

}
