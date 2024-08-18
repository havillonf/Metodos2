package src.autovalores;

import java.util.Arrays;

import static src.autovalores.PotenciaRegular.produtoEscalar;

public class Householder {
    private double[][] a;
    private int tam;

    public static void main(String[] args) {
        double[][] A = {
                {40,  8,  4,  2, 1},
                { 8, 30, 12,  6, 2},
                { 4, 12, 20,  1, 2},
                { 2,  6,  1, 25, 4},
                { 1,  2,  2,  4, 5}
        };

        Householder hh = new Householder(A, 5);
        RespostaHH result = hh.metodo();

        System.out.println("MATRIZ TRIDIAGONAL");
        printMatriz(result.A_barra);
        System.out.println("\n\nMATRIZ H acumulada");
        printMatriz(result.H);
    }

    public static void printMatriz(double[][] matriz){
        for (double[] lines : matriz) {
            System.out.println(Arrays.toString(lines));
        }
    }

    public Householder(double[][] matriz, int tam) {
        this.a = matriz;
        this.tam = tam;
    }

    public RespostaHH metodo() {
        double[][] H = identidade(tam);
        double[][] A_ant = a;
        double[][] A_barra = a;

        for (int i = 0; i < tam - 2; i++) {
            double[][] H_i = matrizHouseholder(A_ant, i);
            double[][] aux1 = multiplicar(transpor(H_i), A_ant);
            A_barra = multiplicar(aux1, H_i);
            A_ant = A_barra;
            H = multiplicar(H, H_i);
        }

        return new RespostaHH(A_barra, H);
    }

    private double[][] matrizHouseholder(double[][] matriz, int i) {
        double[] w = new double[tam];
        double[] w2 = new double[tam];
        double[] n = new double[tam];

        for (int indice = i + 1; indice < tam; indice++) {
            w[indice] = matriz[indice][i];
        }

        w2[i + 1] = Math.sqrt(produtoEscalar(w, w));
        double[] N = subtrair(w, w2);

        double comp = Math.sqrt(produtoEscalar(N, N));
        for (int indice2 = 0; indice2 < tam; indice2++) {
            n[indice2] = N[indice2] / comp;
        }

        double[][] I = identidade(tam);
        double[] aux1 = constXvetor(2, n);
        double[][] H = subtrair(I, produtoVetorial(aux1, n));
        return H;
    }

    private double[] constXvetor(double n, double[] v) {
        double[] res = new double[tam];
        for (int i = 0; i < tam; i++) {
            res[i] = n * v[i];
        }
        return res;
    }

    private double[][] produtoVetorial(double[] v1, double[] v2) {
        double[][] mat = new double[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                mat[i][j] = v1[i] * v2[j];
            }
        }
        return mat;
    }

    private double[][] identidade(int tam) {
        double[][] I = new double[tam][tam];
        for (int i = 0; i < tam; i++) {
            I[i][i] = 1.0;
        }
        return I;
    }

    private double[][] multiplicar(double[][] A, double[][] B) {
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

    private double[][] transpor(double[][] A) {
        int n = A.length;
        double[][] T = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                T[i][j] = A[j][i];
            }
        }
        return T;
    }

    private double[] subtrair(double[] v1, double[] v2) {
        double[] res = new double[tam];
        for (int i = 0; i < tam; i++) {
            res[i] = v1[i] - v2[i];
        }
        return res;
    }

    private double[][] subtrair(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }


}
