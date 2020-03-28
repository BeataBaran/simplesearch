import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileReaderTest {

    @InjectMocks
    FileReader fileReader;

    @Mock
    TextFileVisitor visitor;

    @Test
    void readFilesFromDirectory() throws IOException {
        Map<String, String> filesWithContent = fileReader.readFilesFromDirectory("./src/test/resources/test");

        assertNotNull(filesWithContent);
        assertEquals(1, filesWithContent.size());
        assertTrue(filesWithContent.containsKey("a.txt"));
    }

}
