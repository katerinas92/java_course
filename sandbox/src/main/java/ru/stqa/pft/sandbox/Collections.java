package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args) {

                                /* РАБОТА С МАССИВАМИ */

    // объявляем переменную с типом "массив строк"
    // длинный вариант объявления массива и заполнение его значениями
    /*
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";
    */
    // сокращенный вариант объявления массива и заполнение его значениями
    String[] langs = {"Java", "C#", "Python", "PHP"};

    // интерации по элементам массива langs
    System.out.println("Выводим элементы массива через простой цикл for");
    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);
    }

    // для коллекций есть особая конструкция цикла, предназначенная для перебора значений коллекции
    System.out.println();
    System.out.println("Выводим элементы массива через особую конструкцию цикла");
    for (String l : langs) {
      System.out.println("Я хочу выучить " + l);
    }
                            /* РАБОТА С ЛИСТАМИ */

    // создаем лист
    // слева указываем интерфейс и в <> указываем тип элементов, а справа указан конкретный класс, который реализует этот интерфейс
    // стандартное создание листа
    List<String> languages1 = new ArrayList<String>();
    // добавляем элементы в лист
    languages1.add("Java");
    languages1.add("C#");
    languages1.add("Python");
    languages1.add("PHP");

    // создание листа и заполнение листа значениями через массив значений с помощью класса, который преобразует значений массива в списки
    List<String> languages2 = Arrays.asList("Scala", "VBA");

    // создаем лист, в котором не указываем тип данных
    List languages3 = Arrays.asList("JavaScript", "Borland");

    // выводим значения листа languages1
    System.out.println();
    System.out.println("Выводим элементы листа languages1");
    for (String l : languages1) {
      System.out.println("Я хочу выучить " + l);
    }

    // выводим значения листа languages2
    System.out.println();
    System.out.println("Выводим элементы листа languages2");
    for (int i = 0; i < languages2.size(); i++) {
      System.out.println("Я хочу выучить " + languages2.get(i));
    }

    // выводим значения листа languages3
    System.out.println();
    System.out.println("Выводим элементы листа languages3");
    // так как в листе languages3 не задан тип данных для значений листа, нужно указать тип Object
    for (Object l : languages3) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
