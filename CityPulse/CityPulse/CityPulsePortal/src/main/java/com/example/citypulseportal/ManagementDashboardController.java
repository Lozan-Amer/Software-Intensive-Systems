package com.example.citypulseportal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementDashboardController {

    @FXML private Label messageLabel;

    public void setUser(User user) {
        messageLabel.setText("Welcome, FDL");
    }

    @FXML
    public void handleManageCategories() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("category_management.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage Categories");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open categories window.");
        }
    }

    @FXML
    public void handleViewReports() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("usage_reports.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Usage Reports");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open usage reports.");
        }
    }

    @FXML
    public void handleMonitorCameras() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("camera_monitor.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Camera Monitor");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open camera monitor.");
        }
    }
}
