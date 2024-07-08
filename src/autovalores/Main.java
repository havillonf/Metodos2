package autovalores;

public class Main {

    public static void main(String[] args) {
        // dados iniciais: matriz do problema, vetor arbitrario e tolerancia do erro
        double[][] matrizA = { {40.0,  8.0,  4.0,  2.0, 1.0},
                               { 8.0, 30.0, 12.0,  6.0, 2.0},
                               { 4.0, 12.0, 20.0,  1.0, 2.0},
                               { 2.0,  6.0,  1.0, 25.0, 4.0},
                               { 1.0,  2.0,  2.0,  4.0, 5.0} };

        double[] v0 = {1.0, 1.0, 1.0, 1.0, 1.0};

        double tolerancia = 0.0001;
        double erro = 10.0;

        // inicializar o autovalor lambda1
        double lambda1Novo = 0.0;
        
        double lambda1Velho;

        // inicializar o vetor vkNovo
        double[] vkNovo = v0;
        double[] vkVelho;

        double[] x1Velho = {0.0, 0.0, 0.0, 0.0, 0.0};

        int cont = 0;

        while(erro > tolerancia){
            // atualizar as variaveis 
            lambda1Velho = lambda1Novo;
            vkVelho = vkNovo;

            // normalizar vkVelho
            x1Velho = normalizar(vkVelho);

            // calcular o vetor não normalizado
            vkNovo = produtoMatrizVetor(matrizA, x1Velho);

            // calcular a nova estimativa de lambda1
            lambda1Novo = produtoEscalar(x1Velho, vkNovo);

            // verificar convergência
            erro = Math.abs((lambda1Novo - lambda1Velho)/lambda1Novo);

            cont++;

            System.out.println("Iteração " + cont);
            System.out.println("Lambda 1 novo: " + lambda1Novo);
            System.out.println("Lambda 1 velho: " + lambda1Velho);
            System.out.println("Erro novo: " + erro);
            System.out.println("");
        }

        System.out.println("Resultados: ");
        System.out.println("Autovalor: " + lambda1Novo);
        System.out.print("Autovetor: { ");
        for(int i = 0; i < x1Velho.length; i++){
            System.out.print(x1Velho[i] + " ");
        }
        System.out.print("}");
        System.out.println();
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
            aux[0] = produtoEscalar(A[i], v);
        }

        return aux;
    }
}   