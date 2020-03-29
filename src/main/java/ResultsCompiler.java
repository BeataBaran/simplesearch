import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class ResultsCompiler {

    static SortedMap<String, Double> getTopResults(int numberOfTopResults, Map<String, Double> map) {
        SortedMap<String, Double> sortedResults = sortMapByValue(map);
        return getFirstResults(numberOfTopResults, sortedResults);
    }

    static private SortedMap<String, Double> sortMapByValue(Map<String, Double> mapToSort) {
        SortedMap<String, Double> sortedMap = new TreeMap<>((o1, o2) -> {
            Double o1Value = mapToSort.get(o1);
            Double o2Value = mapToSort.get(o2);
            if (o2Value.compareTo(o1Value) != 0) {
                return o2Value.compareTo(o1Value);
            } else {
                return o1.compareTo(o2);
            }
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
