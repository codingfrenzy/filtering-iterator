package test;

import framework.FilteringIterator;
import framework.IObjectTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FilteringIteratorTest {
    public static void main(String[] args) {
        System.out.println("Testing FilteringIterator: ");

        testGetOnlyEvenNumbers();
        testGetOnlyVowels();
    }

    private static void displayContent(String msg, List list, Iterator itr) {
        StringBuilder sb = new StringBuilder();
        while (itr.hasNext()) {
            sb.append(itr.next());
            sb.append(", ");
        }
        System.out.println(msg + ": " + sb.toString());
    }

    private static void testGetOnlyEvenNumbers() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        IObjectTest getOnlyEvenNumbers = (a) -> ((Integer) a % 2 == 0);
        FilteringIterator itr = new FilteringIterator(list.iterator(), getOnlyEvenNumbers);
        displayContent("Only even numbers", list, itr);
    }

    private static void testGetOnlyVowels() {
        List<Character> list = Arrays.asList('b', 'c', 'i', 'f', 'a', 'u');
        IObjectTest getOnlyVowels = (c) -> {
            char ch = (char) c;
            return ch == 'a' ||
                    ch == 'e' ||
                    ch == 'i' ||
                    ch == 'o' ||
                    ch == 'u';
        };
        FilteringIterator itr = new FilteringIterator(list.iterator(), getOnlyVowels);
        displayContent("Only vowels from a list of characters", list, itr);
    }
}

/**
 * Sample output:
 * <p>
 * Testing FilteringIterator:
 * Only even numbers: 2, 4, 6,
 * Only vowels from a list of characters: i, a, u,
 */