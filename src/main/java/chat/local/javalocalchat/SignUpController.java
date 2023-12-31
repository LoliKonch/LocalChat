package chat.local.javalocalchat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Sign up window controller class
 * @author Infobezdar'
 * @version 1.0
 */
public class SignUpController {

    private final ChangeWindow  ChangeWindow= new ChangeWindow();

    /**
     * Field regular expression for E-mail
     */
    private static final Pattern RCF2822_MAIL_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                    "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
                    "?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]" +
                    "?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\" +
                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    /**
     * Field regular expression for login
     */
    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9_]");

    @FXML
    private Button backButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Pane sideBackground;

    @FXML
    private Button signUpButton;

    /**
     * Window initialization and control procedure
     */
    @FXML
    void initialize() {

        // Adding a limiter (40) to the login field
        TextFieldLimiter.addTextLimiter(loginField, 40);
        // Adding a limiter (40) to the password field
        TextFieldLimiter.addTextLimiter(passwordField, 40);
        // Adding a limiter (100) to the E-mail field
        TextFieldLimiter.addTextLimiter(mailField, 100);

        // The event listener for the back button opens a Sign_in window
        backButton.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml"));

        // The event listener for the creating an account request button can open an E-mail_confirmation window
        signUpButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() &&
                passwordField.getText() != null && !passwordField.getText().trim().isEmpty() &&
                mailField.getText() != null && !mailField.getText().trim().isEmpty()) {

                if (LOGIN_PATTERN.matcher(loginField.getText()).find()) {

                    if (RCF2822_MAIL_PATTERN.matcher(mailField.getText()).matches()) {

                        Client.setUsername(loginField.getText().trim());
                        Client.setPassword(passwordField.getText().trim());
                        Client.setEmail(mailField.getText().trim());

                        try {
                            // Start connection to server
                            Client.startClient();
                            // Send creating an account request to server
                            Client.sendMessage(Client.getUsername());
                            Client.sendMessage("sign_up" +
                                    "|" + Client.getUsername() +
                                    "|" + Client.getPassword() +
                                    "|" + Client.getEmail());

                            String answer = Client.waitMessage();
                            if (answer.equals("successful_pre_sign_up")) {
                                ChangeWindow.changeWindowTo(sideBackground,
                                        "E-mail_confirmation.fxml",
                                        true);

                            } else {
                                ExceptionBox.createExceptionBox(sideBackground,
                                        "         Username already occupied");
                                Client.closeEverything();
                            }
                        } catch (IOException e) {
                            Client.closeEverything();
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "        Unable to connect to server" +
                                            "\n         Please try again later");
                        }
                    } else {
                        ExceptionBox.createExceptionBox(sideBackground, "Invalid E-mail address");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "                    Invalid Login");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}