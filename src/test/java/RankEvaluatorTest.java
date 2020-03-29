import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class RankEvaluatorTest {

    @Test
    void getRankForPartlyMatchedText() {
        double rank = RankEvaluator.getRank("Test text file content!", "the file");
        assertEquals(0.5, rank);
    }

    @Test
    void getRankForNotMatchedText() {
        double rank = RankEvaluator.getRank("Test text file content!", "something else");
        assertEquals(0, rank);
    }

    @Test
    void getRankForFullyMatchedText() {
        double rank = RankEvaluator.getRank("Test text file content", "text content");
        assertEquals(1, rank);
    }

    @Test
    void getRankWordIsPartOfOtherWord() {
        double rank = RankEvaluator.getRank("different flavors and textures", "text");
        assertEquals(0, rank);
    }

    @Test
    void getRankForMixedOrder() {
        double rank = RankEvaluator.getRank("Test text file content", "text Test");
        assertEquals(1, rank);
    }

    @Test
    void getRankForMixedCaseMatches() {
        double rank = RankEvaluator.getRank("Test text file content", "test");
        assertEquals(0, rank);
    }

    @Test
    void getRankForMatchAtTheBeginningOfText() {
        double rank = RankEvaluator.getRank("Test text file content", "Test");
        assertEquals(1, rank);
    }

    @Test
    void getRankForMatchAtTheEndOfText() {
        double rank = RankEvaluator.getRank("Test text file content", "content");
        assertEquals(1, rank);
    }

    @Test
    void getRankForWordsAtTheEndOfSentence() {
        double rank = RankEvaluator.getRank("Test text file content!", "file content");
        assertEquals(1, rank);
    }

    @Test
    void emptySearchText() {
        double rank = RankEvaluator.getRank("Test text file content!", "    ");
        assertEquals(0, rank);
    }

    @Test
    void emptyFileContent() {
        double rank = RankEvaluator.getRank("    ", "test text");
        assertEquals(0, rank);
    }
}
