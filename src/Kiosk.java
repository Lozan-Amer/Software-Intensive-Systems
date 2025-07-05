import java.util.*;

public class Kiosk {
    private Map<Category, List<Place>> data;
    private PersonalIdGenerator idGenerator;

    public Kiosk() {
        data = new HashMap<>();
        idGenerator = new PersonalIdGenerator();

        // דוגמה לטעינת קטגוריה אחת
        Category food = new Category("Food");
        List<Place> foodPlaces = Arrays.asList(
                new Place("Pizza Bar", "Main St 12", "Italian pizza", "10:00-22:00"),
                new Place("Bistro Italiano", "King George 5", "Pasta & wine", "11:00-23:00")
        );
        data.put(food, foodPlaces);
    }

    public List<Category> displayCategories() {
        return new ArrayList<>(data.keySet());
    }

    public List<Place> displayPlaces(Category category) {
        return data.getOrDefault(category, new ArrayList<>());
    }

    public String showPlaceDetails(Place place) {
        return "Name: " + place.getName() + "\n" +
                "Address: " + place.getAddress() + "\n" +
                "Hours: " + place.getOpeningHours();
    }

    public Route buildRoute() {
        Route route = new Route();
        for (List<Place> places : data.values()) {
            for (Place place : places) {
                route.addPlace(place);
            }
        }
        return route;
    }

    public String generatePersonalId() {
        return idGenerator.generateId();
    }

    public void displayQRCode() {
        System.out.println("[QR Code Displayed]");
    }
}
