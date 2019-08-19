package ru.stqa.pft.sandbox1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceMeasurement {

  @Test
  public void testDistanceMeasurement(){
    Point p1 = new Point (2, 2);
    Point p2 = new Point(8,10);
    Assert.assertEquals(p1.distance(p2), 10.0);
  }
}
