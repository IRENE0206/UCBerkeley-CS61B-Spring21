package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testStudent() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        for (int i = 0; i < 10000; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                sad.addFirst(i);
                ads.addFirst(i);
                assertEquals("addFirst(" + i + ")\naddFirst(" + i +")", ads.size(), sad.size());
            } else if (operationNumber == 1) {
                sad.addLast(i);
                ads.addLast(i);
                assertEquals("addLast(" + i + "\naddLast(" + i +")", ads.size(), sad.size());
            } else if (operationNumber == 2 && sad.size() > 0 && ads.size() > 0) {
                Integer sadFirst = sad.removeFirst();
                Integer adsFirst = ads.removeFirst();
                assertEquals("removeFirst()\nremoveFirst()", adsFirst, sadFirst);
            } else if (operationNumber == 3 && sad.size() > 0 && ads.size() > 0) {
                Integer sadLast = sad.removeLast();
                Integer adsLast = ads.removeLast();
                assertEquals("removeLast()\nremoveLast()", adsLast, sadLast);
            }
        }
    }
}