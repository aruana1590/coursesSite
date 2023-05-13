package kz.aruana.coursesSite.exceptions;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException(String message) {
        super(message);
    }
}
