import static org.junit.Assert.*;
import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {


    private boolean isEmpty(LinkedList<Integer> a, StudentArrayDeque<Integer> b) {
        return a.isEmpty() && b.isEmpty();
    }

    @Test
    public void testStudentArrayDeque() {
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();

        final int bound = 10;

        for (int i = 0; i < bound; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                expect.addLast(i);
                stu.addLast(i);
            } else {
                expect.addFirst(i);
                stu.addFirst(i);
            }
        }

        Integer exp, real;
        String method;
        for (int i = 0; i < bound; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                exp = expect.removeLast();
                real = stu.removeLast();
                method = "removeLast()";
            } else {
                exp = expect.removeFirst();
                real = stu.removeFirst();
                method = "removeFirst";
            }
            assertEquals("Oh noooo!\nThis is bad Method:\n   " + method + "  actual:" + real
                                    + " not equal to expect:" + exp + "!", exp, real);
        }

    }
}
