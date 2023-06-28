package fotius.example.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileTodoListStorageIntegrationTest {
    private Path path;
    private FileTodoListStorage storage;

    @BeforeEach
    void beforeEach() {
        path = getBuildTmpDir().resolve(Paths.get("my-list.txt"));
        storage = new FileTodoListStorage(path);
    }

    @Test
    void elementCanBeLoaded() throws IOException {
        // tspp-labs/build/tmp/my-list.txt
        TodoList content = new TodoList(List.of("222"));

        var contentItems = content.items();
        System.out.println(path.toString());
        System.out.println(contentItems.get(0));

        Files.writeString(path, String.join("\n", contentItems));

        var tmpStorage = storage.load().items();
        //System.out.println(tmpStorage.get(0));

        Assertions.assertEquals(contentItems, tmpStorage);

    }
    @Test
    void elementCanBeSaved() throws IOException {
        TodoList storageContent = new TodoList(List.of("222"));
        storage.save(storageContent);

        Assertions.assertEquals(storageContent.items().get(0), storage.load().items().get(0));
    }
    public static Path getBuildTmpDir() {
        final Path tmp = Paths.get(System.getProperty("user.dir")).resolve("build").resolve("tmp");
        if (!Files.exists(tmp)) {
            try {
                return Files.createDirectory(tmp);
            } catch (IOException ioEx) {
                throw new RuntimeException(ioEx);
            }
        }
        return tmp;
    }
}
