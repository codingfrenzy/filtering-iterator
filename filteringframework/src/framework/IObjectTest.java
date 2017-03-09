package framework;

/**
 * Class that holds the test condition that each value in a collection has to pass, in order to be filtered appropriately
 */
public interface IObjectTest {
    /**
     * Method to test the value in a iterated collection according to some arbitrary condition.
     * @param o Value of any type
     * @return true if test passes; else false
     */
    boolean test(Object o);
}
