import java.util.Scanner;

public class Entradas {
    static private Scanner teclado = new Scanner(System.in);

    static String texto(String mensagem) {
        System.out.print(mensagem);
        String text = teclado.nextLine();

        if (text.trim().equals("")) {
            System.out.println("Tem que ser apenas uma letra\n");
            return texto(mensagem);
        } else {
            return text;
        }
    }

    static int num(String mensagem) {
        try {
            int num = Integer.parseInt(texto(mensagem));
            return num;
        } catch (Exception e) {
            System.out.println("Número inválido\n");
            return num(mensagem);
        }
    }
}
