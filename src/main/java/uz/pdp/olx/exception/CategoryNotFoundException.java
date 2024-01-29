package uz.pdp.olx.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("category not found");
    }
    public CategoryNotFoundException(String s) {
        super(s+"parent category not found");
    }



}
