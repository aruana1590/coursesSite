package kz.aruana.coursesSite.exceptions;

public class AlreadyExists extends  RuntimeException{
    public AlreadyExists(String message) {
        super(message);
    }
}
