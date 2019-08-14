package ru.stqa.pft.sandbox1;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test
  public void testArea(){
    Square s = new Square (5);
    Assert.assertEquals(s.area(), 25.0);

    Point p1 = new Point (2, 2);
    Point p2 = new Point(8,10);
    Assert.assertEquals(p1.distance(p2), 10.0);
  }
}
