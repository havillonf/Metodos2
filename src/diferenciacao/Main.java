package src.diferenciacao;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o xi: ");
        double x = sc.nextDouble();

        System.out.println("Digite o delta: ");
        double deltaX = sc.nextDouble();

        System.out.println("Derivada primeira, segunda ou terceira? (Digite 1, 2 ou 3)");
        int ordem = sc.nextInt();

        System.out.println("Digite c para central, b para backward e f para forward");
        String filosofia = sc.next();


        double error = 1000.0;
        double derivative = 0.0;
        double previousDerivative = 0.0;
        int i = 0;
        while(error > 0.00001 && i < 16){
            if(ordem == 1){
                switch (filosofia){
                    case "c":
                        derivative = firstDerivativeCentral(x, deltaX);
                        break;
                    case "b":
                        derivative = firstDerivativeBackward(x, deltaX);
                        break;
                    case "f":
                        derivative = firstDerivativeForward(x, deltaX);
                        break;
                }
            } else if(ordem == 2){
                switch (filosofia){
                    case "c":
                        derivative = secondDerivativeCentral(x, deltaX);
                        break;
                    case "b":
                        derivative = secondDerivativeBackward(x, deltaX);
                        break;
                    case "f":
                        derivative = secondDerivativeForward(x, deltaX);
                        break;
                }
            }else if(ordem == 3){
                switch (filosofia){
                    case "c":
                        derivative = thirdDerivativeCentral(x, deltaX);
                        break;
                    case "b":
                        derivative = thirdDerivativeBackward(x, deltaX);
                        break;
                    case "f":
                        derivative = thirdDerivativeForward(x, deltaX);
                        break;
                }
            }
            error = error(derivative, previousDerivative);
            System.out.print("i: " + i);
            System.out.print(" delta: (" + deltaX);
            System.out.print(") - derivada:  (" + derivative);
            System.out.print(") - erro: (" + error + ")");
            System.out.println();
            previousDerivative = derivative;
            deltaX/=2;
            i++;
        }

    }
    public static double fx(double x){
        return sqrt(pow(E, 3*x) + 4*pow(x,2));
    }
    public static double firstDerivativeCentral(double x, double deltaX){
        return (fx(x + deltaX) - fx(x - deltaX))/deltaX;
    }
    public static double firstDerivativeBackward(double x, double deltaX){
        return (fx(x) - fx(x - deltaX))/deltaX;
    }
    public static double firstDerivativeForward(double x, double deltaX){
        return (fx(x + deltaX) - fx(x))/deltaX;
    }
    public static double secondDerivativeCentral(double x, double deltaX){
        return (fx(x + deltaX) - 2.0 * fx(x) + fx(x - deltaX))/Math.pow(deltaX, 2.0);
    }
    public static double secondDerivativeBackward(double x, double deltaX){
        return (fx(x) - 2.0*fx(x-deltaX) + fx(x - 2.0*deltaX))/Math.pow(deltaX, 2.0);
    }
    public static double secondDerivativeForward(double x, double deltaX){
        return (fx(x + 2.0*deltaX) - 2.0 * fx(x + deltaX) + fx(x))/Math.pow(deltaX, 2.0);
    }
    public static double thirdDerivativeForward(double x, double deltaX){
        return (-fx(x) + 3.0*fx(x + deltaX) - 3.0 * fx(x + 2.0*deltaX) + fx(x + 3.0*deltaX))/Math.pow(deltaX, 3.0);
    }
    public static double thirdDerivativeCentral(double x, double deltaX){
        return (-(1.0/2.0) * fx(x-2.0*deltaX) + fx(x-deltaX) - fx(x+deltaX) + (1.0/2.0) * fx(x + 2.0*deltaX))/Math.pow(deltaX, 3.0);
    }
    public static double thirdDerivativeBackward(double x, double deltaX){
        return (-fx(x - 3.0*deltaX) + 3.0*fx(x - 2.0*deltaX) -3.0 * fx(x - deltaX) + fx(x))/Math.pow(deltaX, 3.0);
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