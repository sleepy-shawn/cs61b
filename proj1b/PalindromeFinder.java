/** This class outputs all palindromes in the words file in the current directory. */
import java.util.Arrays;
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        // CharacterComparator offByOne = new OffByOne();
        int [] offarray = new int [24];
        while (!in.isEmpty()) {
            String word = in.readString();
            for (int i = 0; i < 24; i++) {
                CharacterComparator offByx = new OffByN(i);
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByx)) {
                    offarray[i] = offarray[i] + 1;
                }
            }
        }
        // max index code from gpt for convenience
        int max = offarray[0];
        int maxIndex = 0;
        for (int i = 1; i < offarray.length; i++) {
            if (offarray[i] > max) {
                max = offarray[i];
                maxIndex = i;  // 更新最大值的索引
            }
        }

        // 输出最大值和其索引
        System.out.println("数组中的最大值是: " + max);
        System.out.println("最大值的索引是: " + maxIndex);
        System.out.print(max);
    }
}
