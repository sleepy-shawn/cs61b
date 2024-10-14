import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("h"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Noon"));
        assertFalse(palindrome.isPalindrome("zrx"));
        assertFalse(palindrome.isPalindrome("noo n"));

        // new isPalindrome
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("cjkd", offByOne));
        assertTrue(palindrome.isPalindrome("abcb", offByOne));
        assertFalse(palindrome.isPalindrome("blue", offByOne));
        assertFalse(palindrome.isPalindrome("red", offByOne));

        // for offByN
        CharacterComparator offBy3 = new OffByN(3);
        assertTrue(palindrome.isPalindrome("dog", offBy3));
        assertFalse(palindrome.isPalindrome("cat", offBy3));
    }
}
