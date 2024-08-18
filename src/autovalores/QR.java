package src.autovalores;

import java.util.Arrays;

public class QR {
    private double[][] a;
    private int tam;
    private double erro;

    public QR(double[][] matriz, int n, double erro) {
        this.a = matriz;
        this.tam = n;
        this.erro = erro;
    }

    public static void main(String[] args) {
        double[][] A = {
                {40, 8, 4, 2, 1},
                {8, 30, 12, 6, 2},
                {4, 12, 20, 1, 2},
                {2, 6, 1, 25, 4},
                {1, 2, 2, 4, 5}
        };

        QR obj = new QR(A, 5, 0.0000001);
        RespostaQR result = obj.metodo();
        double[][] P = result.P;
        double[] lamb = result.lambda;
        double[][] A_nova = result.A_nova;

        System.out.println("Matriz diagonal\n");
        imprimirMatriz(A_nova);
        System.out.println("\n");

        System.out.println("Matriz P\n");
        imprimirMatriz(P);
        System.out.println("\n");

        P = transposta(P);

        System.out.println("Pares autovalor-autovetor\n");
        for (int i = 0; i < obj.tam; i++) {
            System.out.println(lamb[i] + " -> " + Arrays.toString(P[i]));
        }
    }

    public RespostaQR metodo() {
        double[][] P = identidade(this.tam);
        double val = 100;
        double[][] A_velha = this.a;
        double[][] A_nova = this.a;
        double[] lambda;

        while (val > this.erro) {
            Decomposicao qr = decomposicaoQR(A_velha);
            double[][] Q = qr.Q;
            double[][] R = qr.R;
            A_nova = multiplicarMatrizes(R, Q);
            A_velha = A_nova;
            P = multiplicarMatrizes(P, Q);
            val = somaTermosDiagonalInferior(A_nova);
        }

        lambda = diagonal(A_nova);
        return new RespostaQR(P, lambda, A_nova);
    }

    private Decomposicao decomposicaoQR(double[][] A) {
        double[][] QT = identidade(this.tam);
        double[][] R_velha = A;
        double[][] R_nova = A;

        for (int j = 0; j < this.tam - 1; j++) {
            for (int i = j + 1; i < this.tam; i++) {
                double[][] J_ij = matrizJacobi(R_velha, i, j);
                R_nova = multiplicarMatrizes(J_ij, R_velha);
                R_velha = R_nova;
                QT = multiplicarMatrizes(J_ij, QT);
                System.out.println("Matriz R_nova da iteração (" + i + "," + j + ")\n");
                imprimirMatriz(R_nova);
                System.out.println("\n");
            }
        }

        double[][] Q = transposta(QT);
        return new Decomposicao(Q, R_nova);
    }

    private double[][] matrizJacobi(double[][] A, int i, int j) {
        double[][] J_ij = identidade(this.tam);
        double ang;
        double erro = Math.pow(10, -6);

        if (Math.abs(A[i][j]) <= erro) {
            return J_ij;
        }

        if (Math.abs(A[j][j]) <= erro) {
            if (A[i][j] < 0) {
                ang = Math.PI / 2;
            } else {
                ang = -Math.PI / 2;
            }
        } else {
            ang = Math.atan(-A[i][j] / A[j][j]);
        }

        J_ij[i][i] = Math.cos(ang);
        J_ij[j][j] = Math.cos(ang);
        J_ij[i][j] = Math.sin(ang);
        J_ij[j][i] = -Math.sin(ang);

        return J_ij;
    }

    private double somaTermosDiagonalInferior(double[][] A) {
        double soma = 0;
        for (int j = 0; j < this.tam - 1; j++) {
            for (int i = j + 1; i < this.tam; i++) {
                soma += A[i][j] * A[i][j];
            }
        }
        return soma;
    }

    private double[][] identidade(int n) {
        double[][] identidade = new double[n][n];
        for (int i = 0; i < n; i++) {
            identidade[i][i] = 1.0;
        }
        return identidade;
    }

    private double[][] multiplicarMatrizes(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static double[][] transposta(double[][] A) {
        int n = A.length;
        double[][] transposta = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposta[i][j] = A[j][i];
            }
        }
        return transposta;
    }

    private double[] diagonal(double[][] A) {
        int n = A.length;
        double[] diag = new double[n];
        for (int i = 0; i < n; i++) {
            diag[i] = A[i][i];
        }
        return diag;
    }

    private static void imprimirMatriz(double[][] matriz) {
        for (double[] linha : matriz) {
            System.out.println(Arrays.toString(linha));
        }
    }
}

