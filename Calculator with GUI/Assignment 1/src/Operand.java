
import java.util.*;

public class Operand {

     int value;

    public Operand(String token) {
            this.value = Integer.valueOf(token);
    }

    public Operand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean check(String token) {
        return token.matches("\\+|\\-|\\*|\\/|\\#|\\(|\\)|\\^");
    }
}