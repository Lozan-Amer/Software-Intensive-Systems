
public class Place {
    private String address;
    private String description;
    private String name;
    private String openingHours;

    public Place(String name, String address, String description, String openingHours) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.openingHours = openingHours;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    @Override
    public String toString() {
        return name;
    }
}
