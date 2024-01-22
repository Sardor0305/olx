package uz.pdp.olx.exception;


public class TokenIsExpiredException extends RuntimeException{

    public TokenIsExpiredException(String message) {
        super(message);
    }

    public static TokenIsExpiredException expiryDate() {
        return new TokenIsExpiredException("Token is expired : ");
    }
}
