import java.io.IOException;
import java.util.Scanner;

public class ConsoleInterface {
    public static void Initialize() {
        Scanner scanner = new Scanner(System.in);
        boolean isSuccessful = false;
        while (!isSuccessful) {
            System.out.println("Write path to an existing file");
            String pathFrom = scanner.nextLine();
            System.out.println("Write path to a new file");
            String pathTo = scanner.nextLine();
            Parser p = new Parser(pathFrom, pathTo);

            try {
                p.count();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            }
            isSuccessful = true;
        }
        System.out.println("Successfully done!");
    }
}
