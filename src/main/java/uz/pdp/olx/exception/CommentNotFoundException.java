package uz.pdp.olx.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(){super("Comment not found");}
}
