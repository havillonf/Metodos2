package src.autovalores;

import java.util.ArrayList;
import java.util.List;

import static src.autovalores.PotenciaInversa.potenciaInversa;
import static src.autovalores.PotenciaRegular.printResposta;

public class PotenciaDeslocamento {

    public static void main(String[] args) {
        // implementação para o método da potência regular - tarefa 10

        double tolerancia = 0.00001;

        // matriz A1
        double[][] matrizA1 = { {5.0, 2.0, 1.0},
                {2.0, 3.0, 1.0},
                {1.0, 1.0, 2.0} };

        double[] vA1 = {1.0, 1.0, 1.0};

        System.out.println("Matriz A1: ");

        List<autovalores.RespostaPotencia> r1 = new ArrayList<>();

        for(int deslocamento = 1; deslocamento < 25; deslocamento+=5){
            r1.add(potenciaDeslocamento(matrizA1, vA1, tolerancia, deslocamento));
        }

        for(autovalores.RespostaPotencia respostaPotencia : r1){
            printResposta(respostaPotencia);
        }

//        // matriz A2
//        double[][] matrizA2 = { {-14.0,  1.0,  -2.0},
//                {  1.0, -1.0,   1.0},
//                { -2.0,  1.0, -11.0} };
//
//        double[] vA2 = {1.0, 1.0, 1.0};
//
//        System.out.println("\nMatriz A2: ");
//        Resposta r2 = potenciaInversa(matrizA2, vA2, tolerancia);
//        printResposta(r2);
//
//        // matriz A3
//        double[][] matrizA3 = { {40.0,  8.0,  4.0,  2.0, 1.0},
//                { 8.0, 30.0, 12.0,  6.0, 2.0},
//                { 4.0, 12.0, 20.0,  1.0, 2.0},
//                { 2.0,  6.0,  1.0, 25.0, 4.0},
//                { 1.0,  2.0,  2.0,  4.0, 5.0} };
//
//        double[] vA3 = {1.0, 1.0, 1.0, 1.0, 1.0};
//
//        System.out.println("\nMatriz A3: ");
//        Resposta r3 = potenciaInversa(matrizA3, vA3, tolerancia);
//        printResposta(r3);
    }

    public static autovalores.RespostaPotencia potenciaDeslocamento(double[][] matriz, double[] vetor, double tolerancia, double deslocamento){
        double[][] matrizNova = matrizMenosEscalar(matriz, deslocamento);
        autovalores.RespostaPotencia valores = potenciaInversa(matrizNova, vetor, tolerancia);
        valores.autovalor += deslocamento;
        return valores;
    }

    public static double[][] matrizMenosEscalar(double[][] matriz, double escalar){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = matriz[i][j] - escalar;
            }
        }
        return matriz;
    }
}