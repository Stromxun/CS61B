public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int lengthOfArray;
    private int first, last;
    public ArrayDeque() {
        lengthOfArray = 8;
        array = (T[]) new Object[lengthOfArray];
        first = 0;
        last = 0;
    }

    private boolean testUsageFactor() { // prevent the usage factor < 0.25
        double ratio = size * 1.0 / lengthOfArray;
        return ratio >= 0.25;
    }

    private boolean isFull() {
        return size() == lengthOfArray;
    }

    private void resize() {
        int p = first, k = 0, remainder = lengthOfArray;
        lengthOfArray = (lengthOfArray * 2);
        T[] newArray = (T[]) new Object[lengthOfArray];
        while (remainder > 0) {
            newArray[k] = array[p];
            p = (p + 1) % lengthOfArray;
            remainder--;
            k++;
        }
        array = newArray;
        first = 0;
        last = size;
    }


    public void addFirst(T item) {
        if (isFull()) {
            resize();
        }
        first = (first + lengthOfArray - 1) % lengthOfArray;
        array[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize();
        }
        array[last] = item;
        last = (last + lengthOfArray + 1) % lengthOfArray;
        size++;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return  size;
    }

    public void printDeque() {
        int p = first;
        while (p != last) {
            System.out.print(array[p] + " ");
            p = (p + 1) % lengthOfArray;
        }
        System.out.print('\n');
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = array[first];
        first = (first + lengthOfArray + 1) % lengthOfArray;
        return item;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = array[first];
        last = (last + lengthOfArray - 1) % lengthOfArray;
        return item;
    }

    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        int p = first;
        while (index > 0) {
            index--;
            p = (p + lengthOfArray + 1) % lengthOfArray;
        }
        return array[p];
    }
}
