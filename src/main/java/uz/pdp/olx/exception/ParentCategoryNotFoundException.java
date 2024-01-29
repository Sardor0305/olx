package uz.pdp.olx.exception;

public class ParentCategoryNotFoundException extends RuntimeException{
    public ParentCategoryNotFoundException() {
        super("parent category not found");
    }
}
