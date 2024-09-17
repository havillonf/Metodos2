package src.gaussLegendre;
import java.util.Scanner;
import java.lang.Math;

public class GaussLegendre {

    public static double f(double x) {
        return 4*x + Math.pow(x, 3);
    }

    public static double x(double sk, double xi, double xf) {
        return (xi + xf) / 2 + ((xf - xi) / 2) * sk;
    }

    public static void gaussLegendre2Pontos(double a, double b, double erroEstimado) {
        double s = Math.sqrt(1.0 / 3);
        double[] raizes = {s, -s};
        double w = 1;
        double[] pesosW = {w, w};

        executarMetodo(2, pesosW, raizes, erroEstimado, a, b);
    }

    public static void gaussLegendre3Pontos(double a, double b, double erroEstimado) {
        double s = Math.sqrt(3.0 / 5);
        double[] raizes = {s, 0, -s};
        double w = 5.0 / 9;
        double w2 = 8.0 / 9;
        double[] pesosW = {w, w2, w};

        executarMetodo(3, pesosW, raizes, erroEstimado, a, b);
    }

    public static void gaussLegendre4Pontos(double a, double b, double erroEstimado) {
        double[] raizes = {0.861136, -0.861136, 0.339981, -0.339981};
        double w = 0.34785;
        double w3 = 0.65214;
        double[] pesosW = {w, w, w3, w3};

        executarMetodo(4, pesosW, raizes, erroEstimado, a, b);
    }

    public static void executarMetodo(int qtdPontos, double[] pesosW, double[] raizes, double tolerancia, double a, double b) {
        double delta;
        double Xi;
        double Xf;
        double erro = 0;
        double resultadoAnterior = 0;
        double resultado = 0;
        double resultadoAux;
        int N = 1;
        int interacoes = 0;

        do {
            resultadoAux = resultado;
            resultado = 0;
            delta = (b - a) / N;

            for (int i = 0; i < N; i++) {
                Xi = a + i * delta;
                Xf = Xi + delta;
                double somatorio = 0;

                for (int k = 0; k < qtdPontos; k++) {
                    somatorio += (pesosW[k] * f(x(raizes[k], Xi, Xf)));
                }

                resultado += ((Xf - Xi) / 2) * somatorio;
                interacoes++;
            }

            N *= 2;
            resultadoAnterior = resultadoAux;
            erro = Math.abs((resultadoAnterior - resultado) / resultado);

        } while (!(erro < tolerancia));

        System.out.println("Partições: " + N);
        System.out.println("Interações: " + interacoes);
        System.out.println("Resultado: " + resultado);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a, b;
        double tolerancia = 0.000001;
        int qtdPontos;

        // Solicitar intervalos ao usuário
        System.out.print("Informe o valor de a (início do intervalo): ");
        a = scanner.nextDouble();
        System.out.println(a);

        System.out.print("Informe o valor de b (fim do intervalo): ");
        b = scanner.nextDouble();
        System.out.println(b);

        System.out.println("Quantos pontos? (2 a 4)");
        qtdPontos = scanner.nextInt();
        while(qtdPontos < 1 || qtdPontos > 4) {
            System.out.print("Informe a quantidade de pontos corretamente (2, 3 ou 4): ");
            qtdPontos = scanner.nextInt();
        }

        if (qtdPontos == 1) {
            gaussLegendre2Pontos(a, b, tolerancia);
        } else if (qtdPontos == 2) {
            gaussLegendre3Pontos(a, b, tolerancia);
        } else if (qtdPontos == 3) {
            gaussLegendre4Pontos(a, b, tolerancia);
        }
    }
}
