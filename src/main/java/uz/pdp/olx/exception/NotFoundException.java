package uz.pdp.olx.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg) {
        super(msg + " not found");
    }
}
