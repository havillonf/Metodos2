package Atividade1;
import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        double deltaX = 0.5;
        double x = 2;
        double error = 1000.0;
        double derivative;
        double previousDerivative = 0.0;
        int i = 0;
        while(error > 0.00001 && i < 16){
            derivative = secondDerivative(deltaX, x);
            error = error(derivative, previousDerivative);
            System.out.print("i: " + i);
            System.out.print(" delta: (" + deltaX);
            System.out.print(") - f''x: (" + secondDerivative(deltaX, x));
            System.out.print(") - error: (" + error + ")");
            System.out.println();
            previousDerivative = derivative;
            deltaX/=2;
            i++;
        }

    }
    public static double fx(double x){
        return sqrt(pow(E, 3*x) + 4*pow(x,2));
    }
    public static double secondDerivative(double deltaX, double x){
        return 1/pow(deltaX,2) *
                ((-5.0/2) * fx(x)
                + (4.0/3) * fx(x + deltaX)
                + (4.0/3) * fx(x - deltaX)
                -(1.0/12) * fx(x + 2*deltaX)
                -(1.0/12) * fx(x - 2*deltaX));
    }
    public static double error(double derivative, double previousDerivative){
        double er = (derivative - previousDerivative)/derivative;
        if(er > 0.0){
            return er;
        }else{
            return -er;
        }
    }
}