package com.example.citypulse;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class QRController {

    @FXML private ImageView qrImage;
    @FXML private Label codeLabel;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com.example.citypulse/images/qrcode.png"));
        qrImage.setImage(image);

        int code = new Random().nextInt(900000) + 100000;
        codeLabel.setText("Your Code: " + code);
    }
}
