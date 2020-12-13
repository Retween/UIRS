//Класс реализующий решение уравнение Кеплера методом дихотомии
public class dihotomKepler implements Kepler {

    @Override
    public double resultKepler(double M, double e) {
    double mid, st = 0, fin = 2*Math.PI, eq=10e-12;
        do {
            mid = (fin + st) / 2;
            if (foo(mid, e, M) * foo(st, e, M) <= 0) fin = mid;
            else st = mid;
        } while (Math.abs(st - fin) > eq);
        return mid;
    }

    private double foo(double E, double e, double M) {
        double f = (E - e * Math.sin(E) - M);
        return f;
    }
}
