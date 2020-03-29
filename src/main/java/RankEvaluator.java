import java.util.regex.Pattern;

class RankEvaluator {

    static double getRank(String fileContent, String searchText) {
        String[] searchWords = searchText.split("\\s");
        if (searchWords.length == 0) {
            return 0;
        }
        double numberOfMatches = 0;
        for (String searchWord: searchWords) {
            Pattern pattern = Pattern.compile("\\b" + searchWord + "\\b");
            if (pattern.matcher(fileContent).find()) {
                numberOfMatches++;
            }
        }
        return numberOfMatches/searchWords.length;
    }
}
