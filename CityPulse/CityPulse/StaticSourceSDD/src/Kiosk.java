import java.util.*;

public class Kiosk {
    private List<Category> categories = new ArrayList<>();
    private Map<Category, List<Place>> categoryPlaces = new HashMap<>();
    private PersonalIdGenerator idGenerator = new PersonalIdGenerator();

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setPlacesForCategory(Category category, List<Place> places) {
        categoryPlaces.put(category, places);
    }

    public List<Category> displayCategories() {
        return categories;
    }

    public List<Place> displayPlaces(Category category) {
        return categoryPlaces.getOrDefault(category, new ArrayList<>());
    }

    public String showPlaceDetails(Place place) {
        return "Name: " + place.getName() + "\nAddress: " + place.getAddress()
                + "\nHours: " + place.getOpeningHours();
    }

    public Route buildRoute(List<Place> selectedPlaces) {
        Route route = new Route();
        for (Place p : selectedPlaces) {
            route.addPlace(p);
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
