package ru.stqa.pft.sandbox;

public class DistanceTwoPoints {

    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 3);

        System.out.println("Расстояние между точками X и Y = " + p1.distance(p2));

    }

}