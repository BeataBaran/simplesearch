import org.junit.jupiter.api.Test;

import javax.xml.crypto.dom.DOMCryptoContext;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class ResultsCompilerTest {

    @Test
    void getTopResults() {
        Map<String, Double> map = new HashMap<>();
        map.put("a", 1.);
        map.put("b",  4.);
        map.put("c", 2.);

        SortedMap<String, Double> result = ResultsCompiler.getTopResults(2, map);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("b"));
        assertTrue(result.containsKey("c"));
        assertFalse(result.containsKey("a"));
        assertEquals("b", result.firstKey());
    }


    @Test
    void getTopResultsIfSomeValuesAreTheSame() {
        Map<String, Double> map = new HashMap<>();
        map.put("a", 1.);
        map.put("c", 4.);
        map.put("b", 4.);
        map.put("d", 1.);

        SortedMap<String, Double> result = ResultsCompiler.getTopResults(3, map);

        assertEquals(3, result.size());
        assertTrue(result.containsKey("b"));
        assertTrue(result.containsKey("c"));
        assertTrue(result.containsKey("a"));
        assertFalse(result.containsKey("d"));
        assertEquals("b", result.firstKey());
        assertEquals("a", result.lastKey());
    }

    @Test
    void emptyResultList() {
        Map<String, Double> map = new HashMap<>();
        map.put("a", 1.);
        map.put("c", 4.);
        map.put("b", 4.);
        map.put("d", 1.);

        SortedMap<String, Double> result = ResultsCompiler.getTopResults(0, map);

        assertEquals(0, result.size());
    }

    @Test
    void emptyMapParameter() {
        Map<String, Double> map = new HashMap<>();

        SortedMap<String, Double> result = ResultsCompiler.getTopResults(4, map);

        assertEquals(0, result.size());
    }

    @Test
    void moreRequestedElemsThanInMap() {
        Map<String, Double> map = new HashMap<>();
        map.put("a", 1.);
        map.put("c", 4.);

        SortedMap<String, Double> result = ResultsCompiler.getTopResults(4, map);

        assertEquals(2, result.size());
    }

}
