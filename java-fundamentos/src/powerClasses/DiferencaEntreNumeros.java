package powerClasses;

public class DiferencaEntreNumeros {

    public static int diferenca(int num01, int num02) throws NumberOutOfBoundException{
        if (num01 < 0 || num02 > 10) throw new NumberOutOfBoundException();
        return Math.subtractExact(Math.max(num01, num02), Math.min(num01, num02));
    }

    public static void main(String[] args) {
        try {
            System.out.println(diferenca(2, 6));
        }catch (NumberOutOfBoundException e) {
            System.out.println("Erro: " + e);
        }

        try {
            System.out.println(diferenca(-1, 7));
        }catch (NumberOutOfBoundException e) {
            System.out.println("Erro: " + e);

        }try {
            System.out.println(diferenca(3,16));
        }catch (NumberOutOfBoundException e) {
            System.out.println("Erro: " + e);

        }try {
            System.out.println(diferenca(-2, 14));
        }catch (NumberOutOfBoundException e) {
            System.out.println("Erro: " + e);
        }




    }
}
