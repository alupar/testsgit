package ru.stqa.pft.sandbox1;

import java.awt.*;

public class Point {
  public double x;
  public double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2){
    double sqrt = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    return sqrt;
  }

}
