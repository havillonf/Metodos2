package src.pvi;


public class RungeKutta {

    public static void main(String[] args) {
        double t0 = 0;     // tempo inicial
        double v0 = 5;     // velocidade inicial
        double y0 = 200;   // altitude inicial
        double k = 0.25;   // constante de aerodinâmica
        double m = 2;      // massa do objeto
        double g = 10;     // gravidade

        double[] dts_ls = {0.1, 0.01, 0.001, 0.0001};

        for (double dt : dts_ls) {
            rungeKutta(t0, v0, y0, k, m, g, dt);
        }
    }

    public static void rungeKutta(double t0, double v0, double y0, double k, double m, double g, double dt) {
        double[] s0 = {v0, y0};
        int i = 1;
        double yMax = y0;
        double tMax = t0;
        double vFinal = 0;

        while (s0[1] >= 0) {
            double v = -g - (k / m * v0);
            double vMeio = v0 + (dt / 2) * v;
            double yMeio = y0 + (dt / 2) * v0;

            double v1 = v0 + dt * v;
            double y1 = y0 + dt * v0;

            double[] s1 = new double[2];
            s1[0] = s0[0] + dt * ((1.0 / 6) * v + (4.0 / 6) * (-g - (k / m * vMeio)) + (1.0 / 6) * (-g - (k / m * v1)));
            s1[1] = s0[1] + dt * ((1.0 / 6) * v0 + (4.0 / 6) * vMeio + (1.0 / 6) * v1);

            t0 = t0 + dt;
            i += 1;
            vFinal = v0;
            v0 = s1[0];
            y0 = s1[1];
            s0 = s1;

            if (y0 > yMax) {
                yMax = y0;
                tMax = t0;
            }
        }

        System.out.println("Δt                = " + dt + ":");
        System.out.println("altura_max        = " + yMax);
        System.out.println("tempo_max_altura  = " + tMax);
        System.out.println("tempo_total       = " + (t0 - dt));
        System.out.println("velocidade_final  = " + Math.abs(vFinal) + "\n\n");
    }
}
