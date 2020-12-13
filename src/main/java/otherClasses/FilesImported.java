package otherClasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Vilrose Daquiado
 */
public class FilesImported {

    private Path file;
    private List<String> files;

    public FilesImported() {
    }

    public List<String> getFiles() throws IOException {
        files = Files.lines(Paths.get("src/ImportedFiles.txt")).distinct().collect(Collectors.toList());

        return Collections.unmodifiableList(files);
    }

    public void addFile(Path file) throws IOException{
        this.file = file;
        Files.write(Paths.get("src/ImportedFiles.txt"), file.toString().getBytes());
    }

    public boolean contains(String filename){
        Object newFile = filename;
        return files.contains(newFile);
    }

    @Override
    public String toString() {
        return "otherClasses.FilesImported{" + "file :" + file + '}';
    }
}
