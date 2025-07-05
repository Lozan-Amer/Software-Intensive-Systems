package com.example.citypulse;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets; // ← שורה חשובה
import javafx.geometry.Pos;   // ← אם את משתמשת ב־setAlignment

import java.util.*;

public class SUC2Controller {

    @FXML private ComboBox<String> categoryComboBox;
    @FXML private ListView<String> placesListView;
    @FXML private ListView<String> selectedPlacesListView;
    @FXML private VBox placeDetailsBox;
    @FXML private Button continueOnMobileButton;

    private final Map<String, List<Place>> categoryPlaces = new HashMap<>();
    private final List<Place> selectedPlaces = new ArrayList<>();

    @FXML
    public void initialize() {
        initializeData();

        categoryComboBox.getItems().addAll(categoryPlaces.keySet());
        categoryComboBox.setOnAction(e -> handleCategorySelected());

        placesListView.setOnMouseClicked(e -> {
            String name = placesListView.getSelectionModel().getSelectedItem();
            if (name != null) {
                Place place = findPlaceByName(name);
                if (place != null) {
                    showPlaceDetails(place);
                }
            }
        });
    }

    @FXML
    public void handleAddPlace() {
        String name = placesListView.getSelectionModel().getSelectedItem();
        if (name != null) {
            Place place = findPlaceByName(name);
            if (place != null && !selectedPlaces.contains(place)) {
                selectedPlaces.add(place);
                selectedPlacesListView.getItems().add(place.getName());
            }
        }
    }

    @FXML
    public void handleContinueOnMobile() {
        // יצירת קוד בן 6 ספרות בלבד
        Random rand = new Random();
        String userId = String.format("%06d", rand.nextInt(1_000_000));

        // טעינת תמונת QR
        Image qrImage = new Image(getClass().getResourceAsStream("/com/example/citypulse/images/qrcode.png"));
        ImageView qrView = new ImageView(qrImage);
        qrView.setFitWidth(200);
        qrView.setPreserveRatio(true);

        // טקסט עליון - כותרת
        Label title = new Label("SCAN QR CODE TO DOWNLOAD\nCITYPULSE APPLICATION");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-text-alignment: center;");
        title.setAlignment(Pos.CENTER);
        title.setWrapText(true);

        // טקסט מזהה אישי
        Label codeLabel = new Label("Your Personal ID: " + userId);
        codeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #444444;");

        // הרכבת תצוגת חלון
        VBox content = new VBox(20, title, qrView, codeLabel);
        content.setPadding(new Insets(25));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 10; -fx-background-radius: 10;");

        Scene scene = new Scene(content);
        Stage popup = new Stage();
        popup.setTitle("Continue on your Mobile");
        popup.setScene(scene);
        popup.setWidth(360);
        popup.setHeight(420);
        popup.show();
    }


    @FXML
    public void handleGenerateRoute() {
        Random rand = new Random();

        Map<Place, Double> distanceMap = new HashMap<>();
        for (Place p : selectedPlaces) {
            double fakeDistance = 0.5 + rand.nextDouble() * 5.0;
            distanceMap.put(p, fakeDistance);
        }

        selectedPlaces.sort(Comparator.comparingDouble(distanceMap::get));

        selectedPlacesListView.getItems().clear();
        for (Place p : selectedPlaces) {
            double distance = distanceMap.get(p);
            String entry = String.format("%s (%.1f km)", p.getName(), distance);
            selectedPlacesListView.getItems().add(entry);
        }

        // ✅ הוסף שורה זו להצגת כפתור ההמשך
        continueOnMobileButton.setVisible(true);
    }

    private void handleCategorySelected() {
        String category = categoryComboBox.getValue();
        placesListView.getItems().clear();
        if (category != null && categoryPlaces.containsKey(category)) {
            for (Place p : categoryPlaces.get(category)) {
                placesListView.getItems().add(p.getName());
            }
        }
    }

    private void showPlaceDetails(Place place) {
        placeDetailsBox.getChildren().clear();
        placeDetailsBox.setSpacing(10);

        Label name = new Label("Name: " + place.getName());
        Label address = new Label("Address: " + place.getAddress());
        Label phone = new Label("Phone: " + place.getPhone());
        Label hours = new Label("Hours: " + place.getOpeningHours());

        for (Label label : List.of(name, address, phone, hours)) {
            label.setFont(Font.font("Arial", 16));
            label.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        }

        try {
            Image image = new Image(getClass().getResourceAsStream(
                    "/com/example/citypulse/images/" + place.getImageName()));

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(350);
            imageView.setPreserveRatio(true);

            VBox content = new VBox(10, name, address, phone, hours, imageView);
            content.setStyle("-fx-padding: 10;");
            placeDetailsBox.getChildren().add(content);

        } catch (Exception e) {
            Label error = new Label("Image not found: " + place.getImageName());
            error.setStyle("-fx-text-fill: red;");
            VBox content = new VBox(10, name, address, phone, hours, error);
            content.setStyle("-fx-padding: 10;");
            placeDetailsBox.getChildren().add(content);
        }
    }

    private Place findPlaceByName(String name) {
        return categoryPlaces.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }



