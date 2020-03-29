import java.util.Map;
import java.util.TreeMap;

class SearchEngine {

    static Map<String, Double> search(Map<String, String> filesWithContent, String searchText) {
        return performSearch(filesWithContent, prepareSearchInput(searchText));
    }

    private static String prepareSearchInput(String input) {
        return input.replaceAll("[^\\w\\s]|_", "").replaceAll("\\s+", " ");
    }

    private static Map<String, Double> performSearch(Map<String, String> filesToSearch, String searchText) {
        Map<String, Double> filesWithScore = new TreeMap<>();
        filesToSearch.forEach((fileName, content) -> filesWithScore.put(fileName, RankEvaluator.getRank(content, searchText)));
        return filesWithScore;
    }
}
