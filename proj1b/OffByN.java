public class OffByN implements CharacterComparator {

    private final int N;

    public OffByN(int N) {
        this.N = N;
    }

    private int diff(int x, int y) {
        return Math.abs(x - y);
    }

    @Override
    public boolean equalChars(char x, char y) {
        return diff(x, y) == N;
    }
}
