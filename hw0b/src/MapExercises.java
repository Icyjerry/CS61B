import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> alpha = new TreeMap<>();
        alpha.put('a', 1);
        alpha.put('b', 2);
        alpha.put('c', 3);
        alpha.put('d', 4);
        alpha.put('e', 5);
        alpha.put('f', 6);
        alpha.put('g', 7);
        alpha.put('h', 8);
        alpha.put('i', 9);
        alpha.put('j', 10);
        alpha.put('k', 11);
        alpha.put('l', 12);
        alpha.put('m', 13);
        alpha.put('n', 14);
        alpha.put('o', 15);
        alpha.put('p', 16);
        alpha.put('q', 17);
        alpha.put('r', 18);
        alpha.put('s', 19);
        alpha.put('t', 20);
        alpha.put('u', 21);
        alpha.put('v', 22);
        alpha.put('w', 23);
        alpha.put('x', 24);
        alpha.put('y', 25);
        alpha.put('z', 26);
        return alpha;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> squares = new TreeMap<>();
        for (Integer num : nums) {
            squares.put(num, num * num);
        }
        return squares;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }
}
