package fotius.example.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodoListTest {

    // EXAMPLE TEST
    // READS AS: Test that newly created list has no items in it
    @Test
    void newlyCreatedListHasNoItemsInIt() {
        TodoList list = new TodoList();
        Assertions.assertTrue(list.items().isEmpty());
    }
    @Test
    void elementCanBeAddedToTheList() {
        TodoList list = new TodoList();
        list.add("123");
        Assertions.assertTrue(list.items().contains("123"));
    }
    @Test
    void sameElementCanBeAddedMultipleTimes(){
        TodoList list = new TodoList();
        String data = "123";
        list.add("123");
        Assertions.assertTrue(list.items().toArray().length == 1);
        list.add("123");
        Assertions.assertTrue(list.items().toArray().length == 2);
    }
    @Test
    void elementCanBeDeletedWithUseIndex() {
        TodoList list = new TodoList();
        list.add("123");
        Assertions.assertTrue(list.items().toArray().length == 1);
        list.deleteAt(0);
        Assertions.assertTrue(list.items().isEmpty());
    }
    @Test
    void elementCanBeRemovedBehindName() {
        TodoList list = new TodoList();
        list.add("123");
        Assertions.assertTrue(list.items().toArray().length == 1);
        list.delete("123");
        Assertions.assertTrue(list.items().isEmpty());
    }
}
