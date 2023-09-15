package customexceptions;

public class InvalidConfirmationCodeException extends Exception{
    public InvalidConfirmationCodeException(String message) {
        super(message);
    }
}
