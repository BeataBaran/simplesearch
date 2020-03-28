import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Searcher {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("search> ");
            String input = br.readLine();
            if (input.equals(":quit")) {
                break;
            }
        }
    }
}
