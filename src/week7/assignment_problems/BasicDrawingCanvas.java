package week7.assignment_problems;

abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    Shape() {
        shapeId = "S" + (++counter);
    }

    public abstract double calculateArea();

    void scale(double factor) {
    }

    void scale(double xFactor, double yFactor) {
        scale(xFactor);
        scale(yFactor);
    }

    String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    void scale(double factor) {
        radius *= factor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    void scale(double factor) {
        side *= factor;
    }
}

public class BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println("Area = " + s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.println("Circle ID: " + c.getShapeId());
        System.out.printf("Circle Area: %.2f%n", c.calculateArea());

        System.out.println("Square ID: " + sq.getShapeId());
        System.out.println("Square Area: " + sq.calculateArea());

        sq.scale(2.0);

        System.out.println("Square Area after scale(2.0): "
                + sq.calculateArea());

        printArea(c);
    }
}