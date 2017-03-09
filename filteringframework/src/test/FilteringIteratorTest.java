package test;

import framework.FilteringIterator;
import framework.IObjectTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FilteringIteratorTest {
    public static void main(String[] args) {
        System.out.println("Testing FilteringIterator: ");

        // in a given list of numbers, only retrieve even numbers
        testGetOnlyEvenNumbers();

        // in a given list of charactesr, only retrieve the vowels
        testGetOnlyVowels();
    }

    /**
     * Helper method to display the elements in a List that passes IObjectTest condition.
     * @param msg
     * @param list
     * @param itr
     */
    private static void displayContent(String msg, List list, Iterator itr) {
        StringBuilder sb = new StringBuilder();
        while (itr.hasNext()) {
            sb.append(itr.next());
            sb.append(", ");
        }
        System.out.println(msg + ": " + sb.toString());
    }

    /**
     * Test to retrieve only even numbers in a list of numbers.
     */
    private static void testGetOnlyEvenNumbers() {
        // initialize a list with random numbers
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Instantiate IObjectTest with a test method to check whether or not a number is even.
        IObjectTest getOnlyEvenNumbers = (a) -> ((Integer) a % 2 == 0);

        // instantiate FilterIterator with the above implementation of IObjectTest and the original list of numbers
        FilteringIterator itr = new FilteringIterator(list.iterator(), getOnlyEvenNumbers);

        // display the result
        displayContent("Only even numbers", list, itr);
    }

    /**
     * Test to retrieve only the vowels from a list of characters
     */
    private static void testGetOnlyVowels() {
        // initialize a list of random characters
        List<Character> list = Arrays.asList('b', 'c', 'i', 'f', 'a', 'u');

        // Instantiate IObjectTest with a test to check whether a given character is a vowel
        IObjectTest getOnlyVowels = (c) -> {
            char ch = (char) c;
            return ch == 'a' ||
                    ch == 'e' ||
                    ch == 'i' ||
                    ch == 'o' ||
                    ch == 'u';
        };

        // create an iterator with the above test and the original list of characters
        FilteringIterator itr = new FilteringIterator(list.iterator(), getOnlyVowels);

        // display the result
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