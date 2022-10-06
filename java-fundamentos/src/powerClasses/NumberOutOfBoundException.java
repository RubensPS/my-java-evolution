package powerClasses;

public class NumberOutOfBoundException extends Exception{
    public NumberOutOfBoundException() {
        super("Os números estão fora do intervalo aceito.");
    }
}
