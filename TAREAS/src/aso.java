import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;

public class aso {

    public static void main (String[] args) {

        System.out.println(getTotalNumberOfLetters("Pedro","Juan","Armando","Goku","Adriana"));
    }

    public static int getTotalNumberOfLetters(String... number){
        return Arrays.stream(number).filter(i -> i.length() > 5).mapToInt(i -> i.length()).sum();


    }
}
