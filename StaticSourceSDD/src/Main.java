
public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();

        System.out.println("Categories:");
        for (Category c : kiosk.displayCategories()) {
            System.out.println("- " + c.getName());

            for (Place p : kiosk.displayPlaces(c)) {
                System.out.println(kiosk.showPlaceDetails(p));
            }
        }

        System.out.println("\nGenerated Route:");
        Route route = kiosk.buildRoute();
        for (Place p : route.getPlaces()) {
            System.out.println("- " + p.getName());
        }

        System.out.println("\nGenerated Personal ID:");
        System.out.println(kiosk.generatePersonalId());

        kiosk.displayQRCode();
    }
}
