package ru.stqa.pft.point;

public class Point {
  double x;
  double y;

  // Конструктор
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
  }
}
