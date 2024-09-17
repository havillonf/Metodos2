package src.autovalores;

import java.util.Arrays;

public class Jacobi {

    public static double[][] jacobi_ij(double[][] A, int i, int j, double e) {
        int n = A.length;
        double theta;
        double[][] Jij = new double[n][n];
        for (int k = 0; k < n; k++) {
            Jij[k][k] = 1.0;
        }

        if (Math.abs(A[i][j]) <= e) {
            return Jij;
        } else if (Math.abs(A[i][i] - A[j][j]) <= e) {
            theta = Math.PI / 4;
        } else {
            theta = 0.5 * Math.atan(-2 * A[i][j] / (A[i][i] - A[j][j]));
        }

        Jij[i][i] = Math.cos(theta);
        Jij[j][j] = Math.cos(theta);
        Jij[i][j] = Math.sin(theta);
        Jij[j][i] = -Math.sin(theta);

        return Jij;
    }

    public static RespostaJacobi varredura_jacobi(double[][] A, double e) {
        int n = A.length;
        double[][] J = new double[n][n];
        for (int k = 0; k < n; k++) {
            J[k][k] = 1.0;
        }
        double[][] A_antiga = A;

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {
                double[][] Jij = jacobi_ij(A_antiga, i, j, e);
                double[][] A_nova = multiplicar_matrizes(transpor(Jij), A_antiga);
                A_nova = multiplicar_matrizes(A_nova, Jij);
                A_antiga = A_nova;
                J = multiplicar_matrizes(J, Jij);
            }
        }

        return new RespostaJacobi(A_antiga, J);
    }

    public static double soma_quadrados_abaixo_diag(double[][] A) {
        int tamanho = A.length;
        double soma = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i < j) {
                    soma += Math.pow(A[i][j], 2);
                }
            }
        }
        return soma;
    }

    public static RespostaJacobi jacobi(double[][] A, double e) {
        int n = A.length;
        double[] autovalores = new double[n];
        double val = 100;
        double[][] J = new double[n][n];
        for (int k = 0; k < n; k++) {
            J[k][k] = 1.0;
        }
        double[][] A_antiga = A;

        while (val > e) {
            RespostaJacobi resultado = varredura_jacobi(A_antiga, e);
            double[][] A_nova = resultado.A;
            double[][] Ji = resultado.J;
            A_antiga = A_nova;
            J = multiplicar_matrizes(J, Ji);
            val = soma_quadrados_abaixo_diag(A_nova);
        }

        for (int i = 0; i < n; i++) {
            autovalores[i] = A_antiga[i][i];
        }

        return new RespostaJacobi(A_antiga, J);
    }

    public static double[][] multiplicar_matrizes(double[][] A, double[][] B) {
        int n = A.length;
        double[][] resultado = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    resultado[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return resultado;
    }

    public static double[][] transpor(double[][] A) {
        int n = A.length;
        double[][] resultado = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i][j] = A[j][i];
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        double[][] A = {
                {-40, 8, 4, 2, 1},
                {8, -30, 12, 6, 2},
                {4, 12, 20, 1, 2},
                {2, 6, 1, 25, 4},
                {1, 2, 2, 4, 5}
        };
        double tolerancia = 0.000001;

        RespostaJacobi resultado = jacobi(A, tolerancia);
        double[][] C = resultado.A;
        double[][] J = resultado.J;

        System.out.println("Matriz J:");
        for (double[] linha : J) {
            System.out.println(Arrays.toString(linha));
        }

    }
}
