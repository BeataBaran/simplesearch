import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class TextFileVisitor extends SimpleFileVisitor<Path> {
    Map<String, String> fileMap;

    TextFileVisitor(Map<String, String> fileMap) {
        this.fileMap = fileMap;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file) && file.getFileName().toString().endsWith(".txt")) {
            fileMap.put(file.getFileName().toString(), Files.readString(file));
        }
        return FileVisitResult.CONTINUE;
    }
}
