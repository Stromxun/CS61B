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

        final int bound = 100;
        Integer exp = 0, real = 0;
        String log = "";
        for (int i = 0; i < bound; i++) {
            boolean empty = !isEmpty(expect, stu);
            double numberBetweenZeroAndOne = empty ? StdRandom.uniform() : StdRandom.uniform(0.5, 1);
            if (numberBetweenZeroAndOne < 0.25) {
                exp = expect.removeLast();
                real = stu.removeLast();
                log = log + "removeLast()\n";
            } else if (numberBetweenZeroAndOne < 0.5){
                exp = expect.removeFirst();
                real = stu.removeFirst();
                log += "removeFirst()\n";
            }else if (numberBetweenZeroAndOne < 0.75) {
                expect.addLast(i);
                stu.addLast(i);
                log += "addLast(" + i +")\n";
            }else {
                expect.addFirst(i);
                stu.addFirst(i);
                log += "addFirst(" + i +")\n";
            }
            assertEquals(log, exp, real);

        }
    }
}
