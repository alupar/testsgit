package ru.stqa.pft.sandbox1;

public class MyFirstProgram {
    double p1;
    public void main(String[] args) {
      hello("world");
      hello("user");
      hello("bro");

      Square s = new Square(5);
      System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle (4, 6);
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


      Point p1 = new Point (2, 2);
      Point p2 = new Point(8,10);

      System.out.println("Растояние между точками " + p1.x + "," + p1.y + " и " + p2.x + "," + p2.y + "=" + p1.distance(p2));
    }

    public static void hello(String somebody){
      System.out.println("Hello, " + somebody + "!");

    }



 }