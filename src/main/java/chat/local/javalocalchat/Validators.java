package chat.local.javalocalchat;

import customexceptions.InvalidConfirmationCodeException;
import javafx.scene.control.PasswordField;

public class Validators {
    public static String confirmationCodeValidator(PasswordField confirmationCodeField) throws InvalidConfirmationCodeException {
        if (confirmationCodeField.getText() != null && !confirmationCodeField.getText().trim().isEmpty()){
            return confirmationCodeField.getText();
        } else {
            throw new InvalidConfirmationCodeException("All fields must be filled in");
        }
    }
}
