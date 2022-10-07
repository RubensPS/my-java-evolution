package desafiodouble.exceptions;

public class JustificativaInvalidaException extends Exception{
    public JustificativaInvalidaException() {
        super("Uma justificativa válida deve ser informada.");
    }
}
