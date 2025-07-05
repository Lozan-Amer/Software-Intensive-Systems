import java.util.Random;

public class PersonalIdGenerator {
    private int personalId;

    public String generateId() {
        Random rand = new Random();
        personalId = 100000 + rand.nextInt(900000); // מספר בן 6 ספרות
        return String.valueOf(personalId);
    }
}
