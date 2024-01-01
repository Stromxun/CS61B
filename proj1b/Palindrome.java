
public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> str = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            str.addLast(ch);
        }

        return str;
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        Deque<Character> list = wordToDeque(word);

        while (list.size() > 1) {
            if (!(list.removeFirst()).equals(list.removeLast())) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator  cc) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        Deque<Character> list = wordToDeque(word);

        while (list.size() > 1) {
            if (!cc.equalChars(list.removeFirst(), list.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
