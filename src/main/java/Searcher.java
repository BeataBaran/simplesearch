import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Searcher {

    public static void main(String[] args) throws IOException {
        validateProgramArguments(args);
        Map<String, String> filesWithContent = loadFilesIntoMemory(args);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("search> ");
            String input = br.readLine();
            if (input.equals(":quit")) {
                System.exit(0);
            }
        }
    }

    private static void validateProgramArguments(String[] args) {
        if (args.length < 2) {
            System.out.println("Please try again with directory as program parameter! " +
                    "Example: java -jar SimpleSearch.jar Searcher /foo/bar");
            System.exit(1);
        }
    }

    private static  Map<String, String> loadFilesIntoMemory(String[] args) throws IOException {
        String directory = args[1].trim();
        FileReader fileReader = new FileReader();
        Map<String, String> filesWithContent = fileReader.readFilesFromDirectory(directory);
        System.out.println(filesWithContent.size() + " files read in directory " + directory);
        return filesWithContent;
    }
}
