package ru.stqa.pft.point;

public class PointTest {
    public static void main(String[] args) {
      Point p = new Point(0,0);
      Point p1 = new Point(1.5, 3.2);
      Point p2 = new Point(4, 5.5);

      System.out.println("Первая точка имеет координаты (" + p1.x + "; " + p1.y + ")");
      System.out.println("Вторая точка имеет координаты (" + p2.x + "; " + p2.y + ")");
      System.out.println("Расстояние от первой до второй точки равно: " + p.distance(p1,p2));
    }
}
