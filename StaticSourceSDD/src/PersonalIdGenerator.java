
import java.util.Random;

public class PersonalIdGenerator {
    private int personalId;

    public String generateId() {
        Random rand = new Random();
        personalId = 100000 + rand.nextInt(900000); // 6-digit code
        return String.valueOf(personalId);
    }
}
