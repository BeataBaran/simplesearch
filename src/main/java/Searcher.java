import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Searcher {
    private static final int NUMBER_OF_RESULTS = 10;

    public static void main(String[] args) throws IOException {
        validateProgramArguments(args);
        Map<String, String> filesWithContent = loadFilesIntoMemory(args);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("search> ");
            String input = br.readLine().trim();
            if (input.equals(":quit")) {
                System.exit(0);
            }
            if (input.equals("")) {
                continue;
            }
            Map<String, Double> searchResult = performSearch(filesWithContent, prepareSearchInput(input));
            Map<String, Double> topSearchResult = ResultsCompiler.getTopResults(NUMBER_OF_RESULTS, searchResult);
            printResults(topSearchResult);
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

    private static String prepareSearchInput(String input) {
        return input.replaceAll("[^\\w\\s]|_", "").replaceAll("\\s+", " ");
    }

    private static Map<String, Double> performSearch(Map<String, String> filesToSearch, String searchText) {
        Map<String, Double> filesWithScore = new TreeMap<>();
        filesToSearch.forEach((fileName, content) -> filesWithScore.put(fileName, RankEvaluator.getRank(content, searchText)));
        return filesWithScore;
    }

    private static void printResults(Map<String, Double> searchResult) {
        searchResult.forEach((key, value) -> System.out.println(String.format("%s : %.0f%%", key, value * 100)));
    }
}
