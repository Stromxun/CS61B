import org.junit.Assert;
import org.junit.Test;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(int a, int b) {
        return a == b;
    }

    @Test
    public void testIsSameNumber() {
        int a = 100, b = 100;
        Assert.assertTrue(isSameNumber(a, b));
        a = 150; b = 150;
        Assert.assertTrue(isSameNumber(a, b));
    }
}
