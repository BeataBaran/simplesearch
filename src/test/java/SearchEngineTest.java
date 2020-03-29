import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class SearchEngineTest {

    @Test
    void search() {
        Map<String, String> filesWithContent = new HashMap<>();
        filesWithContent.put("filename", "content");
        String searchText = "test, search text.";

        Map<String, Double> searchResult = SearchEngine.search(filesWithContent, searchText);

        assertEquals(filesWithContent.size(), searchResult.size());
        assertEquals(0., searchResult.get("filename"));
    }
}
