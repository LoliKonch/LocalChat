package chat.local.javalocalchat;

import customexceptions.InvalidDataException;
import javafx.scene.control.PasswordField;

public class Validators {

    public static String confirmationCodeValidator(PasswordField confirmationCodeField) throws InvalidDataException {
        if (confirmationCodeField.getText() != null && !confirmationCodeField.getText().trim().isEmpty()){
            return confirmationCodeField.getText().trim();
        } else {
            throw new InvalidDataException("All fields must be filled in");
        }
    }

    public static String EmailValidator(PasswordField confirmationCodeField) throws InvalidDataException {
        if (confirmationCodeField.getText() != null && !confirmationCodeField.getText().trim().isEmpty()){
            return confirmationCodeField.getText().trim();
        } else {
            throw new InvalidDataException("All fields must be filled in");
        }
    }
}