    private void initializeData() {
        categoryPlaces.put("Restaurants", Arrays.asList(
                new Place("Burger Bar", "Main St 12", "09:00-23:00", "03-1234567", "burger.png"),
                new Place("Coffee Corner", "Ben Yehuda 8", "07:00-19:00", "03-7654321", "coffee.png"),
                new Place("Italiano", "Dizengoff 99", "12:00-23:00", "03-5556666", "italian.png"),
                new Place("Meat House", "Rothschild 10", "11:00-23:00", "03-7778888", "meat.png"),
                new Place("Pizza Town", "King George 55", "11:00-22:00", "03-1112222", "pizza.png"),
                new Place("Seafood Point", "Yarkon 23", "12:00-22:00", "03-8887777", "seafood.png"),
                new Place("Vegan Life", "Allenby 4", "08:00-21:00", "03-4443333", "vegan.png")
        ));

        categoryPlaces.put("Shopping", Arrays.asList(
                new Place("Azrieli Mall", "Azrieli 1", "10:00-22:00", "03-1234567", "mall1.png"),
                new Place("Dizengoff Center", "Dizengoff 50", "09:30-21:30", "03-9876543", "mall2.png"),
                new Place("TLV Mall", "Carlebach 6", "10:00-22:00", "03-1110000", "mall3.png"),
                new Place("Ramat Aviv Mall", "Brodetzky 43", "09:00-22:00", "03-1230000", "mall4.png"),
                new Place("Gindi TLV", "Hashmonaim 96", "10:00-21:00", "03-2223333", "mall5.png"),
                new Place("Arena Mall", "HaSharon Blvd", "10:00-22:00", "09-5556666", "mall6.png"),
                new Place("Grand Mall", "HaHistadrut 71", "09:30-21:00", "04-8889999", "mall7.png")
        ));

        categoryPlaces.put("Parks", Arrays.asList(
                new Place("Yarkon Park", "Yarkon River", "06:00-22:00", "03-2223655", "park1.png"),
                new Place("Gan Meir", "King George", "07:00-21:00", "09-5956699", "park2.png"),
                new Place("Charles Clore Park", "Tel Aviv Beach", "08:00-20:00", "02-5994820", "park3.png"),
                new Place("HaYarkon Garden", "Pinkas St", "06:30-21:30", "04-2561230", "park4.png"),
                new Place("Independence Park", "Ben Gurion Blvd", "06:00-23:00", "04-66998877", "park5.png"),
                new Place("Ramat Gan National Park", "Avraham Krinitzi", "07:00-20:00", "03-3366124", "park6.png"),
                new Place("Park Ariel Sharon", "Highway 4", "06:00-22:00", "09-8877575", "park7.png")
        ));

        categoryPlaces.put("Trails", Arrays.asList(
                new Place("Sea Trail", "Beachfront", "Open 24h", "-", "trail1.png"),
                new Place("Mountain Trail", "Carmel", "Open 24h", "-", "trail2.png"),
                new Place("River Walk", "Jordan Valley", "06:00-20:00", "0526489999", "trail3.png"),
                new Place("Urban Trail", "City Center", "08:00-20:00", "03-2511253", "trail4.png"),
                new Place("Nature Trail", "Galilee", "07:00-18:00", "03-1247775", "trail5.png"),
                new Place("Historic Trail", "Old City", "09:00-17:00", "04-9871475", "trail6.png")
        ));

        categoryPlaces.put("Attractions", Arrays.asList(
                new Place("Safari Ramat Gan", "Ramat Gan", "09:00-17:00", "03-2222222", "attraction2.png"),
                new Place("Superland", "Rishon LeZion", "10:00-18:00", "03-3333333", "attraction3.png"),
                new Place("Tel Aviv Museum", "Shaul HaMelech", "10:00-18:00", "03-4444444", "attraction4.png"),
                new Place("Eretz Israel Museum", "Levanon St", "10:00-18:00", "03-5555555", "attraction5.png"),
                new Place("Israel Aquarium", "Jerusalem", "09:00-17:00", "02-6666666", "attraction6.png"),
                new Place("Escape Room", "Allenby 100", "11:00-23:00", "03-7777777", "attraction17.png"),
                new Place("Ice Peaks", "Holon", "12:00-22:00", "03-8888888", "attraction18.png")
        ));

        categoryPlaces.put("Museums", Arrays.asList(
                new Place("Museum of Art", "King Saul St", "10:00-17:00", "03-9991111", "museum1.png"),
                new Place("Science Museum", "Weizmann St", "09:00-16:00", "03-8881234", "museum2.png"),
                new Place("Children's Museum", "Holon", "09:00-14:00", "03-2224444", "museum3.png"),
                new Place("History Museum", "Jaffa", "10:00-18:00", "03-3339999", "museum4.png"),
                new Place("Jewish Heritage Museum", "Rothschild", "10:00-16:00", "03-1118888", "museum5.png"),
                new Place("Natural History Museum", "HaUniversita", "09:00-15:00", "03-5556667", "museum6.png"),
                new Place("Modern Art Gallery", "Florentin", "11:00-19:00", "03-9990000", "museum8.png")
        ));
    }

    public static class Place {
        private final String name, address, openingHours, phone, imageName;

        public Place(String name, String address, String openingHours, String phone, String imageName) {
            this.name = name;
            this.address = address;
            this.openingHours = openingHours;
            this.phone = phone;
            this.imageName = imageName;
        }

        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getOpeningHours() { return openingHours; }
        public String getPhone() { return phone; }
        public String getImageName() { return imageName; }
    }
}
