package uz.pdp.olx.exception;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(String msg) {
        super(msg + " is exist");
    }
}
