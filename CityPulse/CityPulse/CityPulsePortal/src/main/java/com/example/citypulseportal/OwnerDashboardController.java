package com.example.citypulseportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OwnerDashboardController {

    @FXML private TextField businessNameField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextField openingHoursField;
    @FXML private TextArea promotionsArea;
    @FXML private ImageView businessImageView;

    private BusinessData businessData;

    public void setUser(User user) {
        this.businessData = user.getBusiness(); // <-- שימו לב לשינוי כאן

        businessNameField.setText(businessData.getName());
        phoneField.setText(businessData.getPhone());
        addressField.setText(businessData.getAddress());
        openingHoursField.setText(businessData.getHours());
        promotionsArea.setText(businessData.getDescription());

        if (businessData.getImageFileName() != null) {
            Image image = new Image(getClass().getResourceAsStream("/images/" + businessData.getImageFileName()));
            businessImageView.setImage(image);
        }
    }

    @FXML
    private void handleSave() {
        businessData.setName(businessNameField.getText());
        businessData.setPhone(phoneField.getText());
        businessData.setAddress(addressField.getText());
        businessData.setHours(openingHoursField.getText());
        businessData.setDescription(promotionsArea.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved successfully.");
        alert.show();
    }

    @FXML
    private void handleUploadImage() {
        // פעולה לעתיד (כדי שהשגיאה לא תופיע)
    }
}
