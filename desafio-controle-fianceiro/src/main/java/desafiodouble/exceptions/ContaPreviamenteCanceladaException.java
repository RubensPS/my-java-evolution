package desafiodouble.exceptions;

public class ContaPreviamenteCanceladaException extends Exception {
    public ContaPreviamenteCanceladaException(int conta) {
        super(String.format("A conta %d já se encontra cancelada!", conta));
    }
}
