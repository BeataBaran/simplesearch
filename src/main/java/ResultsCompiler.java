import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class ResultsCompiler {

    static SortedMap<String, Double> getTopResults(int numberOfTopResults, Map<String, Double> map) {
        SortedMap<String, Double> sortedResults = sortMapByValue(map);
        return getFirstResults(numberOfTopResults, sortedResults);
    }

    static private SortedMap<String, Double> sortMapByValue(Map<String, Double> mapToSort) {
        SortedMap<String, Double> sortedMap = new TreeMap<>((fileName1, fileName2) -> {
            Double fileRank1 = mapToSort.get(fileName1);
            Double fileRank2 = mapToSort.get(fileName2);
            int compareRankResult = fileRank2.compareTo(fileRank1);
            return compareRankResult != 0 ? compareRankResult : fileName1.compareTo(fileName2);
        });
        sortedMap.putAll(mapToSort);
        return sortedMap;
    }

    static private SortedMap<String, Double> getFirstResults(int numberOfTopResults, SortedMap<String, Double> map) {
        String key = getKey(numberOfTopResults, map);
        if (key != null) {
            return map.headMap(key);
        }
        return map;
    }

    static private String getKey(int numberOfTopResults, SortedMap<String, Double> sortedMap) {
        int i = 0;
        for (String key : sortedMap.keySet()) {
            if (i++ == numberOfTopResults) {
                return key;
            }
        }
        return null;
    }
}
