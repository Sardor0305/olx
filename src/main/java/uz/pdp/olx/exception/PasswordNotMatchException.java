package uz.pdp.olx.exception;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
        super("password not match");
    }
}
