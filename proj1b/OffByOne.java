
public class OffByOne implements CharacterComparator {



    private int diff(int x, int y) {
        return Math.abs(x - y);
    }

    @Override
    public boolean equalChars(char x, char y) {
        return diff(x, y) == 1;
    }
}
