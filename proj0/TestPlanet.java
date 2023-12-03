public class TestPlanet {

    public static void main(String[] args) {
        testPlanet();
    }

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }


    private static void testPlanet() {
        // Instance the objection of Planet to test
        Planet earth = new Planet(1.0, 1.5, 2.0, 2.5, 3.0, "jupiter.gif");
        Planet mars = new Planet(2.0, 3, 4.0, 1.5, 2.0, "jupiter.gif");

        System.out.println("earth's pairwise force with mars: Fx:" +
            earth.calcForceExertedByX(mars) + " Fy:" + earth.calcForceExertedBy(mars));
        System.out.println("mars's pairwise force with earth: Fx:" +
            mars.calcForceExertedByX(earth) + " Fy:" + mars.calcForceExertedBy(earth));
    }
}
