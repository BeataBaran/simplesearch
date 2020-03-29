import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TextFileVisitorTest {

    @InjectMocks
    TextFileVisitor visitor;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        visitor.fileMap = new HashMap<>();
    }

    @Test
    void visitTextFile() throws IOException {
        String fileName = "a.txt";
        File textFile = new File(tempDir, fileName);
        List<String> lines = Arrays.asList("x", "y", "z");
        Files.write(textFile.toPath(), lines);

        FileVisitResult result = visitor.visitFile(textFile.toPath(), null);

        assertEquals(FileVisitResult.CONTINUE, result);
        assertEquals(1, visitor.fileMap.size());
        String expected = "x\ny\nz\n".replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expected, visitor.fileMap.get(fileName));
    }

    @Test
    void visitNonTextFile() throws IOException {
        File imageFile = new File(tempDir, "b.jpg");

        FileVisitResult result = visitor.visitFile(imageFile.toPath(), null);

        assertEquals(FileVisitResult.CONTINUE, result);
        assertEquals(0, visitor.fileMap.size());
    }
}
