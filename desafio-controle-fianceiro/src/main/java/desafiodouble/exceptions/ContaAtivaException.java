package desafiodouble.exceptions;

public class ContaAtivaException extends Exception {
    public ContaAtivaException(int conta) {
        super(String.format("A conta %d já se encontra ativa!", conta));
    }
}
