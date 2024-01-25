package uz.pdp.olx.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("product not found");
    }
}
