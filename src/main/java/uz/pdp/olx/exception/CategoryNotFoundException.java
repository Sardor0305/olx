package uz.pdp.olx.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("category bot found");
    }
}
