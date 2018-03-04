package ru.stqa.pft.sandbox;

public class Rectangle {
  public double a;
  public double b;

  // Конструктор
  public Rectangle(double a, double b) {
    this.a = a;
    this.b = b;
  }
    // Функция, вычисляющая площадь треугольника
    public double area() {
      return this.a * this.b;
    }
  }

