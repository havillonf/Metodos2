package autovalores;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import static autovalores.PotenciaRegular.potenciaRegular;
import static autovalores.PotenciaRegular.printResposta;

public class PotenciaInversa {

    public static void main(String[] args) {
        // implementação para o método da potência regular - tarefa 10

        double tolerancia = 0.00001;

        // matriz A1
        double[][] matrizA1 = { {5.0, 2.0, 1.0},
                                {2.0, 3.0, 1.0},
                                {1.0, 1.0, 2.0} };

        double[] vA1 = {1.0, 1.0, 1.0};

        System.out.println("Matriz A1: ");
        Resposta r1 = potenciaInversa(matrizA1, vA1, tolerancia);
        printResposta(r1);

        // matriz A2
        double[][] matrizA2 = { {-14.0,  1.0,  -2.0},
                                {  1.0, -1.0,   1.0},
                                { -2.0,  1.0, -11.0} };

        double[] vA2 = {1.0, 1.0, 1.0};
        
        System.out.println("\nMatriz A2: ");
        Resposta r2 = potenciaInversa(matrizA2, vA2, tolerancia);
        printResposta(r2);

        // matriz A3
        double[][] matrizA3 = { {40.0,  8.0,  4.0,  2.0, 1.0},
                                { 8.0, 30.0, 12.0,  6.0, 2.0},
                                { 4.0, 12.0, 20.0,  1.0, 2.0},
                                { 2.0,  6.0,  1.0, 25.0, 4.0},
                                { 1.0,  2.0,  2.0,  4.0, 5.0} };

        double[] vA3 = {1.0, 1.0, 1.0, 1.0, 1.0};

        System.out.println("\nMatriz A3: ");
        Resposta r3 = potenciaInversa(matrizA3, vA3, tolerancia);
        printResposta(r3);
    }

    public static Resposta potenciaInversa(double[][] matriz, double[] v, double tolerancia){
        Resposta r = potenciaRegular(calcularInversa(matriz), v, tolerancia);
        r.autovalor = 1/r.autovalor;
        return r;
    }

    public static double[][] calcularInversa(double[][] matrizData) {
        try {
            RealMatrix matriz = MatrixUtils.createRealMatrix(matrizData);
            LUDecomposition decomposicao = new LUDecomposition(matriz);
            RealMatrix inversaMatrix = decomposicao.getSolver().getInverse();
            return inversaMatrix.getData();
        } catch (Exception e) {
            return null;
        }
    }
}   