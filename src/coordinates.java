import static java.lang.Math.*;

import java.util.HashMap;

public class coordinates {

  //неизменные параметры орбиты
  private double a, e, i, O, w, M0;

  public coordinates(double a, double e, double i, double o, double w, double m0) {
    this.a = a;
    this.e = e;
    this.i = i;
    O = o;
    this.w = w;
    M0 = m0;
  }

  // используем интерфейс Kepler для реализации полиморфима
  // (мы не знаем, какой метод будет реализовывать решение уравнения Кеплера)
  private Kepler resultKepler = new dihotomKepler();

  //Метод, возвращающий массив координат в определленное время
  double[] coordinates(double t) {

    //Вычисление входных параметров по соответствующим формулам
    double n = 1;
    double M = n * t + M0;
    double E = resultKepler.resultKepler(M, e);
    double v = 2 * atan(Math.sqrt((1 + e) / (1 - e)) * Math.tan(E / 2));
    double r = a * (1 - e * e) / (1 + e * Math.cos(v));
    double u = v + w;

    double[] x = new double[3];

    x[0] = r * (cos(u) * cos(O) - sin(u) * sin(O) * cos(i));
    x[1] = r * (cos(u) * sin(O) + sin(u) * cos(O) * cos(i));
    x[2] = r * sin(u) * sin(i);
      System.out.print("(r=" + r + ", ");
      System.out.print("u=" + u + ", ");
      System.out.print("E=" + E + ", ");
      System.out.print("v=" + v + ", ");
      System.out.print("M=" + M + ", ");
      System.out.println();
    return x;
  }

  //Метод, возвращающий HashMap параметров орбиты по входному массиву координат
  public HashMap<String, Double> orbitElemCalc(double[] x, double t) {

   HashMap<String, Double> result = new HashMap<>();

    double r = sqrt(x[0]*x[0]+x[1]*x[1]+x[2]*x[2]);
    result.put("r", r);
    double u = asin(x[2]/(sin(i)*r));
    if(t<2.7)u=PI-u;
    else if(t>=2.7)
    {
        u=2*PI+u;
    }
    if(t>=PI){
        u=u-2*PI;
    }
    if(t>5.9){
        u=PI-u;
    }
    result.put("u", u);
    double v = u - w;
    result.put("v", v);
    double E = 2 * Math.atan(Math.tan(v / 2) / Math.sqrt((1 + e) / (1 - e)));
    if(t>=PI)E=E*(-1);
    
    result.put("E", E);
    double M = E - e * Math.sin(E);
    result.put("M", M);
    return result;

  }

}
