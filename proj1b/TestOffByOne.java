import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testOffByOne() {
        Assert.assertTrue(offByOne.equalChars('a', 'b'));
        Assert.assertTrue(offByOne.equalChars('r', 'q'));

        Assert.assertFalse(offByOne.equalChars('a', 'e'));
        Assert.assertFalse(offByOne.equalChars('z', 'a'));
        Assert.assertFalse(offByOne.equalChars('a', 'a'));
    }


    static CharacterComparator offBy5 = new OffByN(5);
    @Test
    public void testOffByN() {
        Assert.assertTrue(offBy5.equalChars('a', 'f'));
        Assert.assertTrue(offBy5.equalChars('f', 'a'));
        Assert.assertFalse(offBy5.equalChars('f', 'h'));
    }
    // Your tests go here.

}
