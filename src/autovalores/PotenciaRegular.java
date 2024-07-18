package autovalores;

public class PotenciaRegular {

    public static void main(String[] args) {
        // implementação para o método da potência regular - tarefa 10

        double tolerancia = 0.00001;

        // matriz A1
        double[][] matrizA1 = { {5.0, 2.0, 1.0},
                                {2.0, 3.0, 1.0},
                                {1.0, 1.0, 2.0} };

        double[] vA1 = {1.0, 1.0, 1.0};

        System.out.println("Matriz A1: ");
        Resposta r1 = potenciaRegular(matrizA1, vA1, tolerancia);
        printResposta(r1);


        // matriz A2
        double[][] matrizA2 = { {40.0,  8.0,  4.0,  2.0, 1.0},
                                { 8.0, 30.0, 12.0,  6.0, 2.0},
                                { 4.0, 12.0, 20.0,  1.0, 2.0},
                                { 2.0,  6.0,  1.0, 25.0, 4.0},
                                { 1.0,  2.0,  2.0,  4.0, 5.0} };

        double[] vA2 = {1.0, 1.0, 1.0, 1.0, 1.0};

        System.out.println("\nMatriz A2: ");
        Resposta r2 = potenciaRegular(matrizA2, vA2, tolerancia);
        printResposta(r2);
    }

    public static Resposta potenciaRegular(double[][] matrizA, double[] v0, double tolerancia) {
        // dados iniciais: matriz do problema, vetor arbitrario e tolerancia do erro

        double erro = 100.0;

        // inicializar o autovalor lambda1
        double lambda1Novo = 0.0;

        // inicializar o vetor vkNovo
        double[] vkNovo = v0;
        double[] vkVelho = {0.0, 0.0, 0.0, 0.0, 0.0};

        double[] x1Velho = {0.0, 0.0, 0.0, 0.0, 0.0};


        while(erro > tolerancia){
            // atualizar as variaveis
            double lambda1Velho = lambda1Novo;
            vkVelho = vkNovo;

            // normalizar vkVelho
            x1Velho = normalizar(vkVelho);

            // calcular o vetor não normalizado
            vkNovo = produtoMatrizVetor(matrizA, x1Velho);

            // calcular a nova estimativa de lambda1
            lambda1Novo = produtoEscalar(x1Velho, vkNovo);

            // verificar convergência
            erro = Math.abs((lambda1Novo - lambda1Velho)/lambda1Novo);

        }

        return new Resposta(lambda1Novo, x1Velho);
    }

    public static double[] normalizar(double[] v){
        double[] aux = new double[v.length];
        double denominador = Math.sqrt(produtoEscalar(v, v));
        for(int i = 0; i < v.length; i++){
            aux[i] = v[i]/denominador;
        }
        return aux;
    }

    public static double produtoEscalar(double[] v1, double[] v2){
        double aux = 0;
        for(int i = 0; i < v1.length; i++){
            aux += v1[i]*v2[i];
        }
        return aux;
    }

    public static double[] produtoMatrizVetor(double[][] A, double[] v){

        double[] aux = new double[v.length];

        for(int i = 0; i < A.length; i++){
            aux[i] = produtoEscalar(A[i], v);
        }

        return aux;
    }

    public static void printResposta(Resposta r){
        System.out.println("Resultados: ");
        System.out.println("Autovalor: " + r.autovalor);
        System.out.print("Autovetor: { ");
        for(int i = 0; i < r.autovetor.length; i++){
            System.out.print(r.autovetor[i] + " ");
        }
        System.out.print("}");
        System.out.println();
    }
}   