package Exceptions;

/**
 * This is our own exception.
 * When user enters an integer which is not listed on
 * menu, this will be thrown.
 */
public class IndexNotFoundException extends Exception {

    public IndexNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
