package framework;

import java.util.Iterator;

/*
Test: Create an Iterator filtering framework:
(1) filteringiterator.IObjectTest interface with a single boolean myTest(Object o) method and
(2) an implementation of Iterator (let's call it filteringiterator.FilteringIterator) which is initialized with another Iterator and an filteringiterator.IObjectTest instance: new filteringiterator.FilteringIterator(myIterator, myTest). Your filteringiterator.FilteringIterator will then allow iteration over 'myIterator', but skipping any objects which don't pass the 'myTest' myTest. Create a simple unit myTest for this framework.
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
     * The next element that passes the above test in the iterated collection.
     */
    private Object nextElement;

    /**
     * Constructor to create a FilterIterator object that takes in an iterator to a collection and a test that is to be performed on the data in the collection.
     * @param myIterator
     * @param myTest
     */
    public FilteringIterator(Iterator myIterator, IObjectTest myTest) {
        itr = myIterator;
        this.myTest = myTest;
        findNextEligibleElement();
    }

    /**
     * Helper method to find
     * @return
     */
    private boolean hasNextHelper() {
        if (!itr.hasNext()) {
            nextElement = null;
            return false;
        }
        return true;
    }

    private void findNextEligibleElement() {
        if (!hasNextHelper())
            return;

        nextElement = itr.next();
        while (!myTest.test(nextElement)) {
            if (!hasNextHelper())
                return;

            nextElement = itr.next();
        }
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public Object next() {
        Object element = nextElement;
        findNextEligibleElement();
        return element;
    }
}
