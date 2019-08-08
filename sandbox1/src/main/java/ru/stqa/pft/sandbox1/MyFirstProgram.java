package ru.stqa.pft.sandbox1;

public class MyFirstProgram {

    public static void main(String[] args) {
      hello("world");
      hello("user");
      hello("bro");

      Square s = new Square(5);
      System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle (4, 6);
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

      Point p = new Point(1, 1, 3, 5);
      System.out.println("Растояние между точками " + p.x1 + "," + p.y1 + " и " + p.x2 + "," + p.y2 + "=" + p.distance());
    }

    public static void hello(String somebody){
      System.out.println("Hello, " + somebody + "!");
    }



 }