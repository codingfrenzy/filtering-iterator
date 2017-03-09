package framework;

import java.util.Iterator;

/*
Test: Create an Iterator filtering framework:
(1) filteringiterator.IObjectTest interface with a single boolean myTest(Object o) method and
(2) an implementation of Iterator (let's call it filteringiterator.FilteringIterator) which is
    initialized with another Iterator and an filteringiterator.IObjectTest instance:
    new filteringiterator.FilteringIterator(myIterator, myTest).
    Your filteringiterator.FilteringIterator will then allow iteration over 'myIterator',
    but skipping any objects which don't pass the 'myTest' myTest.

Create a simple unit myTest for this framework.
 */
public class FilteringIterator implements Iterator<Object> {
    /**
     * Keeps track of the internal iterator
     */
    private Iterator itr;

    /**
     * The filter test that is to be performed during iteration.
     */
    private IObjectTest myTest;

    /**
     * The next element that has passed the above test in the iterated collection.
     */
    private Object nextElement;

    /**
     * Constructor to create a FilterIterator object that takes in an iterator to a collection and a test that is to be performed on the data in the collection.
     * @param myIterator Iterator to the collection over which to iterate
     * @param myTest Filter test that has to be perfored for each data in the collection
     */
    public FilteringIterator(Iterator myIterator, IObjectTest myTest) {
        itr = myIterator;
        this.myTest = myTest;

        // find the first eligible element that passes the test
        // so that upon first invocation of hasNext() or next(), the value is ready.
        findNextEligibleElement();
    }

    /**
     * Helper method to validate if there are any more elements left in the collection.
     * @return true if there are more elements; else false and resets the nextElement
     */
    private boolean hasNextHelper() {
        if (!itr.hasNext()) {
            nextElement = null;
            return false;
        }
        return true;
    }

    /**
     * Method to find the next value in the collection that passes the filtering test.
     */
    private void findNextEligibleElement() {
        // check if end of collection is reached.
        if (!hasNextHelper())
            return;

        // get next element in the original collection using its iterator.
        nextElement = itr.next();

        // loop through of the remaining values in the original iterator until the IObjectTest test passes
        while (!myTest.test(nextElement)) {
            if (!hasNextHelper())
                return;

            // reaching here means that there are still more elements in the original collection AND the current value does not satisfy te IObjectTest condition.
            nextElement = itr.next();
        }

        // upon reaching here, nextElement either has a value that satisfies the test condition OR has reached the end of the original collection.
    }

    /**
     * Checks if there are any more valid (satisfies IObjectTest) elements left in the original iterated collection.
     * @return true if there exists a valid element that passes the test
     */
    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    /**
     * Returns the value of the next element that satisfies IObjectTest condition. Also moves the pointer to the next eligible element from the original collection.
     * @return the value that has passed the test
     */
    @Override
    public Object next() {
        // Save the current element for return
        Object element = nextElement;

        // find the next appropriate element
        findNextEligibleElement();

        // return saved element
        return element;
    }
}
