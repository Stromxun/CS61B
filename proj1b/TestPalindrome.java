import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("apple"));
        assertTrue(palindrome.isPalindrome("txt"));
        assertTrue(palindrome.isPalindrome("enne"));
        assertTrue(palindrome.isPalindrome("mmm"));
        assertTrue(palindrome.isPalindrome("abcddcba"));
        assertFalse(palindrome.isPalindrome("Mmm"));
        assertTrue(palindrome.isPalindrome("c taat c"));

        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertFalse(palindrome.isPalindrome("ccc", obo));
    }
}
