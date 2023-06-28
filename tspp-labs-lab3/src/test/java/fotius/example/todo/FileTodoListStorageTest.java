package fotius.example.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class FileTodoListStorageTest {
    private FileTodoListStorage storage;
    private Path testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = Files.createTempFile("test", ".txt");
        storage = new FileTodoListStorage(testFilePath);
    }
    @Test
    void saveAndLoadTodoList() throws StorageException {

        TodoList todoList = new TodoList();

        todoList.add("1");
        todoList.add("2");
        todoList.add("3");

        storage.save(todoList);

        TodoList loadedTodoList = storage.load();

        Assertions.assertEquals(todoList.items(), loadedTodoList.items());
    }
}