import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

class FileReader {

    HashMap<String, String> readFilesFromDirectory(String directory) throws IOException {
        HashMap<String, String> fileMap = new HashMap<>();
        FileVisitor<Path> visitor = new TextFileVisitor(fileMap);
        Files.walkFileTree(Paths.get(directory), visitor);
        return fileMap;
    }
}
