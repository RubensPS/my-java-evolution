package powerClasses;

public class StringSample {

    static void stringConcat() {
        String nome = "Rubens";
        String sobrenome = "Souza";
        String nomeCompleto = nome.concat(" ").concat(sobrenome);
        System.out.println(nomeCompleto);
    }

    static void stringFormat(String nome, Float salario) {
        StringBuilder conteudo = new StringBuilder();
        conteudo.append(String.format("Meu nome é %s e meu salário é %.2f", nome, salario));
        System.out.println(conteudo);
    }

    public static void main(String[] args) {

    }


}
