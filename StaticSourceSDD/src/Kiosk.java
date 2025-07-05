
import java.util.*;

public class Kiosk {
    private List<Category> categories;
    private Map<Category, List<Place>> categoryPlaces;
    private PersonalIdGenerator idGenerator;

    public Kiosk() {
        categories = new ArrayList<>();
        categoryPlaces = new HashMap<>();
        idGenerator = new PersonalIdGenerator();

        // Example data setup (can be modified or loaded externally)
        setupData();
    }

    public List<Category> displayCategories() {
        return categories;
    }

    public List<Place> displayPlaces(Category category) {
        return categoryPlaces.getOrDefault(category, new ArrayList<>());
    }

    public String showPlaceDetails(Place place) {
        return "Name: " + place.getName() + "\n"
                + "Address: " + place.getAddress() + "\n"
                + "Opening Hours: " + place.getOpeningHours() + "\n"
                + "Description: " + place.getDescription();
    }

    public Route buildRoute() {
        Route route = new Route();
        for (List<Place> places : categoryPlaces.values()) {
            for (Place place : places) {
                route.addPlace(place);
            }
        }
        return route;
    }

    public void displayQRCode() {
        System.out.println("Displaying QR code for the route (not implemented visually).");
    }

    public String generatePersonalId() {
        return idGenerator.generateId();
    }

    private void setupData() {
        Category parks = new Category("Parks");
        Category museums = new Category("Museums");
        categories.add(parks);
        categories.add(museums);

        Place park1 = new Place("Central Park", "123 Main St", "A large urban park", "8am - 8pm");
        Place museum1 = new Place("City Museum", "45 History Ave", "Art and history exhibits", "10am - 6pm");

        categoryPlaces.put(parks, List.of(park1));
        categoryPlaces.put(museums, List.of(museum1));
    }
}
