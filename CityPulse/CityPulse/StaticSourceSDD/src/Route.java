import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Place> places = new ArrayList<>();

    public void addPlace(Place place) {
        places.add(place);
    }

    public List<Place> getPlaces() {
        return places;
    }
}
