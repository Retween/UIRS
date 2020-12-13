import static java.lang.Math.*;

import java.util.HashMap;

public class coordinates {

  private double a, e, i, O, w, M0;


  // используем интерфейс Kepler для реализации полиморфима
  // (мы не знаем, какой метод будет реализовывать решение уравнения Кеплера)
  private Kepler resultKepler = new dihotomKepler();


  public coordinates(double a, double e, double i, double o, double w, double m0) {
    this.a = a;
    this.e = e;
    this.i = i;
    O = o;
    this.w = w;
    M0 = m0;
  }

  //Метод возвращающий массив координат
  double[] coordinates(double t) {
    //Вычисление входных параметров по соответствующим формулам

//    double n = Math.sqrt(1.0 / Math.pow(a, 3));
    double n = 1;
    double M = n * t + M0;
    double E = resultKepler.resultKepler(M, e);
    double v = 2 * atan(Math.sqrt((1 + e) / (1 - e)) * Math.tan(E / 2));
    double r = a * (1 - e * e) / (1 + e * Math.cos(v));
    double u = v + w;

    System.out.printf("Прямой    u %20.20f\n", u);
//    System.out.printf("Прямой    v %20.20f\n", v);
//    System.out.printf("Прямой    r %20.20f\n", r);
//    System.out.printf("Прямой    E %20.20f\n", E);
//    System.out.printf("Прямой    M %20.20f\n", M);

    double[] x = new double[3];

    x[0] = r * (cos(u) * cos(O) - sin(u) * sin(O) * cos(i));
    x[1] = r * (cos(u) * sin(O) + sin(u) * cos(O) * cos(i));
    x[2] = r * sin(u) * sin(i);

    return x;
  }

  //Обратный расчет
  public HashMap<String, Double> orbitElemCalc(double[] x) {
    HashMap<String, Double> result = new HashMap<>();

    double u = Math
        .atan(x[2] * Math.cos(O) / (Math.sin(i) * (x[0] + x[2] * Math.sin(O) / Math.tan(i))));
    result.put("u", u);
    System.out.printf("Обрантный u %20.20f\n\n", u);

    double v = u - w;
    result.put("v", v);
//    System.out.printf("Обрантный v %20.20f\n\n", v);

    double r = a * (1 - e * e) / (1 + e * Math.cos(v));
    result.put("r", r);
//    System.out.printf("Обрантный r %20.20f\n\n", r);

    double E = 2 * Math.atan(Math.tan(v / 2) / Math.sqrt((1 + e) / (1 - e)));
    result.put("E", E);
//    System.out.printf("Обрантный E %20.20f\n\n", E);

    double M = E - e * Math.sin(E);
    result.put("M", M);
//    System.out.printf("Обрантный M %20.20f\n\n", M);

    return result;
  }

}
