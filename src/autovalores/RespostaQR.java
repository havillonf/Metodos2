package src.autovalores;

public class RespostaQR {
    double[][] P;
    double[] lambda;
    double[][] A_nova;
    public RespostaQR(double[][] P, double[] lambda, double[][] A_nova) {
        this.P = P;
        this.lambda = lambda;
        this.A_nova = A_nova;
    }
}
