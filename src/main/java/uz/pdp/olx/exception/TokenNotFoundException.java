package uz.pdp.olx.exception;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(String message) {
        super(message);
    }

    public static TokenNotFoundException byToken(String token) {
        return new TokenNotFoundException("Token not found : " + token);
    }
}
