package ru.stqa.pft.sandbox;

public class Square {
  public double l;

  // Конструктор
  public Square(double len) {
    this.l = len;
  }

  // Функция, вычисляющая площадь квадрата
  public double area() {
    return this.l * this.l;
  }
}
