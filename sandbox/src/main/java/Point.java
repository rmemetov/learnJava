public class Point {
    public static void main(String[] args) {

        double x1 = 3;
        double y1 = 1;
        double x2 = 2;
        double y2 = 0;


        System.out.println("Расстояние между точками " + " = " + distance(x1, y1, x2, y2));
    }


    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
