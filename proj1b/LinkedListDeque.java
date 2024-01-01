public class LinkedListDeque<T> implements Deque<T> {

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }

        private T get(int index) {
            if (index == 0) {
                return item;
            } else {
                return this.next.get(index - 1);
            }
        }
    }

    private int size;
    private Node<T> first, last;

    public LinkedListDeque() {
        this.first = null;
        this.last = null;
        size = 0;
    }
    public void addFirst(T item) {
        size++;
        if (first == null) { // when first and tail is null
            first = new Node<>(item);
            last = first;
            return;
        }

        Node<T> tmp = first;
        first = new Node<>(item);
        tmp.prev = first;
        first.next = tmp;
    }

    public void addLast(T item) {
        size++;
        if (last == null) { // when first and tail is null
            first = new Node<>(item);
            last = first;return;
        }

        Node<T> tmp = last;
        last = new Node<>(item);
        tmp.next = last;
        last.prev = tmp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = first;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print('\n');
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = first.item;
        size--;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = last.item;
        size--;
        if (first == last) { // when size == 1
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    public T get(int index) {
        if (isEmpty() && index >= size()) {
            return null;
        }
        Node<T> p = first;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return first.get(index);
    }
}
