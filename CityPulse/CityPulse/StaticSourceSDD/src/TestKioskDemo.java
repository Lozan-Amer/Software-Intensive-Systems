import java.util.*;

public class TestKioskDemo {
    public static void main(String[] args) {
        // Step 1: Create Places
        Place place1 = new Place("Pizza Bar", "Main St 12", "Great pizza place", "10:00-22:00");
        Place place2 = new Place("Bistro Italiano", "King St 3", "Italian cuisine", "11:00-23:00");

        // Step 2: Create Category and assign places
        Category foodCategory = new Category("Food");
        List<Place> foodPlaces = new ArrayList<>();
        foodPlaces.add(place1);
        foodPlaces.add(place2);

        // Step 3: Initialize Kiosk and simulate interaction
        Kiosk kiosk = new Kiosk();
        kiosk.setCategories(Arrays.asList(foodCategory));
        kiosk.setPlacesForCategory(foodCategory, foodPlaces);

        System.out.println("== Display Categories ==");
        List<Category> categories = kiosk.displayCategories();
        for (Category c : categories) {
            System.out.println("- " + c.getName());
        }

        System.out.println("\n== Display Places in 'Food' ==");
        List<Place> selectedPlaces = kiosk.displayPlaces(foodCategory);
        for (Place p : selectedPlaces) {
            System.out.println("- " + p.getName());
        }

        System.out.println("\n== Show Details of Place ==");
        System.out.println(kiosk.showPlaceDetails(place1));

        System.out.println("\n== Build Route ==");
        Route route = kiosk.buildRoute(selectedPlaces);
        for (Place p : route.getPlaces()) {
            System.out.println("-> " + p.getName());
        }

        System.out.println("\n== Generate Personal ID and Display QR ==");
        System.out.println("ID: " + kiosk.generatePersonalId());
        kiosk.displayQRCode();
    }
}
