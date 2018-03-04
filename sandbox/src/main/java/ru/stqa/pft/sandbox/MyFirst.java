package ru.stqa.pft.sandbox;

public class MyFirst {
	public static void main(String[] args) {
		hello("World!");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
	}

	public static void hello(String somebody) {
		//String somebody = "World!";
		System.out.println("Hello, " + somebody);
	}
}