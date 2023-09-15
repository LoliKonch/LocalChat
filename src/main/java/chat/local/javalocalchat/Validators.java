package chat.local.javalocalchat;

import customexceptions.InvalidDataException;
import javafx.scene.control.PasswordField;
import java.util.regex.Pattern;


public class Validators {

    private static final Pattern RCF2822_MAIL_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                    "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
                    "?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]" +
                    "?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\" +
                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    public static String confirmationCodeValidator(PasswordField confirmationCodeField) throws InvalidDataException {
        if (confirmationCodeField.getText() != null && !confirmationCodeField.getText().trim().isEmpty()){
            return confirmationCodeField.getText().trim();
        } else {
            throw new InvalidDataException("All fields must be filled in");
        }
    }

    public static String EmailValidator(PasswordField mailField) throws InvalidDataException {
        if (mailField.getText() != null && !mailField.getText().trim().isEmpty()){
            if (RCF2822_MAIL_PATTERN.matcher(mailField.getText()).matches()) {
                return mailField.getText().trim();
            } else {
                throw new InvalidDataException("Invalid E-mail address");
            }
        } else {
            throw new InvalidDataException("All fields must be filled in");
        }
    }
}
