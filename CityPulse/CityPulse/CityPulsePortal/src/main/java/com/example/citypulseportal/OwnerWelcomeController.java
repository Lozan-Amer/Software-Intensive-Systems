package com.example.citypulseportal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OwnerWelcomeController {

    @FXML private Label welcomeLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("WELCOME Business Owner " + user.getBusiness().getName());
    }

    @FXML
    public void handleUpdateInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("owner_dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("CityPulse Dashboard");
            stage.setScene(new Scene(loader.load()));

            OwnerDashboardController controller = loader.getController();
            controller.setUser(user);

            stage.show();

            // Close the welcome window
            welcomeLabel.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
