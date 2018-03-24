package ru.stqa.pft.sandbox;

public class Equation {

  private double a;
  private double b;
  private double c;
  // количество корней уравнения
  private int n;

  // создаем конструктор
  public Equation (double a, double b, double c) {

    this.a = a;
    this.b = b;
    this.c = c;

    // задаем значение дискриминанту
    double d = b*b - 4*a*c;

    // вычисляем значение n в зависимости от значения дискриминанта
    if (d > 0) {
      n = 2;
    } else {
      if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }
    }
  }

  // метод возвращающий значение n
  public int rootNumber() {
    return n;
  }
}