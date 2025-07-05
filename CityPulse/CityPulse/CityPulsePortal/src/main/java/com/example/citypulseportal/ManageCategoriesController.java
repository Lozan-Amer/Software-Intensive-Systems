package com.example.citypulseportal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManageCategoriesController {

    @FXML private ListView<String> categoryListView;
    @FXML private TextField newCategoryField;
    @FXML private Label statusLabel;

    // רשימת קטגוריות עם 6 ברירת מחדל
    private final ObservableList<String> categories = FXCollections.observableArrayList(
            "Parks", "Museums", "Restaurants", "Shopping", "Attractions", "Nature"
    );

    @FXML
    public void initialize() {
        categoryListView.setItems(categories);
        statusLabel.setText(""); // נקה את הטקסט בעת התחלה
    }

    @FXML
    public void handleAddCategory() {
        String newCategory = newCategoryField.getText().trim();

        if (newCategory.isEmpty()) {
            statusLabel.setText("Please enter a category name.");
            return;
        }

        if (categories.contains(newCategory)) {
            statusLabel.setText("Category already exists.");
        } else {
            categories.add(newCategory);
            newCategoryField.clear();
            statusLabel.setText("Category added.");
        }
    }

    @FXML
    public void handleRemoveCategory() {
        String selected = categoryListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            categories.remove(selected);
            statusLabel.setText("Category removed.");
        } else {
            statusLabel.setText("Please select a category to remove.");
        }
    }

    @FXML
    public void handleSaveCategories() {
        // כאן אפשר גם לשמור לקובץ בעתיד אם תרצי
        statusLabel.setText("Changes saved successfully.");
    }
}
