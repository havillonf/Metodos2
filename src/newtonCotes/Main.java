package src.newtonCotes;

import java.util.Scanner;

public class Main {
    public static double F(double x) {
        return 4*x + Math.pow(x, 3);
    }

    public static void executarMetodo(double a, double b, double tolerancia, int abordagem, int grau) {
        int numIteracoes = 0;
        double delta = 0;
        int N = 2;
        double resultado = 0;
        double resultadoAnterior = 0;
        double erro = 0;
        double Xi = 0;
        double Xf = 0;
        double integral;
        boolean executar = true;
        while(executar){
            System.out.println("Execução " + numIteracoes);
            integral = 0;
            numIteracoes++;
            delta = (b-a)/N;
            System.out.println("Delta: " + delta);
            for(int i = 0; i < N; i++){
                Xi = a + i*delta;
                Xf = Xi + delta;
                System.out.println("Xi: " + Xi);
                System.out.println("Xf: " + Xf);
                if (abordagem == 1) { // Fechada
                    switch (grau) {
                        case 1:
                            integral += fechada1(Xi, Xf);
                            break;
                        case 2:
                            integral += fechada2(Xi, Xf);
                            break;
                        case 3:
                            integral += fechada3(Xi, Xf);
                            break;
                        case 4:
                            integral += fechada4(Xi, Xf);
                            break;
                    }
                } else if (abordagem == 2) { // Aberta
                    switch (grau) {
                        case 1:
                            integral += aberta1(Xi, Xf);
                            break;
                        case 2:
                            integral += aberta2(Xi, Xf);
                            break;
                        case 3:
                            integral += aberta3(Xi, Xf);
                            break;
                        case 4:
                            integral += aberta4(Xi, Xf);
                            break;
                    }
                }
            }
            N *= 2;
            resultadoAnterior = resultado;
            resultado = integral;
            erro = Math.abs((resultado - resultadoAnterior)/resultado);
            System.out.println("Erro: " + erro);
            if(erro < tolerancia || numIteracoes > 10){
                executar = false;
            }
        }
        System.out.println("Integral: " + resultado);
        System.out.println("Iterações: " + numIteracoes);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double Xi, Xf;
        double tolerancia = 0.000001;
        int abordagem, grau;

        // Solicitar intervalos ao usuário
        System.out.print("Informe o valor de Xi (início do intervalo): ");
        Xi = scanner.nextDouble();
        System.out.println(Xi);

        System.out.print("Informe o valor de Xf (fim do intervalo): ");
        Xf = scanner.nextDouble();
        System.out.println(Xf);

        // Menu para selecionar abordagem
        System.out.println("Selecione a abordagem de integração:");
        System.out.println("1. Fechada");
        System.out.println("2. Aberta");
        System.out.print("Digite o número correspondente à abordagem: ");
        abordagem = scanner.nextInt();

        // Menu para selecionar o grau
        System.out.println("Selecione o grau da aproximação (1 a 4): ");
        grau = scanner.nextInt();

        executarMetodo(Xi, Xf, tolerancia, abordagem, grau);

        scanner.close();
    }

    public static double fechada1(double Xi, double Xf) { // N = 1
        return (Xf - Xi) / 2 * (F(Xi) + F(Xf));
    }

    public static double fechada2(double Xi, double Xf) { // N = 2
        double h = (Xf - Xi) / 2;
        return (h) / 3 * (F(Xi) + 4 * F(Xi + h) + F(Xi + 2 * h));
    }

    public static double fechada3(double Xi, double Xf) { // N = 3
        double h = (Xf - Xi) / 3;
        return (3 * h / 8) * (F(Xi) + 3 * F(Xi + h) + 3 * F(Xi + 2 * h) + F(Xi + 3 * h));
    }

    public static double fechada4(double Xi, double Xf) { // N = 4
        double h = (Xf - Xi) / 4;
        return (2 * h / 45) * (7 * F(Xi) + 32 * F(Xi + h) + 12 * F(Xi + 2 * h) + 32 * F(Xi + 3 * h) + 7 * F(Xi + 4 * h));
    }

    public static double aberta1(double Xi, double Xf) { // N = 1
        double h = (Xf - Xi) / 3;
        return (Xf - Xi) / 2 * (F(Xi + h) + F(Xi + 2 * h));
    }

    public static double aberta2(double Xi, double Xf) { // N = 2
        double h = (Xf - Xi) / 4;
        return (4 * h / 3) * (2 * F(Xi + h) - F(Xi + 2 * h) + 2 * F(Xi + 3 * h));
    }

    public static double aberta3(double Xi, double Xf) { // N = 3
        double h = (Xf - Xi) / 5;
        return (5 * (Xf - Xi) / 24) * (11 * F(Xi + h) + F(Xi + 2 * h) + F(Xi + 3 * h) + 11 * F(Xi + 4 * h));
    }

    public static double aberta4(double Xi, double Xf) { // N = 4
        double h = (Xf - Xi) / 6;
        return (6 * h / 20) * (11 * F(Xi + h) - 14 * F(Xi + 2 * h) + 26 * F(Xi + 3 * h) - 14 * F(Xi + 4 * h) + 11 * F(Xi + 5 * h));
    }
}
