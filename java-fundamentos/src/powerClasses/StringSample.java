package powerClasses;

public class StringSample {

    static void stringConcat() {
        String nome = "Rubens";
        String sobrenome = "Souza";
        String nomeCompleto = nome.concat(" ").concat(sobrenome);
        System.out.println(nomeCompleto);
    }
    public static void main(String[] args) {
    stringConcat();
    }
}
