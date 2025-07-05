package com.example.citypulseportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private static final Map<String, BusinessData> businessAccounts = new HashMap<>();

    static {
        businessAccounts.put("superland", new BusinessData(
                "Superland", "Rishon Lezion", "03-1234567", "10:00-18:00",
                "Amusement park with rides and attractions", "attraction3.png", "1234"
        ));
        businessAccounts.put("italian_rest", new BusinessData(
                "La Piazza", "Rothschild 10, Tel Aviv", "03-7654321", "12:00-23:00",
                "Italian restaurant with wood oven pizzas", "italian.png", "4321"
        ));
        businessAccounts.put("telaviv_market", new BusinessData(
                "Tel Aviv Market", "Allenby 30, Tel Aviv", "03-9998877", "08:00-20:00",
                "Lively Tel Aviv market with food, clothes, and culture", "mall2.png", "5678"
        ));
    }

    private User authenticate(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new User(username, password, UserType.MANAGEMENT);
        } else if (businessAccounts.containsKey(username)) {
            BusinessData data = businessAccounts.get(username);
            if (data.getPassword().equals(password)) {
                return new User(username, password, UserType.BUSINESS_OWNER, data);
            }
        }
        return null;
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        User user = authenticate(username, password);

        if (user != null) {
            try {
                FXMLLoader loader;
                Stage stage = new Stage();
                Scene scene;

                if (user.getUserType() == UserType.BUSINESS_OWNER) {
                    // Load welcome screen first
                    loader = new FXMLLoader(getClass().getResource("/com/example/citypulseportal/owner_welcome.fxml"));
                    scene = new Scene(loader.load());
                    OwnerWelcomeController controller = loader.getController();
                    controller.setUser(user);  // pass user to controller
                } else {
                    // Admin
                    loader = new FXMLLoader(getClass().getResource("/com/example/citypulseportal/management_dashboard.fxml"));
                    scene = new Scene(loader.load());
                    ManagementDashboardController controller = loader.getController();
                    controller.setUser(user);
                }

                stage.setScene(scene);
                stage.setTitle("CityPulse Dashboard");
                stage.show();

                ((Stage) usernameField.getScene().getWindow()).close();

            } catch (IOException e) {
                e.printStackTrace();
                showError("Failed to load dashboard.");
            }
        } else {
            showError("Invalid username or password.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.show();
    }
}
