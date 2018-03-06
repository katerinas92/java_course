package ru.stqa.pft.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointDistance() {
    Point p1 = new Point(2, 6.8);
    Point p2 = new Point(3.6, 5.5);
    Assert.assertEquals(p1.distance(p2), 2.0615528128088303);
  }
}
