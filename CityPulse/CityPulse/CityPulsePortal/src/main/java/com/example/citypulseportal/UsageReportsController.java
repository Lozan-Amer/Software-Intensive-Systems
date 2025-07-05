package com.example.citypulseportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class UsageReportsController {

    @FXML private ComboBox<String> reportTypeComboBox;
    @FXML private DatePicker reportDatePicker;
    @FXML private TextArea reportResultArea;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        reportTypeComboBox.getItems().addAll(
                "Search Queries",
                "Popular Categories",
                "Preferred Hours",
                "Popular Keywords",
                "Searches per Day"
        );
        reportTypeComboBox.getSelectionModel().selectFirst();
        reportDatePicker.setValue(LocalDate.now());
    }

    @FXML
    public void handleGenerateReport() {
        String type = reportTypeComboBox.getValue();
        LocalDate date = reportDatePicker.getValue();

        if (type == null || date == null) {
            statusLabel.setText("Please select report type and date.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        StringBuilder report = new StringBuilder("Report Type: " + type + "\nDate: " + date + "\n\n");

        switch (type) {
            case "Search Queries" -> report.append("- Zoo near me\n- Open Mall\n- Vegan Restaurants");
            case "Popular Categories" -> report.append("1. Restaurants\n2. Parks\n3. Museums\n4. Shopping");
            case "Preferred Hours" -> report.append("• 18:00–21:00\n• 12:00–14:00\n• 10:00–11:00");
            case "Popular Keywords" -> report.append("'Open now'\n'Discount'\n'Kids'\n'Near me'");
            case "Searches per Day" -> report.append("Wednesday: 12\nThursday: 31\nFriday: 18\nSaturday: 43");
            default -> report.append("No data available.");
        }

        reportResultArea.setText(report.toString());
        statusLabel.setText("Report generated successfully.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
