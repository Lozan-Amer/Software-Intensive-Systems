package com.example.citypulseportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CameraMonitorController {

    @FXML private ComboBox<String> cameraSelector;
    @FXML private ImageView cameraImage;
    @FXML private TextArea behaviorArea;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        // הוספת שמות מצלמות לדוגמה
        cameraSelector.getItems().addAll(
                "Camera 1 - Entrance",
                "Camera 2 - Food Court",
                "Camera 3 - Parking Lot"
        );
        cameraSelector.getSelectionModel().selectFirst();

        // טעינת תמונת מצלמה דמה - שימוש בנתיב הנכון מתוך resources/images
        Image image = new Image(getClass().getResource("/images/streetcamera.jpg").toExternalForm());
        cameraImage.setImage(image);

        // טקסט התנהגות לדוגמה
        behaviorArea.setText("Group of teenagers loitering near the entrance. No immediate danger.");
    }

    @FXML
    public void handleReportIncident() {
        statusLabel.setText("Incident has been reported successfully.");
    }
}
