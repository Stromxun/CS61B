import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('&', '%'));

        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'A'));
    }


    static CharacterComparator offBy5 = new OffByN(5);
    @Test
    public void testOffByN() {
        Assert.assertTrue(offBy5.equalChars('a', 'f'));
        Assert.assertFalse(offBy5.equalChars('F', 'a'));
        Assert.assertFalse(offBy5.equalChars('f', 'h'));
    }
    // Your tests go here.

}
