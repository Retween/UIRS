import static java.lang.Math.*;

public class coordinats {
    private double a, e, i, O, w, M0;

    // используем интерфейс Kepler для реализации полиморфима
    // (мы не знаем, какой метод будет реализовывать решение уравнения Кеплера)
    Kepler resultKepler = new dihotomKepler();


    public coordinats(double a, double e, double i, double o, double w, double m0) {
        this.a = a;
        this.e = e;
        this.i = i;
        O = o;
        this.w = w;
        M0 = m0;
    }

    //Метод возвращающий массив координат
    double [] coordinates (double t){
        //Вычисление входных параметров по соответствующим формулам
        double n = 1;
        double M = n * t + M0;
        double E = resultKepler.resultKepler(M, e);
        double v =  2* atan( Math.sqrt((1 + e) / (1 - e)) * Math.tan(E / 2));
        double r = a * (1 - e * e) / (1 + e * Math.cos(v));
        double u = v + w;
        double[] x = new double[3];
        x[0] = r*(cos(u)*cos(O) - sin(u)*sin(O)*cos(i));
        x[1] = r*(cos(u)*sin(O) + sin(u)*cos(O)*cos(i));
        x[2] = r*sin(u)*sin(i);
        return x;
    }
}
