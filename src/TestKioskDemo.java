public class TestKioskDemo {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();

        System.out.println("== Display Categories ==");
        for (Category category : kiosk.displayCategories()) {
            System.out.println("- " + category.getName());
        }

        // נניח שבוחרים את הקטגוריה הראשונה
        Category selectedCategory = kiosk.displayCategories().get(0);

        System.out.println("\n== Display Places in '" + selectedCategory.getName() + "' ==");
        for (Place place : kiosk.displayPlaces(selectedCategory)) {
            System.out.println("- " + place.getName());
        }

        // נניח שבוחרים את המקום הראשון
        Place selectedPlace = kiosk.displayPlaces(selectedCategory).get(0);

        System.out.println("\n== Show Details of Place ==");
        System.out.println(kiosk.showPlaceDetails(selectedPlace));

        System.out.println("\n== Build Route ==");
        Route route = kiosk.buildRoute();
        for (Place p : route.getPlaces()) {
            System.out.println("-> " + p.getName());
        }

        System.out.println("\n== Generate Personal ID and Display QR ==");
        String personalId = kiosk.generatePersonalId();
        System.out.println("ID: " + personalId);
        kiosk.displayQRCode();

        System.out.println("\nProcess finished successfully.");
    }
}
