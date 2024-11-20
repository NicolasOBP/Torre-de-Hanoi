public class App {
    static int jogadas = 0;
    static int posicao1 = 0;
    static int posicao2 = -1;
    static int minJogadas = 0;
    static String pesosFixos[];
    static long time, tempoJogo;

    public static void main(String[] args) throws Exception {
        System.out.println("Bem vindo a Torre de Hanói!\n");
        System.out.println("Para ganhar, a Torre 1 precisa ser passada para a Torre 3");

        Comeca(Entradas.num("Quantos pesos você deseja que a torre possua: "));
    }

    public static void Comeca(int qtdPesos) {
        int torre1[] = MontaTorre(qtdPesos);
        int torre2[] = new int[qtdPesos];
        int torre3[] = new int[qtdPesos];

        time = System.currentTimeMillis();

        minJogadas = QtdeJogadas(qtdPesos);

        System.out.println("Começou o jogo, o número minimo de jogadas que você pode fazer para ganhar é "
                + minJogadas + "\n");

        MostraTorre(torre1, torre2, torre3);

        for (int i = 0; i < 1; i--) {
            PerguntaTorre(torre1, torre2, torre3);

            jogadas++;

            MostraTorre(torre1, torre2, torre3);

            TestaGanhador(torre3);

            System.out.println("\nJogadas feitas: " + jogadas + "\n");
        }
    }

    public static void PerguntaTorre(int torre1[], int torre2[], int torre3[]) {
        boolean dowhile = true;

        do {
            int move = Entradas.num("Qual torre você quer pegar o peso? (1, 2, 3): ");

            switch (move) {
                case 1:
                    if (TestaTorre(torre1, torre2, torre3)) {
                        System.out.println("Torre escolhida inválida, escolha outra\n");
                    } else {
                        MovePeso(torre1, torre1, torre2, torre3, move);

                        dowhile = false;
                    }
                    break;
                case 2:
                    if (TestaTorre(torre2, torre2, torre3)) {
                        System.out.println("Torre escolhida inválida, escolha outra\n");
                    } else {
                        MovePeso(torre2, torre1, torre2, torre3, move);

                        dowhile = false;
                    }
                    break;
                case 3:
                    if (TestaTorre(torre3, torre2, torre3)) {
                        System.out.println("Torre escolhida inválida, escolha outra\n");
                    } else {
                        MovePeso(torre3, torre1, torre2, torre3, move);

                        dowhile = false;
                    }
                    break;

                default:
                    System.out.println("Número inválido\n");
            }
        } while (dowhile);
    }

    public static boolean TestaTorre(int torre[], int torre2[], int torre3[]) {
        int p1 = ColocaPeso.Peso(torre, 0);
        int p2 = ColocaPeso.Peso(torre2, 100);
        int p3 = ColocaPeso.Peso(torre3, 100);

        if (torre[torre.length - 1] == 0 || p1 > p2 && p1 > p3) {
            return true;
        } else {
            return false;
        }
    }

    public static void MovePeso(int torre[], int torre1[], int torre2[], int torre3[], int t) {
        boolean dowhile = true;

        do {
            int t2 = Entradas.num("\nPara qual torre será movida a torre " + t + ": ");

            if (t2 == t) {
                System.out.println("Não pode escolher mesma torre");

            } else {
                switch (t2) {
                    case 1:
                        if (TestaTorrePesos(torre, torre1)) {
                            System.out.println("Peso da torre" + t2 + " é menor que a torre " + t + " escolha outra");
                        } else {
                            switch (t) {
                                case 2:
                                    torre1[posicao2] = torre2[posicao1];
                                    torre2[posicao1] = 0;

                                    break;
                                case 3:
                                    torre1[posicao2] = torre3[posicao1];
                                    torre3[posicao1] = 0;
                            }

                            dowhile = false;
                        }
                        break;
                    case 2:
                        if (TestaTorrePesos(torre, torre2)) {
                            System.out.println("Peso da torre" + t2 + " é menor que a torre " + t + " escolha outra");
                        } else {
                            switch (t) {
                                case 1:
                                    torre2[posicao2] = torre1[posicao1];
                                    torre1[posicao1] = 0;

                                    break;
                                case 3:
                                    torre2[posicao2] = torre3[posicao1];
                                    torre3[posicao1] = 0;
                            }

                            dowhile = false;
                        }
                        break;
                    case 3:
                        if (TestaTorrePesos(torre, torre3)) {
                            System.out.println("Peso da torre " + t2 + " é menor que a torre " + t + " escolha outra");
                        } else {
                            switch (t) {
                                case 1:
                                    torre3[posicao2] = torre1[posicao1];
                                    torre1[posicao1] = 0;

                                    break;
                                case 2:
                                    torre3[posicao2] = torre2[posicao1];
                                    torre2[posicao1] = 0;
                            }
                            dowhile = false;
                        }
                        break;

                    default:
                        System.out.println("Número inválido, escolha outro");
                        break;
                }
            }
        } while (dowhile);
    }

    public static boolean TestaTorrePesos(int t[], int t2[]) {
        int peso1 = 0;
        int peso2 = 0;

        posicao1 = 0;
        posicao2 = -1;

        for (int i : t) {
            if (i != 0) {
                peso1 = i;
                break;
            }

            posicao1++;
        }

        for (int i : t2) {
            if (i != 0) {
                peso2 = i;
                break;
            }

            posicao2++;
        }

        if (peso1 < peso2 || peso2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void TestaGanhador(int torre3[]) {
        if (torre3[0] != 0) {
            tempoJogo = System.currentTimeMillis();

            tempoJogo = (tempoJogo - time) / 1000;

            System.out.print("\nParabéns, você ganhou em " + tempoJogo + " segundos");

            if (jogadas > minJogadas) {
                System.out.println(
                        ", mas dava para ser melhor, vovê fez " + jogadas + " jogadas e o mínimo era de " + minJogadas);
            } else {
                System.out.println(" com " + minJogadas + " jogadas! O menor possível!!");
            }
            System.exit(0);
        }
    }

    public static void MostraTorre(int torre1[], int torre2[], int torre3[]) {
        String tab = torre1.length < 4 ? "\t\t|" : "\t|";

        System.out.println(
                "Torre 1" + pesosFixos[0] + (torre1.length < 4 ? "\t" : "") + "Torre 2" + pesosFixos[0]
                        + "Torre 3");

        for (int i = 0; i < pesosFixos.length - 1; i++) {
            System.out.print(pesosFixos[torre1[i]]);

            System.out.print(tab + pesosFixos[torre2[i]] + "\t| ");

            System.out.print(pesosFixos[torre3[i]] + "\t");

            System.out.println();
        }
    }

    public static int QtdeJogadas(int qtde) {
        int qtdeJogadas = 1;

        for (int i = 0; i < qtde; i++) {
            qtdeJogadas *= 2;
        }

        return qtdeJogadas - 1;
    }

    public static int[] MontaTorre(int qtdPesos) {
        int torre[] = new int[qtdPesos];
        String discos[] = new String[qtdPesos + 1];

        for (int i = 0; i < discos.length; i++) {
            if (i == 0)
                discos[i] = " ".repeat(qtdPesos * 2 + 1);
            else {
                discos[i] = " ".repeat(((qtdPesos * 2 + 1) - (i * 2 + 1)) / 2) + "-".repeat(i * 2 + 1)
                        + " ".repeat(((qtdPesos * 2 + 1) - (i * 2 + 1)) / 2);
                torre[i - 1] = i;
            }
        }

        pesosFixos = discos.clone();
        return torre;
    }
}
