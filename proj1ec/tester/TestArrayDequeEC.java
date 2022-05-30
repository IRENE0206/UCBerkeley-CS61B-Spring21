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
                int randomValue = StdRandom.uniform(0, 100);
                sad.addFirst(randomValue);
                ads.addFirst(randomValue);
                assertEquals("addFirst(" + randomValue + "\naddFirst(" + randomValue +")", ads.size(), sad.size());
            } else if (operationNumber == 1) {
                int randomValue = StdRandom.uniform(0, 100);
                sad.addLast(randomValue);
                ads.addLast(randomValue);
                assertEquals("addLast(" + randomValue + "\naddLast(" + randomValue +")", ads.size(), sad.size());
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