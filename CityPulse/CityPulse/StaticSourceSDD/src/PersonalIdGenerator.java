import java.util.Random;

public class PersonalIdGenerator {
    public String generateId() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}
