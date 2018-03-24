package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  // тест, когда корней уравнения нет
  public void test0 () {
    Equation e = new Equation(1, 1, 1);
    Assert.assertEquals(e.rootNumber(), 0);
  }

  @Test
  // тест, когда один корень уравнения
  public void test1 () {
    Equation e = new Equation(1, 2, 1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  // тест, когда два корня уравнения
  public void test2 () {
    Equation e = new Equation(1, 5, 6);
    Assert.assertEquals(e.rootNumber(), 2);
  }

  @Test
  // тест, когда линейное уравнение
  public void testLinear () {
    Equation e = new Equation(0, 1, 1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  // тест, когда c = 0
  public void testConstant () {
    Equation e = new Equation(0, 0, 1);
    Assert.assertEquals(e.rootNumber(), 0);
  }

  @Test
  // тест, когда линейное уравнение
  public void testZero () {
    Equation e = new Equation(0, 0, 0);
    Assert.assertEquals(e.rootNumber(), -1);
  }
}
